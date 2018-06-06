#### 关于切面PointCut的切入点
1. execution函数用于匹配方法执行的连接点，语法为：
    - execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选))
    - 参数部分允许使用通配符
        - \*  匹配任意字符，但只能匹配一个元素
        - .. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
        - \+  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
2. @annotation()表示标注了指定注解的目标类方法
    - @annotation(org.springframework.transaction.annotation.Transactional) 表示标注了@Transactional的方法
3. args()通过目标类方法的参数类型指定切点
    - args(String) 表示有且仅有一个String型参数的方法
4. @args()通过目标类参数的对象类型是否标注了指定注解指定切点
    - @args(org.springframework.stereotype.Service) 表示有且仅有一个标注了@Service的类参数的方法
5. within()通过类名指定切点
    - with(examples.chap03.Horseman) 表示Horseman的所有方法
6. @within()匹配标注了指定注解的类及其所有子类
    - @within(org.springframework.stereotype.Service) 给Horseman加上@Service标注，则Horseman和Elephantman 的所有方法都匹配
7. target()通过类名指定，同时包含所有子类
    - target(examples.chap03.Horseman)  且Elephantman extends Horseman，则两个类的所有方法都匹配
8. @target()所有标注了指定注解的类
    - @target(org.springframework.stereotype.Service) 表示所有标注了@Service的类的所有方法
9. this()
    - 大部分时候和target()相同，区别是this是在运行时生成代理类后，才判断代理类与指定的对象类型是否匹配
