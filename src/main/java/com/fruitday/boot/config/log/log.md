## Log
1. LogBack、Slf4j和Log4j之间的关系
    - Slf4j是The Simple Logging Facade for Java的简称，
    是一个简单日志外观模式抽象框架，它本身只提供了日志Facade API
    和一个简单的日志类实现，一般常配合Log4j，LogBack，java.util.logging使用
    - Slf4j作为应用层的Log接入时，程序可以根据实际应用场景动态调整底层的
    日志实现框架(Log4j/LogBack/JdkLog…)
    - LogBack是一个日志框架，与Log4j同源
    - LogBack和Log4j都是开源日记工具库，LogBack是Log4j的改良版本，
    比Log4j拥有更多的特性，同时也带来很大性能提升
    - LogBack官方建议配合Slf4j使用，这样可以灵活地替换底层日志框架
    
#### LogBack
> https://blog.csdn.net/liubo2012/article/details/46337063
1. 结构
    - LogBack被分为3个组件
    - logback-core提供了LogBack的核心功能，是另外两个组件的基础
    - logback-classic则实现了Slf4j的API，所以当想配合Slf4j使用时，需要
    将logback-classic加入classpath
    - logback-access是为了集成Servlet环境而准备的，可提供HTTP-access的日志接口
2. 定义
    - Logback是log4j框架的作者开发的新一代日志框架，它效率更高、能够适应诸多
    的运行环境，同时天然支持SLF4J
    - 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < 
    FATAL，如果设置为WARN，则低于WARN的信息都不会输出
    - 默认情况下，Spring Boot会用Logback来记录日志，并用INFO级别输出到控制台
3. 元素
    - 时间日期：精确到毫秒
    - 日志级别：ERROR, WARN, INFO, DEBUG or TRACE
    - 进程ID
    - 分隔符：--- 标识实际日志的开始
    - 线程名：方括号括起来（可能会截断控制台输出）
    - Logger名：通常使用源代码的类名
    - 日志内容
4. 控制台输出
    - 默认配置ERROR、WARN和INFO级别的日志输出
    - 开发的时候推荐开启调试模式
        - 在运行命令后加入--debug标志，
        如：$ java -jar springTest.jar --debug
        - 在application.properties中配置debug=true，
        该属性置为true的时候，核心Logger（包含嵌入式容器、hibernate、
        spring）会输出更多内容，但是你自己应用的日志并不会输出为DEBUG级别
5. 文件输出
    - 默认不会写入文件。若要写入需要在配置文件中设置
        - logging.file 设置文件，可以是绝对路径，也可以是相对路径。
        如：logging.file=my.log
        - logging.path 设置目录，会在该目录下创建spring.log文件，
        并写入日志内容，如：logging.path=/var/log
    - 二者不能同时使用，如若同时使用，则只有logging.file生效
6. 级别设置
    - 默认情况下，日志文件的大小达到10MB时会切分一次，产生新的日志文件，
    默认级别为：ERROR、WARN、INFO
    - 格式为：’logging.level.* = LEVEL’
        - logging.level：日志级别控制前缀，*为包名或Logger名
            - logging.level.com.dudu=DEBUG：com.dudu包下所有
            class以DEBUG级别输出
        - LEVEL：选项TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
            - logging.level.root=WARN：root日志以WARN级别输出
            - 用来指定最基础的日志输出级别
7. 自定义日志配置
    - 日志服务一般都在ApplicationContext创建前就初始化了，它并不是
    必须通过Spring的配置文件控制
        - 根据不同的日志系统，你可以按如下规则组织配置文件名，就能被正确加载
            - Logback：logback-spring.xml, logback-spring.groovy, 
            logback.xml, logback.groovy
            - Log4j：log4j-spring.properties, log4j-spring.xml, 
            log4j.properties, log4j.xml
            - Log4j2：log4j2-spring.xml, log4j2.xml
            - JDK (Java Util Logging)：logging.properties
    - 默认的命名规则
        - boot默认带有-spring的文件名作为你的日志配置，如
        logback-spring.xml。boot可以为它添加一些spring boot特有的配置项
        - 放在src/main/resources
    - 自定义命名规则
        - 用于针对不同运行时Profile使用不同的日志配置
        - 在application.properties配置文件里面通过logging.config属性
        指定配置文件命名
            - logging.config=classpath:logging-config.xml
