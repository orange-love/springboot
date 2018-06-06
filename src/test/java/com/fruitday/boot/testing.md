#### 单元测试
1. 类
    1. Assert
        - 断言，判断是否相等
    2. suite
        - 运行一个或多个测试用例
        -   @RunWith(Suite.class)
            @SuiteClasses({test.class})
2. 注解
    1. 方法注解
        - @BeforeClass 加载开始
        - @Before 运行开始
        - @Test 运行
        - @After 运行结束
        - @AfterClass 结束销毁
        - @Ignore
3. 测试库
    1. JUnit
        - @RunWith
            - 表达JUnit单元测试框架不要使用内置的方式进行单元测试，而使用指明的类
            来提供单元测试，默认使用SpringRunner
            - 有两个重要的类Assume、Assert和常用的注解标题2中
    2. SpringTest/SpringBootTest,对boot应用程序的单元测试
        - @SpringBootTest
            - 查找boot主程序，通过类注解@SpringBootApplication进行判断，
            并创建运行时候的spring上下文环境
        - @WebMvcTest MVC测试，参数为需要测试的Controller类
        - MockMvc是Spring提供的专门用于测试SpringMvc类
            - perform完成一次mvc调用，springmvctest是servlet容器内的一种
            模拟测试，实际不会发起http调用
                - MockMvcRequestBuilders
                    - get方法模拟一个get请求
            - andExpect 表示请求期望的返回值
            - param构造参数
            - sessionAttr 模拟session
            - cookie 模拟cookie
            - content 设置http body内容
            - contentType http内容
            - accept 期望返回内容
            - header 设置HTTP头
            - MockMvcResultMatchers
                - content 返回内容
    3. Mockito框架，用于模拟任何spring管理的bean(详情见MockTest.class)
        - 通过模拟虚拟对象来代替测试的方法称为mock测试
            - 常用工具
            - Mockito是springboot内置
            - JMock
            - EasyMock
        - mockito可以模拟任何类和接口，模拟方法调用的返回值，模拟抛出异常等。
        mockito实际上同时也记录调用这些模拟方法的输入、输出和顺序，从而可以
        校验这些模拟对象是否被正确的顺序调用，以及按照期望的属性被调用
        - 可以单独运行RunWith(MockitoJUnitRunner.class)进行运行
        - 模拟对象
        - 模拟参数
        - 模拟返回值
        - 模拟异常
        - BBDMockito
            - given 模拟一个方法调用和返回
            - when
            - then
            - anyInt() 指示可以传入任意参数
            - willReturn(1) 固定返回值
        - @MockBean用来模拟实现，在soeingmvc测试中，spring容器不会初始化
            - 模拟注入service方法中所调用的方法。会直接忽视
    4. AssertJ，一个流畅的assertion库，提供了期望值与测试返回值的比较方式
    5. Hamcrest，库的匹配对象（约束或谓词）
    6. JSONassert，对JSON对象或字符串断言的库
    7. JsonPath提供类似XPath用来获取JSON数据片段
        - spring boot内置JsonPath来比较返回的JSON内容
        - $表示根节点
4. 面向数据库的单元测试
    1. @Sql
        - @Sql("sql.sql")
            - 可以包含多个sql，用来在方法执行前进行初始化数据。sql路径没有以/开头，
            则默认是在test包下。否则从根目录搜索，也可以使用classpath、file、
            http作为前缀的资源文件
        - @ActiveProfiles("环境名称")
            - 可以指定激活要测试的环境
    2. XLSUnit
    3. DBUnit
    - 2、3未实践