#### 读取配置文件
1. 方式
    1. 通用的Enviroment类
        - 通用的读取应用程序运行时的环境变量的值，可以读取properties、命令行参数、系统属性、操作系统环境变量等
        - 可以通过容器自动注入调用
    2. 通过key-value方式获取
        - ConfigurationProperties
    3. 通过@Value值
        - SpEL表达式
            - ${aa:moren}