8. 结点属性
    - 根节点\<configuration>
        - 属性
            - scan:当此属性设置为true时，配置文件如果发生改变，将会被
            重新加载，默认值为true
            - scanPeriod:设置监测配置文件是否有修改的时间间隔，默认的时间
            间隔为1分钟。默认单位是毫秒。当scan为true时，此属性生效
            - debug:当此属性设置为true时，将打印出logback内部日志信息，
            实时查看logback运行状态。默认值为false
            - 以\<configuration>开头，后面有零个或多个\<appender>元素，
            有零个或多个\<logger>元素，有最多一个\<root>元素
        - 子节点
            - 2个属性
                - \<contextName> 
                    - 设置上下文名称，默认上下文名称为“default”
                    - 一旦设置，不能修改,可以通过%contextName来打印日志
                    上下文名称
                - \<property>
                    - 定义变量值的标签，有两个属性，name和value
                        - name的值是变量的名称
                        - value的值时变量定义的值
                    - 定义的值会被插入到logger上下文中。定义变量后，可以
                    使“${}”来使用变量
            - 3个节点
                - \<appender> 
                    - 负责写日志的组件,用来格式化日志输出节点，有俩个属性
                        - name指定appender 的名称
                        - class用来指定哪种输出策略，
                        常用就是控制台输出策略和文件输出策略
                            - ConsoleAppender 
                            - FileAppender
                            - RollingFileAppender
                    - ConsoleAppender输出到控制台
                        - \<filter> 
                            - 系统定义的拦截器。class为ThresholdFilter
                            - 用ThresholdFilter来过滤掉ERROR级别以下
                            的日志不输出到文件中。默认不配置
                        - \<target> : 字符串System.out 或者 System.err,
                         默认 System.out
                        - \<encoder>对日志进行格式化
                    - FileAppender把日志添加到文件
                        - \<file>被写入的文件名,可以是相对目录,也可以是绝对目录,
                         如果目录不存在则会自动创建
                        - \<append>如果是true,日志被追加到文件结尾,如果是
                        false,清空现存文件,默认是true
                        - \<encoder>对日志进行格式化
                        - \<prodent> 如果是true，日志会被安全的写入文件,即使其
                        他的FileAppender也会向此文件做写入操作,默认是false
                    - RollingFileAppender输出到文件
                        - 滚动纪录文件，先将日志记录到指定文件，当符合某种条件时，
                        将日志记录到其他文件
                        - 用于切分文件日志
                        - \<file>被写入的文件名,可以是相对目录,也可以是绝对目录,
                         如果目录不存在则会自动创建
                        - \<append>如果是true,日志被追加到文件结尾,如果是
                        false,清空现存文件,默认是true
                        - \<encoder>对日志进行格式化
                        - \<rollingPolicy>
                            - 当发生滚动时，决定 RollingFileAppender的行为，
                            涉及文件移动和重命名
                            - TimeBaseRollingPolicy 
                                - 最常用的滚动策略，根据时间来制定滚动策略，即
                                负责滚动也负责触发滚动
                                - 日志的切分方式——把每一天的日志归档到一个文件中
                                - \<file>可有可无，通过设置file，可以为活动文
                                件和归档文件制定不同位置，当前日志总是纪录到
                                file指定的文件，活动文件的名称不会改变，如果没有
                               设置file，活动文件的名称会根据fileNamePattern的
                                值，每隔一段时间改变一次，“／”或者“\”会被当作
                                目录分隔符
                                - \<fileNamePattern>
                                    - 必要节点，包含文件及“％d”转换符，
                                    “％d”可以包含一个java.text
                                    .SimpleDateFormat
                                    制定的时间格式，如：％d｛yyyy-MM｝,如果
                                    直接使用 ％d ，默认格式是 yyyy－MM－dd
                                    - ${log.path}/logback.%d
                                    {yyyy-MM-dd}.log
                                    - %d{yyyy-MM-dd_HH-mm}
                                    精确到分的日志切分方式
                                - \<maxHistory>可选节点，控制保留的归档文件的
                                最大数量，超出数量就删除旧文件，假设设置每个月
                                滚动，且\<maxHistory> 是 6，则只保存最近6个月
                                的文件，删除之前的旧文件，注意：删除旧文件是哪
                                些为了归档而创建的目录也会被删除
                                - \<filenamePattern>必须包含“%i”例如：
                                设置最小值，和最大值分别为1和2，命名模式为
                                log%i.log,会产生归档文件log1.log和
                                log2.log，还可以指定文件压缩选项，例如：
                                log％i.log.gz 或者 log%i.log.zip
                                - \<triggeringPolicy>告知
                                RollingFileAppender
                                激活RollingFileAppender滚动
                                - \<totalSizeCap>指定日志文件的上限大小
                            - SizeBasedTriggeringPolicy
                                -  查看当前活动文件的大小,如果超过指定大小会告
                                知RollingFileAppender,触发当前活动滚动,只有
                                一个节点,用来规定文件大小
                                - \<maxFileSize> : 活动文件的大小,默认10MB
                                - \<prudent>：当为true时,不支持
                                FixedWindowRollingPolicy,
                                支持TimeBasedRollingPolicy,
                                但是有两个限制
                                    - 不支持也不允许文件压缩
                                    - 不能设置file属性 . 必须留空
                                - 每天生成日志超过限定大小，
                            使用SizeAndTimeBasedRollingPolicy
                - \<encoder>
                    - 对日志进行格式化编码
                    - %d{HH: mm:ss.SSS}——日志输出时间
                    - %thread——输出日志的进程名字，这在Web应用以及
                    异步任务处理中很有用
                    - %-5level——日志级别，并且使用5个字符靠左对齐
                    - %logger{36}——日志输出者的名字
                    - %msg——日志消息
                    - %n——平台的换行符
                    - 更多见下文**layout和encoder对比**
                - \<root>
                    - 是必选节点，用来指定最基础的日志输出级别，
                    只有一个level属性,没有 name 属性，因为已经被命名 为“root”
                    - level:用来设置打印级别，大小写无关。默认是DEBUG。
                    TRACE, DEBUG, INFO, WARN, ERROR, ALL 和
                    OFF，不能设置为INHERITED或者同义词NULL
                    - 可以包含零个或多个元素，标识appender将会添加到logger
                    - 元素可以包含零个或多个元素。与元素类似，声明元素后，会先
                    关闭然后移除全部当前 appender，只引用声明了的appender。如果
                     root 元素没 有引用任何 appender，就会失去所有 appender
                - \<logger>
                    - 设置某一个包或者具体的某一个类的日志打印级别、以及
                    指定\<appender>
                    - 有一个name属性，一个可选的level和一个可选的
                    addtivity属性
                        - name:用来指定受此logger约束的某一个包或
                        具体的某一个类
                        - level:用来设置打印级别，大小写无关。默认继承
                        上级的级别
                        - addtivity:是否向上级logger传递打印信息。默认true
                    - 包含零个或多个\<appender-ref>元素，表示这个appender将会
                    添加到loger
            - \<filter> 过滤节点
                - Logback 的过滤器基于三值逻辑（ternary logic），允许把它们
                组装或成链，从而组成任 意的复合过滤策略。
                    - 这里的所谓三值逻辑是说，过滤器的返回值只能是
                    ACCEPT、DENY 和 NEUTRAL 的其中一个
                - 分类
                    - 级别过滤器（LevelFilter）
                        - 根据记录级别对记录事件进行过滤。如果事件级别等于配置
                        级别，过滤器会根据onMatch和onMismatch属性接受或拒绝事件
                    - 临界值过滤器（ThresholdFilter）
                        - 过滤掉低于指定临界值的事件。当记录的级别等于或高于临界
                        值时 , ThresholdFilter 的decide()方法会返回NEUTRAL;
                         当记录级别低于临界值时 , 事件会被拒绝
                    - 求值过滤器（EvaluatorFilter）
                        - 封装了EventEvaluator,评估是否符合指定的条件
                        - 过滤掉所有日志中不包含hello字符的日志\<expression>
                    - 匹配器（Matchers）
                        - 尽管能通过调用String类的matches()方法进行模式匹
                        配，但这会导致每次调用过滤器 时都会创建一个全新的
                        Pattern对象。为消除这种开销，你可以预先定义一个或多个
                        Matcher对象。一旦定义matcher后，就可以在求值表达式里
                        重复引用它
        - 多环境日志输出
            - 不同环境（prod:生产环境，test:测试环境，dev:开发环境）来定义
            不同的日志输出，在 logback-spring.xml中使用 springProfile
            节点来定义
            - 以logback-spring.xml命名
            - \<!-- 测试环境+开发环境. 多个使用逗号隔开. -->
              \<springProfile name="test,dev">
                  \<logger name="com.dudu.controller" level="info" />
              \</springProfile>
    - 详情见logback-spring.xml文件
