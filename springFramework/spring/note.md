## 一、常用依赖
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.0.RELEASE</version>
        </dependency>
    </dependencies>
```
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" 
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    // 启用注解配置
    <context:annotation-config/> 

    <bean id="Cat111" class="com.mu.pojo.Cat" />
    <bean id="Dog222" class="com.mu.pojo.Dog" />
    <bean id="Person" class="com.mu.pojo.Person" />

</beans>
```
## 二、注解说明
- @Autowired :自动装配通过类型，名字
    如果不能唯一自动装配上属性，则需要通过@Qualifier(value="xxx")
- @Nullable : 字段标记了这个注解，说明这个字段可以为null;
- @Resource : 自动装配通过名字，类型
- Component : 

@Component有几个衍生注解，我们在web开发中，会按照mvc三层架构分层！
- dao 【@Repository】
- service  【@Service】
- controller  【@Controller】

**四个注解功能一样，都是代表将某个类注册到Spring中，装配Bean**


## 三、Spring-Mybatis错误
**nested exception is java.lang.NoSuchMethodError**
- bug背景：maven父工程中有多个子工程，子工程间相互依赖。启动（若干）service和web后，可能会出现这个NoSuchMethodError异常。
- bug原因：子工程中的某些pom依赖交叉重复。

解决方法：仔细检查各个子工程的pom依赖，不要交叉重复依赖
