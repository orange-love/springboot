package com.fruitday.boot.config.error;

import com.fruitday.boot.domain.error.ErrorInfo;
import com.fruitday.boot.enums.HttpResault;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常捕捉
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalExceptionController extends AbstractErrorController {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

    private final ErrorProperties errorProperties;

    public GlobalExceptionController(){
        this(new DefaultErrorAttributes());
    }
    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        this(errorAttributes, new ErrorProperties());
    }
    public GlobalExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity error(HttpServletRequest request, Exception ex) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        String errMsg = getErrorMessage(request) != null ? getErrorMessage(request) : (String)body.get("message");
        log.error("url:{},error:{},message:{}", body.get("path"), body.get("error"), errMsg);
        if(status == HttpStatus.NOT_FOUND) {
            NotFoundException ne = new NotFoundException(HttpResault.NOT_FOUND.getReasonPhrase(), ex);
            return new ResponseEntity(ne.getMessage(), status);
        } else {
            ErrorInfo<Exception> error = new ErrorInfo<>();
//            error.setCode(super.getStatus(request).value());
            error.setCode(ex.hashCode());
            error.setMessage(errMsg);
            error.setUrl(request.getRequestURI());
            error.setData(ex);
            return new ResponseEntity(error, status);
        }
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "服务器错误，请联系管理员";
    }

    /**
     * 判断返回页面或json
     * @param request
     * @return
     */
    protected boolean isJSonRequest(HttpServletRequest request) {
        String requesturl = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requesturl != null && requesturl.endsWith(".josn")) {
            return true;
        } else {
//            也可以根据Http头的Accept字段是否包含JSON来判断
//            request.getHeader("Accept").contains("application/json");
            return false;
        }
    }
}