9. 执行流程
    1. 获得过滤链的策略
        - 依据过滤器链返回的结果做出不同的响应。共有三个响应结果
            - FilterReply.DENY, 直接退出，不执行后续流程
            - FilterReply.NEUTRA，继续向下执行
            - FilterReply.ACCEPT，不进行步骤二,即类型输出类型检查
    2. 执行基本的选择规则
        - 主要是比较下level，如果级别低直接退出后续执行
    3. 创建LoggingEvent对象
        - 这个对象包裹一些基本信息，包括日志界别，信息本身，可能的异常信息，执行时
        间，执行线程，其实一些随日志请求一起发出的数据和MDC。其中MDC是用来装一些
        额外的上下文信息的
    4. 调用appenders
        - 此时logback会调用appender的doAppender，如果appender里有一些filer，
        此时也会调用
    5. 格式化输出结果
        - 通常情况下都是由layout层将event格式化成String型。当然也有意外比如说
        SocketAppender就是将event格式化成流
    6. 输出LoggingEvent
        - 将格式化好的结果，输出到appender中记录的地址

#### MDC requestUUID
> 一种多线程下日志管理实践方式
1. 定义
    1. MDC（Mapped Diagnostic Context，映射调试上下文）是 log4j 和
    logback 提供的一种方便在多线程条件下记录日志的功能。某些应用程序采用
    多线程的方式来处理多个用户的请求。在一个用户的使用过程中，可能有多个不
    同的线程来进行处理。
    2. 典型的例子是 Web 应用服务器。当用户访问某个页面时
    ，应用服务器可能会创建一个新的线程来处理该请求，也可能从线程池中复用已
    有的线程。在一个用户的会话存续期间，可能有多个线程处理过该用户的请求。
    这使得比较难以区分不同用户所对应的日志。当需要追踪某个用户在系统中的相
    关日志记录时，就会变得很麻烦
        - 一种解决的办法是采用自定义的日志格式。将用户信息作为主键。把用户的信息采
        用某种方式编码在日志记录中。这种方式的问题在于要求在每个使用日志记录器的类
        中，都可以访问到用户相关的信息。这样才可能在记录日志时使用。麻烦不易实现
        - MDC 可以看成是一个与当前线程绑定的哈希表，可以往其中添加键值对。MDC
        中包含的内容可以被同一线程中执行的代码所访问。当前线程的子线程会继承其
        父线程中的 MDC 的内容。当需要记录日志时，只需要从 MDC 中获取所需的
        信息即可。MDC 的内容则由程序在适当的时候保存进去。对于一个Web应用来说，
        通常是在请求被处理的最开始保存这些数据
    
