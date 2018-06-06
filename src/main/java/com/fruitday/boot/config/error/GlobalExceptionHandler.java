package com.fruitday.boot.config.error;

import com.fruitday.boot.domain.error.BusinessException;
import com.fruitday.boot.domain.error.ErrorInfo;
import com.fruitday.boot.domain.error.MyException;
import com.fruitday.boot.enums.HttpResault;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 * 全局异常处理类，重写默认异常实现
 * 在处理controller层抛出的自定义异常时，可以实现@ControllerAdvice注解捕获
 * 自定义异常也区分普通调用和ajax调用，可以区别对待，返回不同的结果
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.application.name:}")
    private String systemName;

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", e);
        model.addObject("url", req.getRequestURL());
        model.addObject("data", req.getMethod());

        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            model.addObject("code", HttpResault.NOT_FOUND);
        } else {
            model.addObject("code", HttpResault.ERROR);
        }
        model.setViewName(DEFAULT_ERROR_VIEW);
        return model;
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ModelAndView erfror(HttpServletRequest req, Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("/404");
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> error = new ErrorInfo<>();
        error.setMessage(e.getMessage());
        error.setCode(ErrorInfo.ERROR);
        error.setData("Some Data" + req.getMethod());
        error.setUrl(req.getRequestURL().toString());
        return error;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, HttpServletResponse response, NotFoundException ex) {
        log.warn("{}={}={}", ex.getClass(), systemName, ex.getMessage());
//        log.warn(ex.getMessage(), ex);
        if (ex.getMessage() == null) {
            NotFoundException ne = new NotFoundException(HttpResault.NOT_FOUND.getReasonPhrase(), ex);
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        log.warn("BusinessException{}={}={}", ex.getClass(), systemName, ex.getMessage());
        Throwable orgEx = ex.getCause();
        if (orgEx != null)
            log.error(orgEx.getMessage(), orgEx);
        else
            log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getStackTrace());
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseBody
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return strBuilder.toString();
    }
}
