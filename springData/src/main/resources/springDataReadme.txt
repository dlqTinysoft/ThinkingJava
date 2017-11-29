1.SpringData：provide a familiar and consistent ,
  Spring-based programming model for data access

2.为了方便数据库的访问

3.http://projects.spring.io/spring-data/#quick-start,SpringData开源项目

4.多读英文官方文档

5.SpringData的应用场景：
        SPring Data JPA，减少数据访问层的开发量
        Spring Data Mongo DB ,分布式处理的数据库访问
        Spring Data Redis,key-value键值对的数据库，在大数据中的用的非常多
        Spring Data Solr,全文收缩的服务器,对Solr的访问

 //----------------------采用传统的方式来访问数据库--------------------------------------------
 1.jdbc访问数据库
        (1)Connection Statement ResultSet
        (2)添加依赖:<!--juit依赖-->
                    <dependency>
                      <groupId>junit</groupId>
                      <artifactId>junit</artifactId>
                      <version>4.10</version>
                      <scope>test</scope>
                    </dependency>
                    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
                    <!--mysql Driver-->
                    <dependency>
                      <groupId>mysql</groupId>
                      <artifactId>mysql-connector-java</artifactId>
                      <version>5.1.38</version>
                    </dependency>
        (3)数据表准备
            create databases springdata;
        (4)开发JDBCUtils工具类
            获取Connection,关闭Connection Statement ResultSet
        (5)Dao,数据库访问层,对象模型

 2.Spring jdbcTemplate
   (1)

 3.jdbc和Spring jdbcTemplate的弊端分析
    (1)Dao has many codes
    (2)DaoImpl has many duplicate code
    (3)Develop the page and other functions
 //---------------------使用SpringData访问数据库------------------------------------
  Repository Repostory Query Specifications
  RepositoryDefination
  PagingAndSortingRespository
  CrudRepository
  JpaRepository
  JpaSpecifications
  关于Repository的一些理解:
        SpringData的核心接口，不提共任何方法
        public interface Repository<T,id extends Seriable>
        Repository这个接口是空接口，是为了提供标记作用，也叫标记接口,如Serializable也是标记接口，没有方法声明的接口为标记接口
        如果EmployeeRepository extends Repository<T,ID extends Serializable>,则Spring来管理EmployeeRepository这个接口
        如果我们的接口没有extends Repository,则运行时会报错
        也可以不继承Repository,可以通过RepositoryDefine注解,也可被spring来识别的,既可以被spring来管理
  Repository查询方法的定义规则和使用(重点)
    但是这种方法有弊端，首先方法名太长，约定大于配置，其次对于复杂的查询很难实现
  注解Query的使用:工作中运用的最多
        只需将@Query运用到方法中就ok
  更新和删除，需要事务，必须整合事务操作
        SpringData的事务操作,使用@Modifying 和@Query两个注解联合使用 @Transaction在SpringData中的使用
        注意在做udpate 和delete的时候，在配置文件中一定要开启，<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
        要不然使用@Transactional不能够支持事务，也就是不能够用注解开启事务,同时在dao层方法上要有@Modifying和@Query这两个注解
        同时在service层要开启事务@Transactional

  CrudRepository接口的使用
  PagingAndSortingRepository接口的使用:支持分页,而且非常方便
  JpaRePository接口的使用详解
  JpaSpecificationExecutor，这个接口使单独的，和其他都不是一个体系的,分页的接口不能够带查询条件,因此这个接口诞生了
   这个接口，工作中常用，可以写个接口 extends JpaRepository,JpaSpecificationExecutor，来实现分页，并且这个分页可以带排序，查询条件的