#### layout和encoder对比
> 推荐使用encoder
1. encoder介绍
    1. encoder：主要工作有两个
        - 将一个event事件转换成一组byte数组
        - 将转换后的字节数据输出到文件中
    2. encoder组件是在0.9.19版本之后才引进来的。在以前的版本中，appender是使用
    layout（将一个event事件转换成一个字符串），然后使用【java.io.writer】对象
    将字符串写入到文件中
        - 自从0.9.19版本之后，Fileappender和他的子类是期望使用encoder，不再使用
        layout
    3. layout只是将一个event事件转换成一个字符串这一个功能。此外
    layout不能控制将字符串写出到文件。layout不能整合event事件到一组中。
    与encoder相比，不仅仅能按照格式进行转化，而且还能将数据写入到文件中
    5. 其中patternLayoutEncoder是最常使用encoder，他包含patternLayout大部分
    的工作
    6. 几个重要的encoder
        1. LayoutWrappingEncoder:（不怎么用）
            - 在0.9.19版本之前，都是使用layout来控制输出的格式。那就存在大量的
            layout接口（自定义）的代码。在0.9.19就变成了使用encoder来控制，如果
            我们想使用以前的layout怎么办？这个LayoutWrappingEncoder就是为了
            encoder能够操作内部layout存在的。即这个类在encoder与layout之间提供
            一个桥梁。这个类实现了encoder类，又包含了layout将evnet事件装换成
            字符串的功能
            - 原理：使用layout将输入的evnet事件转换成一个字符串，然后将字符串
            按照用户指定的编码转换成byte数组。最后将byte数据写入到文件中去
            - 在默认的情况下，输出流是立即刷新的。除非immediateFlush属性值
            为false，就不会立即刷新，但是为提高logger接入量
        2. PatternLayoutEncoder：常用。他是LayoutWrappingEncoder的子类
            - 考虑到PatternLayout是layout中最常用的组件，所以logback人员开发
            出了patternLayoutEncoder类，这个类是LayoutWrappingEncoder的扩展
            ，这个类包含了PatternLayout
            - immediateFlush标签与LayoutWrappingEncoder是一样的。默认值为
            【true】。这样的话，在已存在的项目就算没有正常情况下的关闭，也能记录所
            有的日志信息到磁盘上，不会丢失任何日志信息。因为是立即刷新。如果将
            【immediateFlush】设置为【false】，可能就是五倍的原来的logger接入
            量。但是可能会丢失日志信息在没有正常关闭项目的情况下
        3. \<appender name="FILE" class="ch.qos.logback.core.FileAppender"> 
             \<file>foo.log\</file>
             \<encoder>\<!--默认就是PatternLayoutEncoder类-->
               \<pattern>%d %-5level [%thread] %logger{0}: %msg%n\</pattern>
               \<!-- this quadruples logging throughput -->
               \<immediateFlush>false\</immediateFlush>
             \</encoder> 
           \</appender>
        4. 如果想在文件的开头打印出日志的格式信息：即打印日志的模式。使用
        【outputPatternAsHeader】标签，并设置为【true】.默认值为【false】
            - \<appender name="FILE" class="ch.qos.logback.core.FileAppender"> 
                \<file>foo.log\</file>
                \<encoder>
                  \<pattern>%d %-5level [%thread] %logger{0}: %msg%n\</pattern>
                  \<outputPatternAsHeader>true\</outputPatternAsHeader>
                \</encoder> 
              \</appender>
            - 他就会在foo.log文件的开头有一句【#logback.classic pattern: %d 
            [%thread] %-5level %logger{36} - %msg%n】然后才是打印日志的信息
        5. patternLayoutEncoder类既有layout将一个事件转化为字符串，又有将字符
        创写入到文件中的作用。他是encoder标签的默认类实例
