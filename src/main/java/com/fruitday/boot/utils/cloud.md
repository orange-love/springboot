Spring Cloud为开发者提供了一套可以用来快速搭建分布式系统中常见模式的工具。提取主干即是Spring Cloud提供了一套工具。这些工具为开发人员提供了分布式系统下常见问题的通用解决方案。这些方案涵盖了配置管理，服务发现，断路器，智能路由，微代理，控制总线，一次性TOKEN，全局锁，leader选举，分布式session等。

Spring Cloud版本
版本名称	版本
Finchley	snapshot版
Edgware	snapshot版
Dalston SR1	当前最新稳定版本
Camden SR7	稳定版本
Brixton SR7	稳定版本
Angel SR6	稳定版本

Spring Cloud与Spring Boot版本匹配关系
Spring Cloud	Spring Boot
Finchley	兼容Spring Boot 2.0.x，不兼容Spring Boot 1.5.x
Dalston和Edgware	兼容Spring Boot 1.5.x，不兼容Spring Boot 2.0.x
Camden	兼容Spring Boot 1.4.x，也兼容Spring Boot 1.5.x
Brixton	兼容Spring Boot 1.3.x，也兼容Spring Boot 1.4.x
Angel	兼容Spring Boot 1.2.x

什么是SpringCloud
官方的说法就是Spring Cloud 给开发者提供一套按照一定套路快速开发 分布式系统 的工具。 
具体点就是Spring boot实现的微服务架构开发工具。它为微服务架构中涉及的配置管理、服务治理、断路器、智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理等操作提供了一种简单的开发方式。

Spring Cloud的组成
Spring clod包含了多个子项目，如下所述（Spring Cloud一直在更新，这里只是部分，写多了也不一定懂，所以现在只是概括一下，实践过才知道）

Spring Cloud Config 配置管理工具，支持使用Git存储配置内容，可以使用它实现应用配置的外部化存储，并支持客户端配置信息刷新、加密/加密配置内容等。
Spring Cloud Netflix 核心组件（相对于国内的Duboo），对多个NetflixOSS开源套件进行整理。
Eureka 服务治理组件，包含服务注册中心，服务注册与发现机制的实现。（服务治理，服务注册/发现）
Hystrix 容错管理逐渐，实现断路器模式，帮助服务以来中出现的延迟和为故障提供强大的容错能力。(熔断、断路器，容错)
Ribbon 客户端负载 均和的服务调用组件（客户端负载）
Feigin 给予Ribbon和Hystrix的声明式服务调用组件 （声明式服务调用）
Zuul 网关组件，提供智能路由，访问过滤功能
Archaius 外部化配置组件
Spring Cloud Bus 事件、消息总线 （消息总线）
Spring Cloud Cluster 针对Zookeeper、Redis、Hazelcast、Consul、的选举算法和通用状态模式的实现
Spring Cloud Cloudfoundry 与Pivatal Cloudfoundry的整合支持
Spring Cloud Consul 服务发现与配置管理工具
Spring Cloud Stream 通过Redis、Rabbit或者卡夫卡实现消费微服务，可以通过简单的声明式模型发送和接收消息
Spring Cloud AWS 用于简化整合 Amazon Web Service 的组件
Srping Cloud Security 安全工具包，提供在Zuul代理中的OAuth2客户端请求的中继器。
Spring Cloud Sleuth Spring Cloud营运的分布式跟踪实现，可以完美整合Zipkin
Spring Cloud Zookeeper 给予Zookeeper的服务发现与配置管理组件
Spring Cloud Starers Spring Cloud的基本组件，它基于Spring Boot风格项目的基础依赖模块
Spring Cloud CLI 用于在Groovy中快速创建Spring Cloud应用的Spring Boot CLI插件
….
版本说明和选择
因为Spring Cloud不同其他独立项目，它拥有很多子项目的大项目。所以它是的版本是 版本名+版本号 （如Angel.SR6）。 
版本名：是伦敦的地铁名 
版本号：SR（Service Releases）是固定的 ,大概意思是稳定版本。后面会有一个递增的数字。 
所以 Brixton.SR5就是Brixton的第5个Release版本。



<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.8.RELEASE</version>
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<!-- 管理依赖  -->
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Edgware.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<!-- 注意： 这里必须要添加， 否者各种依赖有问题  -->
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/libs-milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>