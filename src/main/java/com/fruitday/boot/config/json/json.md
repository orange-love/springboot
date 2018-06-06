#### JSON
> 序列化和反序列化
1. 类别
    1. Fastjson国内
    2. JackJson
        - Springboot内置
    3. GSON
   
#### JackJson
1. 使用
    1. @ResponseBody将对象序列化为json
    2. 在JacksonConf类中自定义配置
        - JacksonConf.class
    3. 集合的反序列化
        - json字符串无法直接反序列化，需要提供JavaType指明集合类型 
        - JavType type= getCollectionType(List.class,User.class);
2. 序列化和反序列化方式
    1. 使用JsonParser解析json，解析结果是一串Tokens，使用JsonGennerator生成json是最底层的方式
        - 树模型和数据绑定模型都是基于流式操作完成的
        - 详情见JsonController
    2. 使用树遍历方式，json被读入到JsonNode对象中，类似于XML DOM操作
        - 树遍历方式常用于没有POJO对应的json
        - JsonNode jsonNode = (new ObjectMapper()).readTree("{\"name\":\"aaa\"}");
    3. 使用DataBind方式。将POJO和JSON序列和反序列化。可以用jsckjson注解和序列化实现类定制序列化和反序列化操作
        - DataBind最常用的方式
          - 序列化(new ObjectMapper()).readTree("{\"name\":\"aaa\"}"，User.class);
          - 反序列化(new ObjectMapper()).writeValueAsString(User);
3. jackjson注解
    1. JsonProperty
        - 指定别名
        - JsonNaming 自动命名策略
    2. JsonIgnore 
        - 忽略此属性
    3. JsonIgnoreProperties
        - 忽略一组属性，作用在类上
    4. JsonAnySetter
        - 标记在方法上，方法接收key、value参数。用于jackson在反序列化过程中，未找到的对应属性都调用此方法，通常该方法用map来实现
    5. JsonAnyGetter
        - 标注在返回map的方法上，jackson会取出map中每一个值进行序列化
    6. JsonFormat
        - 用于日期格式化
    7. JsonSerialize 
        - 自定义序列化。指定一个实现类来自定义序列化，类需要实现JsonSerializer接口
    8. JsonDeserialize
        - 自定义反序列化。指定一个实现类来自定义反序列化，类需要实现JsonDeserializer接口
    9. JsonView
        - 作用在类或属性上，用来定义一个序列化组。可以根据组来显示某些字段