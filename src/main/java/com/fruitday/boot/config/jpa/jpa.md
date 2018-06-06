#### 概念
1. 访问数据库方式分为
    1. 以sql为中心，在jdbc中做一定程度封装
        - beetlSQL
        - MyBatis
    2. 以java Entity为中心，将实体和实体关系对应到数据库的表和表关系
        - ORM工具
        
#### JPA
1. 定义
    1. 使用Hibernate实现
        - ddl-auto 是否自动创建库
            - 默认none
        - show-sql
            - 打印sql
    2. Repository
        1. 是spring Data的核心，抽象了对数据库和Nosql的操作，提供下列接口
            - CrudRepository：提供了基本的增删改查和批量操作接口
            - PagingAndSortingRepository：集成CrudRepository，提供了附加的分页查询功能
            - JpaRepository：专门用于jpa。提供丰富的数据库访问接口
2. 查询
    1. EntityManager是jpa提供的数据库访问接口