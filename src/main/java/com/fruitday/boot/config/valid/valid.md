#### 校验数据
> 
1. 定义
    1. SpringBoot在内部通过集成hibernate-validation 已经实现了JSR-349验证规范接口 
2. 校验方式
    1. 对封装的Bean进行验证
    2. 对方法简单参数的验证
3. 比较
    1. @Valid是使用hibernate validation的时候使用
        - 可以用在方法、构造函数、方法参数和成员属性（字段）上
        - 作为标准JSR-303规范，还没有吸收分组的功能
        - 用在方法入参上无法单独提供嵌套验证功能。能够用在成员属性（字段）上，
        提示验证框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证
        - 使用hibernate validation的时候使用
        - java的JSR303声明了这类接口，然后hibernate－validator对其进行了实现
    2. @Validated 是只用spring  Validator 校验机制使用
        - 可以用在类型、方法和方法参数上。但是不能用在成员属性（字段）上
        - 提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制
        - 配合BindingResult可以直接提供参数验证结果
        - 用在方法入参上无法单独提供嵌套验证功能。不能用在成员属性（字段）上，
        也无法提示框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证
        - 只用spring  Validator 校验机制使用
    3. 嵌套验证
        - @Valid类注解上也说明了它支持嵌套验证功能
4. 注解
    1. Api
        - @Null	只能是null
        - @NotNull 不能为null 注意用在基本类型上无效，基本类型有默认初始值
        - @AssertFalse 必须为false
        - @AssertTrue	必须是true
    2. 字符串/数组/集合检查：(字符串本身就是个数组)
        - @Pattern(regexp="reg") 验证字符串满足正则
        - @Size(max, min) 验证字符串、数组、集合长度范围
        - @NotEmpty 验证字符串不为空或者null
        - @NotBlank 验证字符串不为null或者trim()后不为空
    3. 数值检查：同时能验证一个字符串是否是满足限制的数字的字符串
        - @Max 规定值得上限int
        - @Min 规定值得下限	
        - @DecimalMax("10.8")	以传入字符串构建一个BigDecimal，规定值要小于这个值 
        - @DecimalMin 可以用来限制浮点数大小
        - @Digits(int1, int2) 限制一个小数，整数精度小于int1；小数部分精度小于int2
        - @Digits 无参数，验证字符串是否合法	
        - @Range(min=long1,max=long2) 检查数字是否在范围之间
      这些都包括边界值
    4. 日期检查：Date/Calendar
        - @Post 限定一个日期，日期必须是过去的日期
        - @Future 限定一个日期，日期必须是未来的日期
    5. 其他验证：
        - @Vaild 递归验证，用于对象、数组和集合，会对对象的元素、数组的元素进行一一校验
        - @Email 用于验证一个字符串是否是一个合法的右键地址，空字符串或null算验证通过
        - @URL(protocol=,host=,port=,regexp=,flags=) 用于校验一个字符串是否是合法URL