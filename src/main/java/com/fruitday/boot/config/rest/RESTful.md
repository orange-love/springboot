#### Rest
> 不是技术或规范，而是一种架构风格
1. 定义
    1. REST表现层状态转化
        - HTTP协议是一个无状态互联网通信协议。所有的状态都保存在服务器端
        - 客户端操作服务器，需要通过某种方式，让服务器端发送状态转化，
        这种转化是建立在表现层之上。称为表现层状态转化
        - 客户端通过url定位到服务器端某个资源，然后通过HTTP请求调用，让其在表现层的状态发生变化
        - 操作方式：get、post、put、delete、patch
    2. HTTP
        1. method
            - post 增加资源或更新
            - put 更改资源，客户端提供完整的资源属性
            - get 查询资源
            - patch 更新资源，客户端提供要更改的资源属性
            - delete 删除资源
            - head 类似get，单只有http头信息，包含要查找的信息
            - options，获取url支持的方法
        2. status
            - 200 请求成功 OK
            - 400 错误的请求。如参数错误
            - 404 请求的资源不存在 not found
            - 405 请求访问的方法不被允许
            - 406 无法使用请求的内容特性（要求）来响应请求的资源
            - 500 服务器内部错误，无法完成请求
    3. WebService
        1. 定义
            - 建立在soap协议上。soap是一种功能完备的消息交换协议
            - WSDL，webservice描述语言，能描述webservice提供的服务名称、参数、
            调用协议等，通过wsdl还能生成客户端调用代码
            - WS-*，一系列与webservice相关的辅助规范
            - 异构系统之间一种互相调用的方式
        2. 对比REST
            - webservice是一种重量级架构，rest是轻量级（web框架）
                - soap协议过于复杂，是个重量级协议，协议基于xml，本身用来
                替代xml-rpc
                - ws-*协议的复杂性，只有商业机构实现了ws规范
                - 传输数据中，json优于xml。如数据量大小，数据表现能力强，
                但影响系统性能
            - rest适合终端到服务的调用、系统内部子系统的互调、不同公司系统之间的互调
            webservice适合不同公司系统的互调
2. 使用
    1. 注解
        - @RestController用于描述REST服务，
            相当于@Controller+@ResponseBody组合
    2. RestTemplate
        - 辅助发起REST请求。默认通过JDK自带的HttpURLcONNECTION作为底层http消息
        的发送方式，使用Jackson来序列化服务器返回的JSON数据
        - 通过RestTemplateBuilder.build();来获得RestTemplate
        - getForObject获得请求信息
        - getForEntity额外获得Http信息
        - ParameterizedTypeReference保留泛型信息
            - 数据在传输的过程中存在泛型擦除，反序列化的时候不知道类型
    3. 定制RestTemplate
    4. Swagger 未完成