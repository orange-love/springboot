#### 自定义异常的处理
1. 定义
    - 在处理controller层抛出的自定义异常时，可以实现@ControllerAdvice注解捕获
    - 自定义异常也区分普通调用和ajax调用，可以区别对待，返回不同的结果
        - 当普通调用时，一般跳转到自定义的错误页面
        - 当ajax调用时，可返回约定的数据对象，方便页面统一处理
2. 缺点
    - 如果配置捕获全局Exception.class的异常，没有办法判断是ajax还是其他方式的调用，所以没有办法区别对待，不好处理返回数据的方式
    - SpringBoot默认情况下，当handlernotfound的情况下，没有直接报错，而是通过向tomcat设置错误报告属性，然后tomcat发现http status=404，就查找默认：/error 这个路径也就是BasicErrorController来进行错误的处理。这种情况下，我们用了@ControllerAdvice自定义错误处理没有生效，因为默认没有抛出异常
        - 解决方式:
            - 设置(ErrorConfig.class)bean，将dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)。另默认情况下，静态资源handler的拦截url是/**，所以访问一定不会存在NoHandlerFoundException，所以我们需要在配置文件中，指定url拦截的 static-path-pattern，配置文件设置：spring.mvc.static-path-pattern=/public这样，我们的@ControllerAdvice注解的GlobalExceptionHandler就能处理404错误了
            - 就是自己实现ErrorController。springboot就不会自动创建BasicErrorController了，就会调用我们自己实现的Controller.即重写默认实现
            
---
#### 统一异常处理
1. 定义
    - springboot提供了默认的统一异常处理，basicErrorController，这个controller提供了默认了错误处理方法，包括错误跳转的路径和渲染方法
    - 解析
        - SpringBoot在页面发生异常的时候会自动把请求转到/error，SpringBoot内置了一个BasicErrorController对异常进行统一的处理。BasicErrorController提供两种返回错误一种是页面返回、当你是页面请求的时候就会返回页面，另外一种是json请求的时候就会返回json错误可以自定义异常页面：server.error.path:/error
        - SpringBoot提供了一种特殊的Bean定义方式，可以让我们容易的覆盖已经定义Controller,原生的BasicErrorController是定义在ErrorMvcAutoConfiguration中注解@ConditionalOnMissingBean 意思就是定义这个bean当ErrorController.class这个没有定义的时候，就是说只要我们在代码里面定义了自己的ErrorController.class时，这段代码就不生效了
2. 实现
    - 可以继承basicErrorController。或重新实现basicErrorController
    
---
#### 总结
1. 使用spring、springBoot的项目中，常见的配置大概有以下三种方案
    1. 使用@ControllerAdvice注解，自定义 exceptionHandler，继承 ResponseEntityExceptionHandler，可以配置多个exceptionHandler
        - 异常解析会先匹配 exceptionHandler中所标识的异常
        - GlobalExceptionHandler类
    2. 在第一种情况没有找到exceptionHandler 或没有解析到对应的异常 的情况下，或根据全局错误路径，访问 AbstractErrorController。也可以使用默认的错误路径访问类：BasicErrorController 
        - ErrorController
    3. 自定义的统一异常处理类：HandlerExceptionResolver 子类，是最后的异常处理策略.只有当 exceptionHandler 不存在 和 未定义带有 responseStatus的异常的情况下，只有当 exceptionHandler 不存在 和 未定义带有 responseStatus的异常的情况下， 例如抛出如下这种异常，不含responseStatus的情况下，才会进入自定义的统一异常解析类 
        -  public class MyHandlerException implements HandlerExceptionResolver {
              @Override
              public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
                                                   Exception e) {
                  ModelAndView mav = new ModelAndView();
                  MappingJackson2JsonView view = new MappingJackson2JsonView();
                  Map<String, Object> attributes = new HashMap<String, Object>();
                  attributes.put("code", "500");
                  attributes.put("message", "系统繁忙");
                  view.setAttributesMap(attributes);
                  view.setContentType("application/json;charset=UTF-8");
                  mav.setView(view);
                  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                  return mav;
          
              }
          }
2. 如何定制自己的异常页面
    - 写一个类，实现ErrorPageRegistrar接口，然后实现registerErrorPages方法，在该方法里面，添加具体的错误处理逻辑(类似web.xml里面配置错误处理方式)，这一种也是全局的异常处理