2. layout功能
    1. layout主要的功能就是：将一个event事件转化为一个String字符串
    2. 一个重要的Layout实例：PatternLayout
        1. 在所有的layout中，patternLayout是一个灵活的Layout实例，将一个
        event转化为一个String，而这个String的格式可以有我们自定义的转化模式得到
        2. patternLayout的转换模式与C语言的printf函数相类似
        3. 一个转换模式有两部分组成：
            - 普通的文本
            - 格式控制表达式（转换说明符）
        4. 转换说明符
            - 必须以【%】开头。（必须的）
            - 【%】后面是一个格式修饰符：用于左对齐，右对齐，前或后截取等等（可选）
            - 转换的关键字。（必须的）
                - 用于控制转换成什么的数据字段。例如logger的name、level级别、
                日期、线程的名称等等
            - 在大括号【{}】中间的范围。（可选的）
            - 在patternLayout中可以使用小括号【()】形成一组转换模式。如果想要
            【()】作为普通字符，就要转义
    3. 如果想让【%】作为普通的字符串。需要使用转义字符【\】。即使用【\%】就会变成
    普通【%】插入
    4. 限制普通字符串紧跟在【转换说明符】后面
        - 例如%nhello，就会报错。因为系统找不到nhello的关键字。所以要加上分割符
        【空格，-，等等】
    5. 格式说明符：可选的。是转换说明符的第二个
        - 作用：用于左右对齐（最小宽度问题），左右截取（最大宽度问题）
        - 位置：在【%】和【转换关键字】之间
        - 几个说明符
            - 默认情况下是【右对齐】，如果想要【左对齐】，就是用减号【-】。然后就
            是一个十进制的【最小宽度】数字。如果数据内容小于最小宽度的数字，如果没
            有【-】就在前面填充空格，如果有【-】就在后面填充空格。直到到达最小宽度
            的值。如果数据内容超过最小宽度，会显示全部内容
            （在没有执行最大宽度的时候）
            - 指定最大宽度：使用点号【.】，后面加上一个十进制的数字。如果数据内容
            超过了【最大宽度】，默认情况下，会从【前面】截取数据。如果数据长度为
            10，最大宽度为8，就会去掉前面2个字符，以显示后面的8个字符数据。如果想
            从【后面截取】，就在【.】后面加上一个【-】,就会从后面截取。即【.-8】
            - 注意：在及指定最小宽度，又指定最大宽度的时候，先指定最小宽度，指定
            最大宽度
            - 如果以【.】开头，只有最大宽度的时候，就没有左右对齐之说
            - 总结：数字可以接在一下说明符的后面
                - 【直接写数字】：标识最小宽度并且是右对齐
                - 【-数字】：标识最小宽度，并且是左对齐
                - 【.数字】：标识最大宽度。如果数据内容超过最大宽度，从前面截取数据
                - 【.-数字】：标识最大宽度。如果数据内容超过最大宽度，从后面截取
                数据
                
#### 日志聚合与分析
> （摘自：http://www.ibm.com/developerworks/cn/java/j-lo-practicelog/index.html）
1. logstash + ElasticSearch 
    - 字段 client、method、request、status、bytes 和 useragent 的格式化数据
    - 未完待整理
                
#### 其他        
1. logback取代log4j的理由
    - 更快的实现：Logback的内核重写了，在一些关键执行路径上性能提升10倍以上。
    而且logback不仅性能提升了，初始化内存加载也更小了。
    - 非常充分的测试：Logback经过了几年，数不清小时的测试。
    Logback的测试完全不同级别的。
    - Logback-classic非常自然实现了SLF4j：Logback-classic实现了SLF4j。
    在使用SLF4j中，你都感觉不到logback-classic。而且因为logback-classic
    非常自然地实现了slf4j ， 所 以切换到log4j或者其他，非常容易，只需要提供
    成另一个jar包就OK，根本不需要去动那些通过SLF4JAPI实现的代码。
    - 非常充分的文档 官方网站有两百多页的文档。
    - 自动重新加载配置文件，当配置文件修改了，Logback-classic能自动重新加载
    配置文件。扫描过程快且安全，它并不需要另外创建一个扫描线程。这个技术充分
    保证了应用程序能跑得很欢在JEE环境里面。
    - Lilith是log事件的观察者，和log4j的chainsaw类似。而lilith还能处理
    大数量的log数据 。
    - 谨慎的模式和非常友好的恢复，在谨慎模式下，多个FileAppender实例跑在
    多个JVM下，能 够安全地写道同一个日志文件。RollingFileAppender会有些
    限制。Logback的FileAppender和它的子类包括 RollingFileAppender能够
    非常友好地从I/O异常中恢复。
    - 配置文件可以处理不同的情况，开发人员经常需要判断不同的Logback配置文件在
    不同的环境下（开发，测试，生产）。而这些配置文件仅仅只有一些很小的不同，
    可以通过,和来实现，这样一个配置文件就可以适应多个环境。
    - Filters（过滤器）有些时候，需要诊断一个问题，需要打出日志。在log4j，
    只有降低日志级别，不过这样会打出大量的日志，会影响应用性能。在Logback，
    你可以继续 保持那个日志级别而除掉某种特殊情况，如alice这个用户登录，她的
    日志将打在DEBUG级别而其他用户可以继续打在WARN级别。要实现这个功能只需加
    4行XML配置。可以参考MDCFIlter 。
    - SiftingAppender（一个非常多功能的Appender）：它可以用来分割日志文件
    根据任何一个给定的运行参数。如，SiftingAppender能够区别日志事件跟进用户
    的Session，然后每个用户会有一个日志文件。
    - 自动压缩已经打出来的log：RollingFileAppender在产生新文件的时候，会
    自动压缩已经打出来的日志文件。压缩是个异步过程，所以甚至对于大的日志文件，
    在压缩过程中应用不会受任何影响。
    - 堆栈树带有包版本：Logback在打出堆栈树日志时，会带上包的数据。
    - 自动去除旧的日志文件：通过设置TimeBasedRollingPolicy或者
    SizeAndTimeBasedFNATP的maxHistory属性，你可以控制已经产生日志文件的
    最大数量。如果设置maxHistory 12，那那些log文件超过12个月的都会被自动移除。
    