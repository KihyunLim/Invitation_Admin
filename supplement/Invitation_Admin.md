# Invitation_Admin 프로젝트
<span style="color:red">추후 확인해봐야 할것</span>
- 2)에 pom.xml 설정하는거에서 `javax.servlet.jsp.jstl-ap`는 왜 안되는지 모르겠다;;
  - spring web mvc에서 해당 스프링 버전의 compile dependencies 참고하면 이거 쓰는게 맞는데..
    - 쓰면 home.jsp에서 Can not find the tag library descriptor for "http://java.sun.com/
 jsp/jstl/core" 에러 남
- 4)에 로깅 설정하면서부터 junit test가 에러나는데 확인필요
  - applicationContext.xml이랑 sql-map-config.xml 확인하라는데 머지이
---

## 1. 프로젝트 생성
- help > Eclipse MarketPlace > sts 검색 > Spring Tools 3 머시기 release 설치
- new > spring legacy project > project name 입력 > templates : spring MVC project > next > top-level package : "com.invitation.khl" > finish > 프로젝트 생성

---

## 2. 프로젝트 설정 변경
- project 우클릭 > properties 
  - project facets > 
    - java : 1.8
      - runtimes : apache tomcat v8.0
    - dynamic web module : 3.1
  - java build path > libraries > 톰캣 및 자바 버전 확인

- pom.xml spring4로 설정
  - `${org.springframework-version}` : spring context 참고
  - `javax.servlet-api` : spring web mvc에서 해당 스프링 버전의 provided dependencies 참고
  - `javax.servlet.jsp-api` : spring web mvc에서 해당 스프링 버전의 compile dependencies 참고
  - `jstl` : 일단 에러가 나서 이걸로 하고 나중에 찾아봐야 할거 같다.
  - 나머지 항목들은 메이븐 최신 버전으로 변경
```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>lkh.invitation</groupId>
	<artifactId>com</artifactId>
	<name>Invitation_Admin</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
	<properties>
		<!-- Java -->
		<java-version>1.8</java-version>
		<javax.inject>1</javax.inject>
		<junit-version>4.12</junit-version>
		
		<!-- Spring  -->
		<org.springframework-version>4.3.25.RELEASE</org.springframework-version>
		<org.aspectj-version>1.9.5</org.aspectj-version>
		
		<!-- Web -->
		<servlet.version>3.0.1</servlet.version>
		<jsp.version>2.2.1</jsp.version>
		<jstl.version>1.2</jstl.version>
		
		<!-- Logging -->
		<log4j-version>1.2.17</log4j-version>
		<org.slf4j-version>1.7.25</org.slf4j-version>
		
		<!-- Maven -->
		<maven-eclipse-plugin-version>2.10</maven-eclipse-plugin-version>
		<maven-compiler-plugin-version>3.8.1</maven-compiler-plugin-version>
		<exec-maven-plugin-version>1.6.0</exec-maven-plugin-version>
	</properties>
	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				
		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j-version}</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>${javax.inject}</version>
		</dependency>
				
		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
		    <artifactId>javax.servlet-api</artifactId>
		    <version>${servlet.version}</version>
		    <scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
		    <artifactId>javax.servlet.jsp-api</artifactId>
		    <version>${jsp.version}</version>
		    <scope>provided</scope>
		</dependency>
		<!-- 이건 왜 안되는지 모르겠다;; -->
		<!-- <dependency>
		    <groupId>javax.servlet.jsp.jstl</groupId>
		    <artifactId>javax.servlet.jsp.jstl-api</artifactId>
		    <version>1.2.1</version>
		</dependency> -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>${jstl.version}</version>
		</dependency>
	
		<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit-version}</version>
			<scope>test</scope>
		</dependency>        
	</dependencies>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>${maven-eclipse-plugin-version}</version>
                <configuration>
                    <additionalProjectnatures>
                        <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                    </additionalProjectnatures>
                    <additionalBuildcommands>
                        <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                    </additionalBuildcommands>
                    <downloadSources>true</downloadSources>
                    <downloadJavadocs>true</downloadJavadocs>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven-compiler-plugin-version}</version>
                <configuration>
                    <source>${java-version}</source>
                    <target>${java-version}</target>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>${exec-maven-plugin-version}</version>
                <configuration>
                    <mainClass>org.test.int1.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

- WEB-INF 폴더 하위의 spring 폴더 삭제
    - root-context.xml과 servlet-context.xml 들어있음

- root-context.xml > applicationContext.xml 대체
  - src/main/resources 우클릭 > new > other > spring > spring bean configuration file > file name : applicationContext.xml
  - 네임스페이스에서 `context` 체크 후 자동 스캔 추가
  - src/main/java에 `com.invitation.biz`패키지 생성
```xml
<!-- applicationContext.xml -->

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<!-- 자동 스캔 추가 -->
	<context:component-scan base-package="com.invitation.biz"></context:component-scan>

</beans>
```

- servlet-context.xml > servlet-config.xml 대체
  - WEB-INF폴더 하위에 config 폴더 생성 후 우클릭 > new > other > spring > spring
bean configuration file > file name : servlet-config.xml 
  - 네임스페이스에서 `context` 체크 후 자동 스캔 추가
  - src/main/java에 `com.invitation.controller`패키지 생성
  - resolver 정보 추가
```xml
<!-- servlet-config.xml -->

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<!-- 자동스캔 추가 -->
	<context:component-scan base-package="com.invitation.controller"></context:component-scan>

	<!-- HandlerMapping, HandlerAdapter를 등록 -->
	<mvc:annotation-driven></mvc:annotation-driven>

	<!-- resolver 설정 추가 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
</beans>
```

- web.xml 수정
  - 변경된 root-context와 servlet-context 경로 수정
  - url패턴 설정
  - 인코딩  설정 추가
```xml
<!-- web.xml -->

<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>
	
	<!-- Creates the Spring Container shared by all Servlets and Filters -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- Processes application requests -->
	<servlet>
		<servlet-name>dispatcherServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/config/servlet-config.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>dispatcherServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
	
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

</web-app>
```

## 3. DB 연동 및 설정
- Maven Dependencies 추가
```xml
<!-- pom.xml -->

	<!-- ~~~ -->
	<dependencies>
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis</artifactId>
		    <version>3.5.3</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis-spring</artifactId>
		    <version>2.0.3</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
		<dependency>
		    <groupId>org.apache.commons</groupId>
		    <artifactId>commons-dbcp2</artifactId>
		    <version>2.7.0</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
		<dependency>
		    <groupId>org.mariadb.jdbc</groupId>
		    <artifactId>mariadb-java-client</artifactId>
		    <version>2.5.2</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-test</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
	
		<!-- Spring -->
		<!-- ~~~ -->
```

- DB 정보 파일 생성
  - src/main/resources에 config 폴더 생성
  - database.properties 파일 생성
```
(db.properties)

jdbc.driver=org.mariadb.jdbc.Driver
jdbc.url=jdbc:mariadb://127.0.0.1:3307/INVITATION?useSSL=false
jdbc.username=invitation
jdbc.password=[자기가 설정한 비밀번호]
```

- applicationContext에 DB 정보 설정 입력
```xml
<!-- applicationContext.xml -->

	<!-- ~~~ -->
	<context:component-scan base-package="com.invitation.biz"></context:component-scan>
	
	<!-- Database Info -->
	<context:property-placeholder location="classpath:config/database.properties"></context:property-placeholder>
	
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="${jdbc.driver}"></property>
		<property name="url" value="${jdbc.url}"></property>
		<property name="username" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
	</bean>
</beans>
```

- DB 연동 테스트 파일 작성
  - src/test/java의 com.invitation.admin에 DataSourceTest.java 파일 생성
  - 우클릭 > Run as > JUnit test 하면 연결 성공 시 아래와 같은 문구 나옴
    - `1709804316, URL=jdbc:mariadb://127.0.0.1:3307/INVITATION?useSSL=false, UserName=invitation, MariaDB Connector/J`
```java
// DataSourceTest.java

package com.invitation.admin;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class DataSourceTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

```

- 웹상에서 DB 연동 확인
  - (DB 연동 확인 구별을 위해 예제 화면 약간 수정)
  - servlet-config에 `<context:component-scan base-package="com.invitation.admin"></context:component-scan>` 추가
  - HomeController.java에 DB 연동 테스트 함수 추가
  - home.jsp 복사해서 home_dbTest.jsp 파일 생성
```java
// HomeController.java

package com.invitation.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BasicDataSource dataSource;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/dbTest.do", method = RequestMethod.GET)
	public String dbTest(Model model) {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select now() as now;");
			
			while(rs.next()) {
				model.addAttribute("dbTime", rs.getString("now"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "home_dbTest";
	}
}
```

```html
<!-- home_dbTest.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the database is ${dbTime}. </P>
</body>
</html>
```

- 매핑 설정
  - help > eclipse market place > orm 검색 > java orm plugin for eclipse beta 설치
  - 프로젝트 우클릭 > new > other > java orm plugin > mybatis configuration xml > 
    - contatiner : 해당 프로젝트 선택
    - mybatis configuration name : xql-map-config.xml 입력
  - src폴더에 생성된 db.properties는 삭제, sql-map0config.xml은 src/main/resource로 이동
```xml
<!-- sql-map-config.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

	<typeAliases>
		<!-- <typeAlias type="user" alias="com.invitation.biz.user.UserVO"></typeAlias> -->
	</typeAliases>
	
	<mappers>
		<!-- <mapper resource="mappings/useres-mapping.xml" /> -->
	</mappers>
</configuration>
```
  - 프로젝트 우클릭 > new > other > java orm plugin > mybatis mapper xml > 
    - container : 해당 프로젝트 선택
    - mybatis mapper name : [기능명]-mapping.xml 입력
  - 생성된 파일을 src/main/resources의 mappings 패키지 생성 후 이동
```xml
<!-- [기능명]-mapping.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="">

</mapper>

<!-- <mapper namespace="#package.#mapperinterfacename">

	<select id="getUserInfo" resultType="user">
		<![CDATA[
			SELECT
				*
			FORM
				USER
			WHERE
				USERID=#{userId};
		]]>
	</select>

</mapper> -->
```
- Spring, JDBC 설정
  - JDBC, session 관련 설정 추가
```xml
<!-- applicationContext.xml -->

	<!-- ~~~ -->
		<property name="password" value="${jdbc.password}"></property>
	</bean>

	<!-- Spring JDBC 설정 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<!-- Spring과 Mybatis 연동 설정 -->
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"></property>
		<property name="configLocation" value="Classpath:sql-map-config.xml"></property>
	</bean>
	
	<bean class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSession"></constructor-arg>
	</bean>
</beans>
```

---

## 4. 로깅 설정 (logback)
- dependency 추가
  - slf4j-version은 1.7.25 이후 버전으로 수정
    - logback dependency의 버전과 호환 문제가 있는것 같음
    - NoClassDefFoundError: org/slf4j/event/LoggingEvent : Logback-classic version 1.1.4 and later require slf4j-api version 1.7.15 or later
  - Class path contains multiple SLF4J bindings 도 로그 중엔 나오지만 이건 딱히 지장은 없는 듯
    - 거슬린다면 artifactId : slf4j-log4j12인거 주석처리하면 해결 되기는 함
```xml
<!-- pom.xml -->

	<!-- ~~~ -->
	<!-- Logging -->
	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-classic</artifactId>
		<version>1.2.3</version>
	</dependency>
	<dependency>
		<groupId>org.lazyluke</groupId>
		<artifactId>log4jdbc-remix</artifactId>
		<version>0.2.7</version>
	</dependency>
	<!-- ~~~ -->
```

- 로깅 파일 작성
  - src/main/resources의 log4j.xml 파일 삭제 후 logback.xml 파일 생성
  - 파일로 로그 관리하려면 '<!-- <appender-ref ref="file" /> -->' 주석 해제 하면 됨
```xml
<!-- logback.xml -->

<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<property name="LOG_DIR" value="C:/eng/log/invitation/admin"></property>
	
	<!-- Appenders -->
	<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<charset>UTF-8</charset>
			<Pattern>
				%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n
			</Pattern>
		</encoder>
	</appender>
	
	<appender name="confirmation_needed" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<charset>UTF-8</charset>
			<Pattern>
				여기이이이봐라아아아아!!! : %d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n
			</Pattern>
		</encoder>
	</appender>
	
	<appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<file>${LOG_DIR}/admin.log</file>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<Pattern>
				%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n
			</Pattern>
		</encoder>
		
		<!-- 일자에 따른 로그파일 처리 -->
		<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
			<fileNamePattern>${LOG_DIR}/admin-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
			<maxFileSize>10MB</maxFileSize>
			<maxHistory>30</maxHistory>
		</rollingPolicy>
		
		<!-- 용량에 따른 로그파일 처리 -->
		<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
			<maxFileSize>10MB</maxFileSize>
		</triggeringPolicy>
		
		<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
			<fileNamePattern>${LOG_DIR}/admin.%i.log.zip</fileNamePattern>
			<MinIndex>1</MinIndex>
			<MaxIndex>10</MaxIndex>
		</rollingPolicy>
	</appender>

	<!-- Application Loggers -->
	<logger name="com.invitation" level="info" additivity="false">
		<appender-ref ref="console" />
		<!-- <appender-ref ref="file" /> -->
	</logger>
	
	<!-- Query Loggers -->
	<logger name="jdbc.sqlonly" level="info" additivity="false">
		<appender-ref ref="console" />
		<!-- <appender-ref ref="file" /> -->
	</logger>
	
	<logger name="jdbc.resultsettable" level="info" additivity="false">
		<appender-ref ref="console" />
		<!-- <appender-ref ref="file" /> -->
	</logger>

	<!-- 3rdparty Loggers -->
	<!-- 여기 로그는 언제 나올지 확인 필요 -->
	<logger name="org.springframework.core" level="info">
		<appender-ref ref="confirmation_needed" />
		<!-- <appender-ref ref="file" /> -->
	</logger>

	<!-- Root Logger와 로그가 겹쳐 주석 처리 -->
	<!-- 
	<logger name="org.springframework.context" level="info">
		<appender-ref ref="confirmation_needed" />
		<appender-ref ref="file" />
	</logger>
	<logger name="org.springframework.beans" level="info">
		<appender-ref ref="confirmation_needed" />
		<appender-ref ref="file" />
	</logger>
	<logger name="org.springframework.web" level="info">
		<appender-ref ref="confirmation_needed" />
		<appender-ref ref="file" />
	</logger> 
	-->

	<!-- Root Logger -->
	<root level="info">
		<appender-ref ref="console" />
		<!-- <appender-ref ref="file" /> -->
	</root>
	
</configuration>
```

- root-context에 로깅 설정 추가
```xml
<!-- applicationContext.xml -->

<!-- ~~~ -->
<context:property-placeholder location="classpath:config/database.properties"/>

<bean id="dataSourceSpied" class="org.apache.commons.dbcp2.BasicDataSource">
	<property name="driverClassName" value="${jdbc.driver}"></property>
	<property name="url" value="${jdbc.url}"></property>
	<property name="username" value="${jdbc.username}"></property>
	<property name="password" value="${jdbc.password}"></property>
</bean>

<bean id="dataSource" class="net.sf.log4jdbc.Log4jdbcProxyDataSource">
	<constructor-arg ref="dataSourceSpied"></constructor-arg>
	<property name="logFormatter">
		<bean class="net.sf.log4jdbc.tools.Log4JdbcCustomFormatter">
			<property name="loggingType" value="MULTI_LINE"></property>
			<property name="sqlPrefix" value="SQL : "></property>
		</bean>
	</property>
</bean>

<!-- Spring JDBC 설정 -->
<!-- ~~~ -->
```

- logger 템플릿 지정
  - windows > preferences > java > editor > templates > new
    -  name : LOGGER
    -  context : java
    -  pattern : ${:import(org.slf4j.Logger, org.slf4j.LoggerFactory)}
private static final Logger LOGGER = LoggerFactory.getLogger(${primary_type_name}.class);
       - LOGGER.info(), LOGGER.error() 등 사용 가능
  - HomeController 파일로 테스트
```java
// HomeController.java

// LOGGER 위주로 수정 됨!!
// ~~~
private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

@Autowired
BasicDataSource dataSource;

/**
	* Simply selects the home view to render by returning its name.
	*/
@RequestMapping(value = "/", method = RequestMethod.GET)
public String home(Locale locale, Model model) {
	LOGGER.info("Welcome home! The client locale is {}.", locale);
	
	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	
	String formattedDate = dateFormat.format(date);
	
	model.addAttribute("serverTime", formattedDate );
	
	return "home";
}

@RequestMapping(value = "/dbTest.do", method = RequestMethod.GET)
public String dbTest(Model model) {
	Connection conn = null;
	Statement st = null;
	
	try {
		conn = dataSource.getConnection();
		st = conn.createStatement();
		ResultSet rs = st.executeQuery("select now() as now;");
		
		LOGGER.info("dbTime");
		
		while(rs.next()) {
		// ~~~
```

---

## 6. AOP 적용
- dependency 추가
```xml
<!-- pom.xml -->

		<!-- ~~~ -->
		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
		<dependency>
		    <groupId>org.aspectj</groupId>
		    <artifactId>aspectjweaver</artifactId>
		    <version>1.9.5</version>
		</dependency>
		
		<!-- Logging -->
		<!-- ~~~ -->
```

- AOP 설정 등록
  - applicationContext.xml에서 Namespaces 탭 클릭 > aop 선택 후 저장
```xml
<!-- applicationContext.xml -->

	<!-- ~~~ -->
	<!-- 자동 스캔 추가 -->
	<context:component-scan base-package="com.invitation.biz"></context:component-scan>
	
	<!-- AOP -->
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
	
	<!-- Database Info -->
	<!-- ~~~ -->
```

- AOP 패키지 생성
  - com.invitation.biz.common.aop 패키지 생성

- AOP 설정 클래스 생성
```java
// PointcutCommon.java

package com.invitation.biz.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.invitation.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.invitation.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
```

- AOP before 추가
```java
// BeforeAdvice.java

package com.invitation.biz.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdvice.class);
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		LOGGER.info("-------------------------------------------------------------------------------------------------------");
		if(args.length > 0) {
			LOGGER.info("[Before] " + method + " 메소드 args 정보 : " + args[0].toString());
		} else {
			LOGGER.info("[Before] " + method + " 메소드");
		}
	}
}
```

- AOP around 추가
```java
// AroundAdvice.java

package com.invitation.biz.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(AroundAdvice.class);
	
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object returnObj = pjp.proceed();
		
		stopWatch.stop();
		
		LOGGER.info("[Around] " + method + " 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return returnObj;
	}
}
```

- AOP afterReturning 추가
```java
// AfterReturningAdvice.java

package com.invitation.biz.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(AfterReturningAdvice.class);
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		if(returnObj != null) {
			LOGGER.info("[AfterReturning] " + method + " 메소드 리턴값 : " + returnObj.toString());
		} else {
			LOGGER.info("[AfterReturning] " + method + " 메소드 리턴값 : null");
		}
	}
}
```

- AOP after 추가
```java
// AfterAdvice.java

package com.invitation.biz.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AfterAdvice.class);
	
	@After("PointcutCommon.allPointcut()")
	public void finallyLog() {
		LOGGER.info("[After] 비즈니스 로직 수행 끝");
		LOGGER.info("-------------------------------------------------------------------------------------------------------");
	}
}
```

- AOP transaction 인터페이스 추가
```java
// PlatformTransactionManager.java

package com.invitation.biz.common.aop;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public interface PlatformTransactionManager {

	TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;
	void commit(TransactionStatus status) throws TransactionException;
	void rollback(TransactionStatus status) throws TransactionException;
}
```

- AOP transaction 설정 추가
```xml
<!-- applicationContext.xml -->

	<!-- ~~~ -->
		<constructor-arg ref="sqlSession"></constructor-arg>
	</bean>

	<!-- Transaction 설정  -->
	<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	
	<tx:advice id="txAdvice" transaction-manager="txManager">
		<tx:attributes>
			<tx:method name="get*" read-only="true" />
			<tx:method name="*" />
		</tx:attributes>
	</tx:advice>
	
	<aop:config>
		<aop:pointcut expression="execution(* com.invitation.biz..*(..))" id="txPointcut" />
		<aop:advisor pointcut-ref="txPointcut" advice-ref="txAdvice" />
	</aop:config>
</beans>
```

---

## 7. bootstrap 적용
- bootstrap 프레임워크 다운
  - adminLTE.io 사이트 접속해서 소스 파일 다운로드
  - 압축해제 후 `src/main/sebapp/resources/bootstrap/adminlte3`에 dist, plugins 폴더 복사

- bootstrap 파일 매핑 설정 추가
```xml
<!-- servlet-config.xml -->

	<!-- ~~~ -->
	<mvc:annotation-driven></mvc:annotation-driven>
	
	<!-- 부트스트랩 추가 -->
	<mvc:resources mapping="adminlte3/**" location="/resources/bootstrap/adminlte3/"></mvc:resources>
	<!-- js파일 매핑 -->
	<mvc:resources mapping="js/**" location="/resources/js/"></mvc:resources>

	<!-- resolver 설정 추가 -->
	<!-- ~~~ -->
```

- 페이지 작성
  - 다른 화면에서도 공통으로 사용될 코드 include로 변경
    - `src/main/webapp/WEB-INF/views/include/adminlte3/head.jsp`
    - `src/main/webapp/WEB-INF/views/include/adminlte3/js.jsp`
    - `src/main/webapp/WEB-INF/views/include/adminlte3/navbar.jsp`
    - `src/main/webapp/WEB-INF/views/include/adminlte3/sidebar.jsp`
    - `src/main/webapp/WEB-INF/views/include/adminlte3/footer.jsp`
  - 각 화면 jsp 작성
    - `src/main/webapp/WEB-INF/views/login/login.jsp`
    - `src/main/webapp/WEB-INF/views/member/memberList.jsp`
    - `src/main/webapp/WEB-INF/views/invitation/invitationList.jsp`
    - `src/main/webapp/WEB-INF/views/invitation/invitationDetail.jsp`
    - `src/main/webapp/WEB-INF/views/invitation/invitationAdd.jsp`
  - 각 화면에서 쓸 js 파일은 우선 생성만
    - `src/main/webapp/resources/js/login/login.js`
    - `src/main/webapp/resources/js/member/memberList.js`
    - `src/main/webapp/resources/js/invitation/invitationList.js`
    - `src/main/webapp/resources/js/invitation/invitationDetail.js`
    - `src/main/webapp/resources/js/invitation/invitationAdd.js`
    - `src/main/webapp/resources/js/util.js`
    - `src/main/webapp/resources/js/def.js`

```jsp
<!--head.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="../adminlte3/plugins/fontawesome-free/css/all.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- icheck bootstrap -->
	<link rel="stylesheet" href="../adminlte3/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="../adminlte3/dist/css/adminlte.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
```

```jsp
<!--js.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
	<!-- jQuery -->
	<script src="../adminlte3/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="../adminlte3/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../adminlte3/dist/js/adminlte.min.js"></script>
```

```jsp
<!--navbar.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
	<!-- Navbar -->
	<nav class="main-header navbar navbar-expand navbar-white navbar-light">
		<!-- Left navbar links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
				href="#"><i class="fas fa-bars"></i></a></li>
		</ul>
	
		<!-- Right navbar links -->
		<ul class="navbar-nav ml-auto">
			<!-- 로그아웃 버튼 -->
			<!-- Notifications Dropdown Menu -->
			<li class="nav-item dropdown"><a class="nav-link"
				data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
					class="badge badge-warning navbar-badge">15</span>
			</a>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
					<span class="dropdown-item dropdown-header">15 Notifications</span>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i
						class="fas fa-envelope mr-2"></i> 4 new messages <span
						class="float-right text-muted text-sm">3 mins</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i>
						8 friend requests <span class="float-right text-muted text-sm">12
							hours</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
						3 new reports <span class="float-right text-muted text-sm">2
							days</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item dropdown-footer">See All
						Notifications</a>
				</div></li>
		</ul>
	</nav>
	<!-- /.navbar -->
```

```jsp
<!--sidebar.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
	<!-- Main Sidebar Container -->
	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo -->
		<a href="../adminlte3/index3.html" class="brand-link">
			<img src="../adminlte3/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">Invitation Admin</span>
		</a>
	
		<!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar Menu -->
			<nav class="mt-2" id="navSidebar">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->
					<li class="nav-item">
						<a href="../member/memberList.do" class="nav-link" id="aMember">
							<i class="nav-icon fas fa-th"></i>
							<p>회원 관리</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="#" class="nav-link" id="aInvitation"> 
							<i class="nav-icon fas fa-table"></i>
							<p>
								청첩장 관리 <i class="fas fa-angle-left right"></i>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="../invitation/invitationList.do" class="nav-link" id="aInvitation_List"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 목록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="../invitation/invitationDetail.do" class="nav-link" id="aInvitation_Detail"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 상세</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="../invitation/invitationAdd.do" class="nav-link" id="aInvitation_Add"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 추가</p>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>
```

```jsp
<!--footer.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
	<footer class="main-footer">
		<div class="float-right d-none d-sm-block">
			<b>Version</b> 3.0.0
		</div>
		<strong>Copyright &copy; 2014-2019 <a href="http://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
	</footer>

	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->
```

```jsp
<!-- login.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp" %>
	<title>로그인</title>
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<span><b>Invitataion</b> Admin</span>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">계정을 입력해주세요.</p>

				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="아이디" id="inputId">
					<div class="input-group-append">
						<div class="input-group-text"></div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="비밀번호" id="inputPassword">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input type="checkbox" id="remember"> <label
								for="remember"> 아이디 저장하기 </label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-4">
						<button type="submit" class="btn btn-primary btn-block" id="btnLogin">로그인</button>
					</div>
					<!-- /.col -->
				</div>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<%@ include file="../include/adminlte3/js.jsp" %>
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/login/login.js"></script>
</body>
</html>
```

```jsp
<!--memberList.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<title>회원 관리</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>회원 관리</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>Rendering engine</th>
											<th>Browser</th>
											<th>Platform(s)</th>
											<th>Engine version</th>
											<th>CSS grade</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 4.0</td>
											<td>Win 95+</td>
											<td>4</td>
											<td>X</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 5.0</td>
											<td>Win 95+</td>
											<td>5</td>
											<td>C</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 5.5</td>
											<td>Win 95+</td>
											<td>5.5</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 6</td>
											<td>Win 98+</td>
											<td>6</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 7</td>
											<td>Win XP SP2+</td>
											<td>7</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>AOL browser (AOL desktop)</td>
											<td>Win XP</td>
											<td>6</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 1.0</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.7</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 1.5</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 2.0</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 3.0</td>
											<td>Win 2k+ / OSX.3+</td>
											<td>1.9</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Camino 1.0</td>
											<td>OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Camino 1.5</td>
											<td>OSX.3+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Netscape 7.2</td>
											<td>Win 95+ / Mac OS 8.6-9.2</td>
											<td>1.7</td>
											<td>A</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>

	<!-- page script -->
	<script>
		$(function () {
			$("#example1").DataTable();
			$('#example2').DataTable({
				"paging": true,
				"lengthChange": false,
				"searching": false,
				"ordering": true,
				"info": true,
				"autoWidth": false,
			});
		});
	</script>
</body>
</html>
```

```jsp
<!--invitationList.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<title>청첩장 목록</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 목록</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>Rendering engine</th>
											<th>Browser</th>
											<th>Platform(s)</th>
											<th>Engine version</th>
											<th>CSS grade</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 4.0</td>
											<td>Win 95+</td>
											<td>4</td>
											<td>X</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 5.0</td>
											<td>Win 95+</td>
											<td>5</td>
											<td>C</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 5.5</td>
											<td>Win 95+</td>
											<td>5.5</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 6</td>
											<td>Win 98+</td>
											<td>6</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>Internet Explorer 7</td>
											<td>Win XP SP2+</td>
											<td>7</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Trident</td>
											<td>AOL browser (AOL desktop)</td>
											<td>Win XP</td>
											<td>6</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 1.0</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.7</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 1.5</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 2.0</td>
											<td>Win 98+ / OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Firefox 3.0</td>
											<td>Win 2k+ / OSX.3+</td>
											<td>1.9</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Camino 1.0</td>
											<td>OSX.2+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Camino 1.5</td>
											<td>OSX.3+</td>
											<td>1.8</td>
											<td>A</td>
										</tr>
										<tr>
											<td>Gecko</td>
											<td>Netscape 7.2</td>
											<td>Win 95+ / Mac OS 8.6-9.2</td>
											<td>1.7</td>
											<td>A</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationList.js"></script>

	<!-- page script -->
	<script>
		$(function () {
			$("#example1").DataTable();
			$('#example2').DataTable({
				"paging": true,
				"lengthChange": false,
				"searching": false,
				"ordering": true,
				"info": true,
				"autoWidth": false,
			});
		});
	</script>
</body>
</html>
```

```jsp
<!--invitationDetail.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<title>청첩장 상세</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 상세</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationDetail.js"></script>
</body>
</html>
```

```jsp
<!--invitationAdd.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<title>청첩장 추가</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 추가</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationAdd.js"></script>
</body>
</html>
```


---

## 8. restful 적용한 로그인 및 나머지 구현
- postman 설치
  - 인터넷에서 postman 검색해서 프로그램 설치
  - 로그인 필요

- depndency 추가
```xml
<!-- pom.xml -->

		<!-- ~~~ -->
		<dependencies>
		<!-- json -->
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.8</version>
		</dependency>
	
		<!-- database -->
		<!-- ~~~ -->
```

- url 패턴 관련 설정 추가
```xml
<!-- servlet-config.xml -->

	<!-- ~~~ -->
	<mvc:annotation-driven></mvc:annotation-driven>
	
	<!-- tomcat의 server.xml에 정의돈 url-pattern "/" 무시하고 현재 DispatcherServlet의 url-pattern으로 설정 -->
	<mvc:default-servlet-handler/>
	
	<!-- 부트스트랩 추가 -->
	<!-- ~~~ -->
```

- restful 적용할 로그인 기능 추가
  - 사용자 VO 생성
    - `com.invitation.biz.admin.user` 패키지 생성
```java
// UserAdminVO.java

package com.invitation.biz.admin.user;

public class UserAdminVO {

	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserAdminVO [id=" + id + ", password=" + password + "]";
	}
}
```

  - xml 매핑 설정 파일 수정
```xml
<!-- sql-map-config.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

	<typeAliases>
		<typeAlias alias="userAdmin" type="com.invitation.biz.admin.user.UserAdminVO"></typeAlias> 
	</typeAliases>
	
	<mappers>
		<mapper resource="mappings/AdminUseres-mapping.xml" />
	</mappers>
</configuration>
```

  - 로그인 확인용 쿼리 xml 추가
```xml
<!-- AdminUseres-mapping.xml.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="AdminUserDAO">
	<select id="getUserInfo" resultType="userAdmin">
		SELECT 
			* 
		FROM 
			ADMIN 
		WHERE 
			ID = #{id};
	</select>
</mapper>
```

  - DAO 파일 생성
    - `com.invitation.biz.admin.user.impl` 패키지 생성
```java
// UserAdminDAOMybatis.java

package com.invitation.biz.admin.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.admin.user.UserAdminVO;

@Repository
public class UserAdminDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserAdminVO getUserInfo(String id) {
		return mybatis.selectOne("AdminUserDAO.getUserInfo", id);
	}
}
```

  - interface 파일 생성
```java
// UserAdminService.java

package com.invitation.biz.admin.user;

public interface UserAdminService {

	UserAdminVO getUserInfo(String id);

}
```

  - service 파일 생성
```java
// UserAdminServiceImpl.java

package com.invitation.biz.admin.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.admin.user.UserAdminService;
import com.invitation.biz.admin.user.UserAdminVO;

@Service("userAdmin")
public class UserAdminServiceImpl implements UserAdminService {

	@Autowired
	private UserAdminDAOMybatis userAdminDAO;
	
	@Override
	public UserAdminVO getUserInfo(String id) {
		return userAdminDAO.getUserInfo(id);
	}
}
```

  - 로그인 controller 수정
```java
// LoginController.java

package com.invitation.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.admin.user.UserAdminService;
import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.biz.common.exception.CommonException;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserAdminService userAdminService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login(Model model) {
		LOGGER.info("login page!!");
		
		return "login/login";
	}
	
	@PostMapping(value="/login.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> doLogin(@RequestBody UserAdminVO user, HttpSession session) throws CommonException {
		Boolean resFlag = false;
		String resMessage = "";
		Map<String, Object> result = new HashMap<String, Object>();
		
		LOGGER.info("doLogin");
		try {
			UserAdminVO resGetUserInfo = userAdminService.getUserInfo(user.getId());
			
			if(resGetUserInfo == null) {
				throw new NullPointerException("User information not found");
			}
			
			if(resGetUserInfo.getPassword().equals(user.getPassword())) {
				session.setAttribute("id", resGetUserInfo.getId());
				
				resFlag = true;
			} else {
				throw new CommonException("비밀번호 불일치!!");
			}
		} catch(NullPointerException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "등록된 아이디가 없습니다.";
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "비밀번호가 일지하지 않습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "로그인 중 에러가 발생했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
	
	@GetMapping(value="/logout.do")
	public String doLogout(HttpSession session) {
		LOGGER.info("logout!!");
		
		session.invalidate();
		
		return "login/login";
	}
}
```

  - postman으로 기능 테스트
    - POST 선택
    - url : http://localhost:8080/admin/login/login.do
    - Headers > key : Content-Type
    - Headers > value : application/json
    - Body > raw 선택 및 우측의 json 확인
      - `{"id" : "root", "password" : "1234"}` 입력
    - send 클릭

  - 웹으로 기능 테스트
    - 화면 이동을 위한 컨트롤러 파일 생성
      - `src/main/java/com.invitation.controller.member/MemberController.java`
      - `src/main/java/com.invitation.controller.invitation/InvitationController.java`
```java
// MemberController.java
package com.invitation.controller.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/memberList.do", method=RequestMethod.GET)
	public String viewMemberList(Model model) {
		LOGGER.info("memberList.do");
		
		return "member/memberList";
	}
}
```

```java
// InvitationController.java

package com.invitation.controller.invitation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/invitation")
public class InvitationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
	
	@RequestMapping(value="/invitationAdd.do", method=RequestMethod.GET)
	public String temp1(Model model) {
		LOGGER.info("invitationAdd.do");
		
		return "invitation/invitationAdd";
	}
	
	@RequestMapping(value="/invitationDetail.do", method=RequestMethod.GET)
	public String temp2(Model model) {
		LOGGER.info("invitationDetail.do");
		
		return "invitation/invitationDetail";
	}
	
	@RequestMapping(value="/invitationList.do", method=RequestMethod.GET)
	public String temp3(Model model) {
		LOGGER.info("invitationList.do");
		
		return "invitation/invitationList";
	}
}
```

    - 각 화면 js파일 작성
```js
// def.js

/**
 * 
 */

var GO_TO_MAIN = "/admin/member/memberList.do";

var MAP_SIDEBAR = {
	"member" : {
		"isUnder" : false,
		"super" : "",
		"memberList.do" : "aMember"
	},
	"invitation"  : {
		"isUnder" : true,
		"super" : "aInvitation",
		"invitationList.do" : "aInvitation_List",
		"invitationDetail.do" : "aInvitation_Detail",
		"invitationAdd.do" : "aInvitation_Add"
	}
};
```

```js
// util.js

/**
 * 
 */

function setActiveSidebar() {
	$("#navSidebar a").removeClass("active");
	$("#navSidebar li").removeClass("menu-open");
	
	var arrPathName = window.location.pathname.split("/", 4);
	if(MAP_SIDEBAR[arrPathName[2]]["isUnder"]) {
		$("#" + MAP_SIDEBAR[arrPathName[2]]["super"])
			.addClass("active")
			.parent().addClass("menu-open");
	}
	$("#" + MAP_SIDEBAR[arrPathName[2]][arrPathName[3]]).addClass("active");
};

function utilGoToMain() {
	$.ajax({
		url : "member/memberList.do",
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "/nHttp error msg : " + msg);
		},
		success : function() {
		}
	});
};
```

```js
// login.js

/**
 * 
 */

console.log("########## login.js ##########");

$(function(){
	$("#btnLogin").on("click", function(){
		var id = $("#inputId").val(),
			password = $("#inputPassword").val();
		
		if(id == "" || password == "") {
			alert("아이디 혹은 비밀번호를 입력해주세요.");
			return;
		}
		
		var reqData = {
			id : id,
			password : password
		};
		
		$.ajax({
			url : "/admin/login/login.do",
			type : "POST",
			dataType : "json",
			data : JSON.stringify(reqData),
			contentType : "application/json",
			mimeType : "application/json",
			error : function(xhr, status, msg) {
				alert("status : " + status + "/nHttp error msg : " + msg);
			},
			success : function(result) {
				if(result.resFlag) {
					window.location.href = GO_TO_MAIN;
				} else {
					alert(result.resMessage);
				}
			}
		})
	});
});
```

```js
// memberList.js

/**
 * 
 */

console.log("########## memberList.js ##########");

$(function(){
	setActiveSidebar();
});
```

```js
// invitationList.js

/**
 * 
 */

console.log("########## invitationList.js ##########");

$(function(){
	setActiveSidebar();
});
```

```js
// invitationDetail.js

/**
 * 
 */

console.log("########## invitationDetail.js ##########");

$(function(){
	setActiveSidebar();
});
```

```js
// invitationAdd.js

/**
 * 
 */

console.log("########## invitationAdd.js ##########");

$(function(){
	setActiveSidebar();
});
```


---

## 9. 회원 관리 화면 구현 - 부트스트랩 적용
- 회원 조회용 VO 파일 생성
  - `com.invitation.biz.member.user`패키지 생성
```java
// UserMemberListVO.java

package com.invitation.biz.member.user;

public class UserMemberListVO {

	private String id;
	private String name;
	private String phone;
	private String statusSee;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatusSee() {
		return statusSee;
	}
	public void setStatusSee(String statusSee) {
		this.statusSee = statusSee;
	}
	
	@Override
	public String toString() {
		return "UserMemberListVO [id=" + id + ", name=" + name + ", phone=" + phone + ", statusSee=" + statusSee + "]";
	}
}
```

- 회원 조회용 쿼리 alias 추가
  - mappings폴더에 `MemberUseres-mapping.xml`파일 생성
  - `userMemberList` alias 추가 및 `MemberUseres-mapping.xml` mapper 추가
```xml
<!-- sql-map-config.xml -->

	<!-- ~~~ -->
	<typeAliases>
		<typeAlias alias="userAdmin" type="com.invitation.biz.admin.user.UserAdminVO"></typeAlias> 
		<typeAlias alias="userMemberList" type="com.invitation.biz.member.user.UserMemberListVO"></typeAlias> 
	</typeAliases>
	
	<mappers>
		<mapper resource="mappings/AdminUseres-mapping.xml" />
		<mapper resource="mappings/MemberUseres-mapping.xml" />
	</mappers>
</configuration>
```

- 회원 조회용 쿼리 추가
  - `getMemberList` 추가
```xml
<!-- MemberUseres-mapping.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="MemberUserDAO">
	<select id="getMemberList" resultType="userMemberList">
		<![CDATA[
			SELECT
				U.ID, U.NAME, U.PHONE, (
					IFNULL((
						SELECT
							IL.VISABLE
						FROM
							INVITATION_LIST IL
						WHERE
							IL.ID = U.ID
							AND NOW() >= DATE_BEGIN
							AND NOW() <= DATE_END)
					, 'X')
				) AS STATUSSEE
			FROM
				USERES U
			WHERE
				U.DELETEFLAG <> 'Y'
			;
		]]>
	</select>
</mapper>
```

- DAO, service, serviceImpl에 회원 조회용 함수 추가
  - `com.invitation.biz.member.user.impl` 패키지 생성
```java
// UserMemberDAOMybatis.java

package com.invitation.biz.member.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.member.user.UserMemberListVO;

@Repository
public class UserMemberDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<UserMemberListVO> getMemberList() {
		return mybatis.selectList("MemberUserDAO.getMemberList");
	}
}
```

```java
// UserMemberService.java

package com.invitation.biz.member.user;

import java.util.List;

public interface UserMemberService {

	List<UserMemberListVO> getMemberList();
}
```

```java
// UserMemberServiceImpl.java

package com.invitation.biz.member.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberService;

@Service("userMember")
public class UserMemberServiceImpl implements UserMemberService {

	@Autowired
	private UserMemberDAOMybatis userMemberDAO;
	
	@Override
	public List<UserMemberListVO> getMemberList() {
		return userMemberDAO.getMemberList();
	}
}
```

- Controller에 회원 조회용 함수 추가
  - 기존에 테스트로 작성했던 회원 조회 함수는 주석 처리
```java
// MemberController.java

		// ~~~
		return "member/memberList";
	}
	
	@RequestMapping(value="/getMemberList", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMemberList() {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<UserMemberListVO> resUserMemberList = null;
		
		LOGGER.info("getMemberList");
		try {
			List<UserMemberListVO> userMemberList = userMemberService.getMemberList();
			
			resFlag = true;
			resUserMemberList = userMemberList;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 목록 조회에 실패앴습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("list", resUserMemberList);
		}
		
		return result;
	}
}
```

- 회원 관리 페이지 수정
  - dataTable.js의 selectbox 쓸려면 js, css 폴더 모두 포함 필요!!
```jsp
<!--memberList.jsp -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>회원 관리</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>회원 관리</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table id="tableMemberList" class="table table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th></th>
											<th>아이디</th>
											<th>이름</th>
											<th>핸드폰</th>
											<th>게시상태</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
```

- 회원 관리 화면 js 수정
```js
// memberList.js

	// ~~~
	setActiveSidebar();
	
	getMemberList();
});

function getMemberList() {
	$.ajax({
		url : "/admin/member/getMemberList",
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			renderMemberList(result.list);
		}
	});
};

function renderMemberList(data) {
	// - 맨 앞에 체크박스 컬럼도 추가해야함
	// 페이징 처리 구현
	// 검색 기능 구현
	// 오름/내림차순 정렬 할 수 있게도 해야하고
	// 공통으로 할 수 있게 유틸로 빼고
	$("#tableMemberList").DataTable({
		lengthChange : false,
		searching : false,
		ordering : false,
		autoWidth : false,
		pagingType: 'full_numbers',
	    language: {
	        paginate: {
	            first:    '«',
	            previous: '‹',
	            next:     '›',
	            last:     '»'
	        },
	        aria: {
	            paginate: {
	                first:    'First',
	                previous: 'Previous',
	                next:     'Next',
	                last:     'Last'
	            }
	        }
	    },
	    info : true,
	    infoCallback : function(settings, start, end, max, total, pre) {
	    	return "총 겸색 결과 : " + 5;
	    },
		data : data,
		
		columnDefs : [{
			targets : 0,
			defaultContent : '',
			data : null,
			className : 'select-checkbox'
		}, {
			targets : 1,
			data : 'id'
		}, {
			targets : 2,
			data : 'name'
		}, {
			targets : 3,
			data : 'phone'
		}, {
			targets : 4,
			data : function(row, type, val, meta) {
				var statusSee = '';
				
				if(row.statusSee == 'Y') {
					statusSee = '게시';
				} else if(row.statusSee == 'N') {
					statusSee = '비게시'; 
				} else {
					statusSee = '-';
				}
				
				return statusSee;
			}
		}],
		select : {
			style : 'os',
			selector : 'th:first-child, td:first-child'
		}
	});
}
```


---

## 10 - 1. 회원 관리 화면 구현 - 페이징 및 검색 구현
- 페이징 패키지 생성
  - `com.invitation.biz.common.paging` 패키지 생성

- 목록 정보를 가진 Criteria 클래스 작성
```java
// Criteria.java

package com.invitation.biz.common.paging;

public class Criteria {

	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 5;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
```

- 목록 하단의 페이징 버튼 정보를 가진 PageMaker 클래스 작성
```java
// PageMaker.java

package com.invitation.biz.common.paging;

public class PageMaker {

	private int totalCount;
	private int displayPageNum = 3;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private Criteria cri;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", displayPageNum=" + displayPageNum + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", cri=" + cri + "]";
	}
}
```

- 고객 조회 쿼리에 검색, 페이징 기능의 쿼리 추가
  - 페이징 처리와 총 검색 결과 개수 정보를 얻기 위한 쿼리 추가
```xml
<!-- MemberUseres-mapping.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="MemberUserDAO">
	<select id="getMemberList" resultType="userMemberList">
		SELECT
			U.ID
			, U.NAME
			, U.PHONE
			, (
				IFNULL((
					SELECT
						IL.VISABLE
					FROM
						INVITATION_LIST IL
					WHERE
						IL.ID = U.ID
						AND NOW() <![CDATA[>=]]> IL.DATE_BEGIN
						AND NOW() <![CDATA[<=]]> IL.DATE_END)
				, 'X')
			) AS STATUSSEE
		FROM
			USERES U
		WHERE
			U.DELETEFLAG <![CDATA[<>]]> 'Y'
		<if test="condition == 'id'">
			AND U.ID LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'name'">
			AND U.NAME LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'phone'">
			AND U.PHONE LIKE CONCAT('%', #{keyword}, '%')
		</if>
		LIMIT #{pageStart}, #{perPageNum};
	</select>
	
	<select id="getMemberListCount" resultType="int">
		SELECT
			COUNT(
				IFNULL((
					SELECT
						IL.VISABLE
					FROM
						INVITATION_LIST IL
					WHERE
						IL.ID = U.ID
						AND NOW() <![CDATA[>=]]> IL.DATE_BEGIN
						AND NOW() <![CDATA[<=]]> IL.DATE_END)
				, 'X')
			)
		FROM
			USERES U
		WHERE
			U.DELETEFLAG <![CDATA[<>]]> 'Y'
		<if test="condition == 'id'">
			AND U.ID LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'name'">
			AND U.NAME LIKE CONCAT('%', #{keyword}, '%')
		</if>
		<if test="condition == 'phone'">
		</if>
			AND U.PHONE LIKE CONCAT('%', #{keyword}, '%')
		;
	</select>
</mapper>
```

- 검색 조건으로 사용할 정보 파라미터로 넘겨주기
```java
// UserMemberDAOMybatis.java

	// ~~~
	private SqlSessionTemplate mybatis;
	
	public List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("pageStart",  cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		param.put("condition", condition);
		param.put("keyword", keyword);
		
		return mybatis.selectList("MemberUserDAO.getMemberList", param);
	}
	
	public int getMemberListCount(String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("condition", condition);
		param.put("keyword", keyword);
		
		return mybatis.selectOne("MemberUserDAO.getMemberListCount", param);
	}
}
```

```java
// UserMemberService.java

// ~~~
public interface UserMemberService {

	List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword);
	
	int getMemberListCount(String condition, String keyword);
}
```

```java
// UserMemberServiceImpl.java

	// ~~~
	@Override
	public List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword) {
		return userMemberDAO.getMemberList(cri, condition, keyword);
	}

	@Override
	public int getMemberListCount(String condition, String keyword) {
		return userMemberDAO.getMemberListCount(condition, keyword);
	}
}
```

```java
// MemberController.java

	// ~~~
	@RequestMapping(value="/getMemberList", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMemberList(Criteria cri,
			@RequestParam(value="searchCondition", defaultValue="", required=false) String condition,
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword
			) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<UserMemberListVO> resUserMemberList = null;
		PageMaker resPageMaker = null;
		
		LOGGER.info("getMemberList");
		try {
			List<UserMemberListVO> userMemberList = userMemberService.getMemberList(cri, condition, keyword);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(userMemberService.getMemberListCount(condition, keyword));
			
			resFlag = true;
			resUserMemberList = userMemberList;
			resPageMaker = pageMaker;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 목록 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("list", resUserMemberList);
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
}
```

- postman으로 기능 테스트
  - `http://localhost:8080/admin/member/getMemberList?searchCondition=phone&searchKeyword=3333`
  - 결과값 : 
```json
{
    "resFlag": true,
    "resMessage": "",
    "list": [
        {
            "id": "test1",
            "name": "일유저",
            "phone": "01022223333",
            "statusSee": "X"
        },
        {
            "id": "test2",
            "name": "이유저",
            "phone": "01033334444",
            "statusSee": "N"
        }
    ],
    "pageMaker": {
        "totalCount": 2,
        "displayPageNum": 3,
        "startPage": 1,
        "endPage": 1,
        "prev": false,
        "next": false,
        "cri": {
            "page": 1,
            "perPageNum": 5,
            "pageStart": 0
        }
    }
}
```

- 회원 관리 화면 수정
  - 회원 추가, 삭제, 검색, 페이징 영역 추가
```jsp
<!-- memberList.jsp-->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>회원 관리</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>회원 관리</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card dataTables_wrapper">
							<div class="card-header">
								<!-- <h3 class="card-title">추가/삭제/검색 영역</h3> -->
								<div class="row">
									<div class="col-md-6">
										<button type="button" class="btn btn-danger">삭제</button>
										<button type="button" class="btn btn-primary">추가</button>
									</div>
									<div class="col-md-6">
										<div class="dataTables_filter">
											<select id="selectCondition">
												<option value="id">아이디</option>
												<option value="name">이름</option>
												<option value="phone">핸드폰</option>
											</select>
											<input type="text" id="inputKeyword" />
											<button type="button" class="btn btn-default" id="btnSearch">검색</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table id="tableMemberList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;"></th>
													<th style="width:25%;">아이디</th>
													<th style="width:20%;">이름</th>
													<th style="width:30%;">핸드폰</th>
													<th style="width:20%;">게시상태</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
										
										<!-- <iframe src="http://www.naver.com">
											iframe를 지원하지 않는 브라우저입니다.
										</iframe> -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
```

- 회원 관리 화면 및 유틸 js 수정
  - DataTable, Paging 관련 함수 공통함수로 작성
```js
// util.js

function utilGoToMain() {
	// ~~~
};

function utilDataTable(targetId, addOption, total) {
	var option = {
		lengthChange : false,
		searching : false,
		ordering : false,
		autoWidth : false,
		language : {emptyTable : "조회 결과가 없습니다."},
		paging : false,
	    info : true,
	    infoCallback : function(settings, start, end, max, total_, pre) {
	    	return "총 겸색 결과 : " + total;
	    },
		data : [],
		columnDefs : []	
	};
	
	option = $.extend(true, option, addOption);
	
	if(total == undefined || typeof total != "number") {
		console.warn("utilDataTable >>>>> total");
		return;
	} else if(!(Array.isArray(option.data))) {
		console.warn("utilDataTable >>>>> data");
		return;
	} else if(!(Array.isArray(option.columnDefs)) && option.columnDefs.length < 1) {
		console.warn("utilDataTable >>>>> columnDefs");
		return;
	} else if(targetId == undefined || document.getElementById(targetId) == null) {
		console.warn("utilDataTable >>>>> targetId");
		return;
	}
	
	$("#" + targetId).DataTable(option);
};

function utilDataTableDestroy(targetId) {
	$("#" + targetId).DataTable().destroy();
};

function utilDataTablePaging(idTarget, idDataTable, pageMaker) {
	if(idTarget == undefined) {
		console.warn("utilDataTable >>>>> idTarget");
		return;
	} else if(idDataTable == undefined) {
		console.warn("utilDataTable >>>>> idDataTable");
		return;
	} else if(pageMaker == undefined || typeof pageMaker != "object") {
		console.warn("utilDataTable >>>>> pageMaker");
		return;
	}
	
	var $idTarget = $("#" + idTarget),
		pagePrevious = (pageMaker.startPage - 1) < 1 ? 1 : pageMaker.startPage - 1,
		pageLast = Math.ceil(pageMaker.totalCount/pageMaker.cri.perPageNum),
		pageNext = (pageMaker.endPage + 1) > pageLast ? pageLast : pageMaker.endPage + 1
		pageList = [], 
		liPage = [],
		liFirstPrevious = [
			{
				"class" : "paginate_button page-item first disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "First",
					"data-dt-idx" : "first",
					"class" : "page-link aPaging",
					"tabindex" : "1",
					"text" : "<<"
				}
			},
			{
				"class" : "paginate_button page-item previous disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Previous",
					"data-dt-idx" : "previous",
					"class" : "page-link aPaging",
					"tabindex" : "",	// previous버튼의 페이지는 유동적으로 해야함
					"text" : "<"
				}
			}
		], liNextLast = [
			{
				"class" : "paginate_button page-item next disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Next",
					"data-dt-idx" : "next",
					"class" : "page-link aPaging",
					"tabindex" : "",	// next버튼의 페이지는 유동적으로 해야함
					"text" : ">"
				}
			},
			{
				"class" : "paginate_button page-item last disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Last",
					"data-dt-idx" : "last",
					"class" : "page-link aPaging",
					"tabindex" : Math.ceil(pageMaker.totalCount/pageMaker.cri.perPageNum),
					"text" : ">>"
				}
			}
		];
	
	for(var index1 = pageMaker.startPage ; index1<= pageMaker.endPage ; index1++) {
		var active = pageMaker.cri.page == index1 ? " active" : "";
		
		liPage.push({
			"class" : "paginate_button page-item" + active,
			"child_a" : {
				"href" : "#",
				"aria-controls" : idDataTable,
				"aria-label" : "Page",
				"data-dt-idx" : index1,
				"class" : "page-link aPaging",
				"tabindex" : index1,
				"text" : index1
			}
		});
	}
	
	$idTarget.find("li:first").siblings().remove();
	$idTarget.find("li:first").show();
	
	pageList = liFirstPrevious.concat(liPage, liNextLast);
	pageList.forEach(function(item){
		var $liPage = $("#liRecord").clone();
		
		$liPage.attr({
			"class" : item["class"],
			"id" : item["id"]
		}).find("a").attr({
			"href" : item["child_a"]["href"],
			"aria-controls" : item["child_a"]["aria-controls"],
			"aria-label" : item["child_a"]["aria-label"],
			"data-dt-idx" : item["child_a"]["data-dt-idx"],
			"tabindex" : item["child_a"]["tabindex"],
			"class" : item["child_a"]["class"]
		}).text(item["child_a"]["text"]);
		
		$idTarget.find("ul").append($liPage);
	});
	
	$idTarget.find("li:first").hide();
	
	if(pageMaker.prev) {
		$idTarget.find(".first").removeClass("disabled");
		$idTarget.find(".previous").removeClass("disabled");
	} else {
		if(pageMaker.cri.page == pageMaker.startPage) {
			$idTarget.find(".first").addClass("disabled");
			$idTarget.find(".previous").addClass("disabled");
		} else {
			$idTarget.find(".first").removeClass("disabled");
			$idTarget.find(".previous").removeClass("disabled");
		}
	}
	
	if(pageMaker.next) {
		$idTarget.find(".next").removeClass("disabled");
		$idTarget.find(".last").removeClass("disabled");
	} else {
		if(pageMaker.cri.page == pageMaker.endPage) {
			$idTarget.find(".next").addClass("disabled");
			$idTarget.find(".last").addClass("disabled");
		} else {
			$idTarget.find(".next").removeClass("disabled");
			$idTarget.find(".last").removeClass("disabled");
		}
	}
};
```

```js
// memberList.js

/**
 * 
 */

console.log("########## memberList.js ##########");

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) + 1;
		} else if($this.data("dt-idx") == "previous") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) - 1;
		}
		
		console.log(tabindex);
		utilDataTableDestroy("tableMemberList");
		getMemberList(Number(tabindex));
	});
});

function getMemberList(pageItem) {
	var requestParam = {
			page : pageItem,
			searchCondition : "",
			searchKeyword : $("#inputKeyword").val().trim()
	};
	
	if(requestParam.searchKeyword != "") {
		requestParam.searchCondition = $("#selectCondition").val();
	}
	
	$.ajax({
		url : "/admin/member/getMemberList?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			// 회원 선택 시 수정 팝업 띄우는 이벤트 추가 필요 (수정 시 필요한 정보는 어디서든 읽어 지겠지 아니면 data 태그라두)
			var option = {
				data : result.list,
				columnDefs : [{
					targets : 0,
					defaultContent : '',
					data : null,
					className : 'select-checkbox'
				}, {
					targets : 1,
					data : 'id'
				}, {
					targets : 2,
					data : 'name'
				}, {
					targets : 3,
					data : 'phone'
				}, {
					targets : 4,
					data : function(row, type, val, meta) {
						var statusSee = '';
						
						if(row.statusSee == 'Y') {
							statusSee = '게시';
						} else if(row.statusSee == 'N') {
							statusSee = '비게시'; 
						} else {
							statusSee = '-';
						}
						
						return statusSee;
					}
				}],
				select : {
					style : 'os',
					selector : 'td:first-child'
				},
				rowId : function(row) {
					return row.id;
				}
			};
			
			utilDataTable("tableMemberList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableMemberList", result.pageMaker);
		}
	});
};
```


---

## 10 - 2. 회원 관리 화면 구현 - 아이디 중복 확인 및 회원 추가 기능 구현
- 쿼리 추가
```xml
<!-- MemberUseres-mapping.xml -->

			<!-- ~~~ -->
			AND U.PHONE LIKE CONCAT('%', #{keyword}, '%')
		</if>
		;
	</select>
	
	<select id="getOverlapCheck" resultType="int">
		SELECT
			COUNT(ID)
		FROM
			USERES
		WHERE
			ID = #{id}
		;
	</select>
	
	<insert id="registerMember">
		INSERT INTO
			USERES 
		(
			ID
			, PASSWORD
			, NAME
			, PHONE
		)
		VALUES (
			#{id}
			, #{password}
			, #{name}
			, #{phone}
		);
	</insert>
</mapper>
```

- DAO파일 수정
```java
// UserMemberDAOMybatis.java

		// ~~~
		return mybatis.selectOne("MemberUserDAO.getMemberListCount", param);
	}
	
	public int getOverlapCheck(String id) {
		return mybatis.selectOne("MemberUserDAO.getOverlapCheck", id);
	}
	
	public void registerMember(UserMemberVO vo) {
		mybatis.insert("MemberUserDAO.registerMember", vo);
	}
}
```

- service파일 수정
```java
// UserMemberService.java

	// ~~~
	int getMemberListCount(String condition, String keyword);
	
	int getOverlapCheck(String id);
	
	void registerMember(UserMemberVO vo);
}
```

- serviceImpl파일 수정
```java
// UserMemberServiceImpl.java

		// ~~~
		return userMemberDAO.getMemberListCount(condition, keyword);
	}
	
	@Override
	public int getOverlapCheck(String id) {
		return userMemberDAO.getOverlapCheck(id);
	}
	
	@Override
	public void registerMember(UserMemberVO vo) {
		userMemberDAO.registerMember(vo);
	}
}
```

- controller파일 수정
```java
// MemberController.java

			// ~~~
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
	
//	@RequestMapping(value="/getOverlapCheck", method=RequestMethod.GET)
	@GetMapping(value="/getOverlapCheck")
	@ResponseBody
	public Map<String, Object> getOverlapCheck(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resOverlapCheckedId = "";
		String resMessage = "";
		int resOverlapCheck = 0;
		
		LOGGER.info("getOverlapCheck");
		try {
			resOverlapCheck = userMemberService.getOverlapCheck(id);
			
			if(resOverlapCheck == 0) {
				resFlag = true;
				resOverlapCheckedId = id;
				resMessage = "사용 가능한 아이디입니다.";
			} else {
				resMessage = "사용중인 아이디입니다.";
			}
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "아이디 중복확인에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resOverlapCheckedId", resOverlapCheckedId);
			result.put("resMessage",  resMessage);
		}
		
		return result;
	}
	
	@PostMapping(value="/registerMember", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> registerMember(@RequestBody UserMemberVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("registerMember");
		try {
			userMemberService.registerMember(vo);
			
			resFlag = true;
			resMessage = "회원 등록이 완료되었습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 등록에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage",  resMessage);
		}
		
		return result;
	}
}
```

- jsp파일 수정
```jsp
<!-- memberList.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>회원 관리</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>회원 관리</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card dataTables_wrapper">
							<div class="card-header">
								<!-- <h3 class="card-title">추가/삭제/검색 영역</h3> -->
								<div class="row">
									<div class="col-md-6">
										<button type="button" class="btn btn-danger">삭제</button>
										<button type="button" class="btn btn-primary" id="btnMemberRegister" data-toggle="modal" data-target="#modal-memberRegister">추가</button>
									</div>
									<div class="col-md-6">
										<div class="dataTables_filter">
											<select id="selectCondition">
												<option value="id">아이디</option>
												<option value="name">이름</option>
												<option value="phone">핸드폰</option>
											</select>
											<input type="text" id="inputKeyword" />
											<button type="button" class="btn btn-default" id="btnSearch">검색</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table id="tableMemberList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;"></th>
													<th style="width:25%;">아이디</th>
													<th style="width:20%;">이름</th>
													<th style="width:30%;">핸드폰</th>
													<th style="width:20%;">게시상태</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<div class="modal fade" id="modal-memberRegister">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 추가</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group">
										<label>아이디</label> 
									</div>
									<div class="input-group mb-3">
										<input type="text" class="form-control resetInput" id="inputRegisterId" maxlength="20">
										<span class="input-group-append">
											<button type="button" class="btn btn-info btn-flat" id="btnOverlapCheck">중복확인</button>
										</span>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterPassword" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterName" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="number" class="form-control resetInput" id="inputRegisterPhone" maxlength="11">
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnRegister">추가</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<div class="modal fade" id="modal-memberModify">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 상세 및 수정</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group mb-3">
										<label>아이디</label> 
										<input type="text" class="form-control resetInput" id="" disabled>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="text" class="form-control resetInput" id="">
									</div>
									<div class="form-group mb-3">
										<label>가입일</label> 
										<input type="text" class="form-control resetInput" id="" disabled>
									</div>
									<div class="form-group mb-3">
										<label>청첩장 게시일</label> 
										<input type="text" class="form-control resetInput" id="" disabled>
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary">수정</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
```

- js파일 수정
```js
// memberList.js

/**
 * 
 */

console.log("########## memberList.js ##########");

var modifyId = "",
	isOverlapCheck = false,
	overlapCheckedId = ""
	isSuccess = false;

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#btnOverlapCheck").on("click", function(){
		var id = $("#inputRegisterId").val();
		
		if(id == "") {
			alert("ID를 입력해주세요.");
		} else if(id.length > 20) {
			alert("ID는 20자 이내로 입력해주세요.");
		} else {
			overlapCheck(id);
		}
	});
	
	$("#modal-memberRegister").on("hide.bs.modal", function(e){
		$(".resetInput").val("");
		
		if(isSuccess) {
			utilDataTableDestroy("tableMemberList");
			getMemberList(1);
		}
	})
	$("#btnMemberRegister").on("click", function(){
		$("#modal-memberRegister").modal("show");
	});
	
	$("#btnRegister").on("click", function() {
		if(validateInfo()) {
			registerMember();
		}
	});
	
	$("#modal-memberModify").on("show.bs.modal", function (e) {
//		getMemberInfo();
		console.log(modifyId);
	});
	$("#modal-memberModify").on("hide.bs.modal", function (e) {
		modifyId = "";
		
		console.log(modifyId);
	});
	$("#tableMemberList").on("dblclick", "tr", function(){
		modifyId = $(this).attr("id");
		
		$("#modal-memberModify").modal("show");
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) + 1;
		} else if($this.data("dt-idx") == "previous") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) - 1;
		}
		
		utilDataTableDestroy("tableMemberList");
		getMemberList(Number(tabindex));
	});
});

function getMemberList(pageItem) {
	var requestParam = {
			page : pageItem,
			searchCondition : "",
			searchKeyword : $("#inputKeyword").val().trim()
	};
	
	if(requestParam.searchKeyword != "") {
		requestParam.searchCondition = $("#selectCondition").val();
	}
	
	$.ajax({
		url : "/admin/member/getMemberList?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			var option = {
				data : result.list,
				columnDefs : [{
					targets : 0,
					defaultContent : '',
					data : null,
					className : 'select-checkbox'
				}, {
					targets : 1,
					data : 'id'
				}, {
					targets : 2,
					data : 'name'
				}, {
					targets : 3,
					data : 'phone'
				}, {
					targets : 4,
					data : function(row, type, val, meta) {
						var statusSee = '';
						
						if(row.statusSee == 'Y') {
							statusSee = '게시';
						} else if(row.statusSee == 'N') {
							statusSee = '비게시'; 
						} else {
							statusSee = '-';
						}
						
						return statusSee;
					}
				}],
				select : {
					style : 'os',
					selector : 'td:first-child'
				},
				rowId : function(row) {
					return row.id;
				}
			};
			
			utilDataTable("tableMemberList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableMemberList", result.pageMaker);
		}
	});
};

function overlapCheck(id) {
	$.ajax({
		url : "/admin/member/getOverlapCheck?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			isOverlapCheck = false;
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag || (result.resOverlapCheckedId != "")) {
				isOverlapCheck = true;
				overlapCheckedId = result.resOverlapCheckedId;
			} else {
				isOverlapCheck = false;
			}
			
			alert(result.resMessage);
		}
	});
};

function getMemberInfo() {
	$.ajax({
		url : "/admin/member/getMemberInfo?" + $.param({memberId : modifyId}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			
		}
	});
};

function validateInfo() {
	if($("#inputRegisterId").val() == "") {
		alert("아이디를 입력해주세요");
		isOverlapCheck = false;
		return false;
	} else if(isOverlapCheck == false) {
		alert("아이디 중복확인을 해주세요");
		return false;
	} else if($("#inputRegisterPassword").val() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#inputRegisterName").val() == "") {
		alert("이름을 입력해주세요");
		return false;
	} else if($("#inputRegisterPhone").val() == "") {
		alert("전화번호를 입력해주세요.");
		return false;
	} else {
		return true;
	}
};

function registerMember() {
	if(overlapCheckedId != $("#inputRegisterId").val()) {
		overlapCheckedId = "";
		isOverlapCheck = false;
		
		alert("아이디가 변경되었습니다. 아이디 중복확인을 해주세요.");
		return false;
	}
	
	var data = {
		id : overlapCheckedId,
		password : $("#inputRegisterPassword").val(),
		name : $("#inputRegisterName").val(),
		phone : $("#inputRegisterPhone").val()
	};
	
	$.ajax({
		url : "/admin/member/registerMember",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json",
		mimeType : "application/json",
		error : function(xhr, status, msg) {
			isOverlapCheck = true;
			isSuccess = false;
			
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				isOverlapCheck = false;
				overlapCheckedId = "";
				isSuccess = true;
				
				alert(result.resMessage);
				$("#modal-memberRegister").modal("hide");
			} else {
				isOverlapCheck = true;
				isSuccess = false;
				
				alert(result.resMessage);
			}
		}
	})
};
```

---

## 10 - 3. 회원 관리 화면 구현 - 회원 상세 조회 기능 구현
- VO 추가
```java
// UserMemberInfoVO.java

package com.invitation.biz.member.user;

public class UserMemberInfoVO {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String registerDate;
	private String latestInvitationBegin;
	private String latestInvitationEnd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLatestInvitationBegin() {
		return latestInvitationBegin;
	}
	public void setLatestInvitationBegin(String latestInvitationBegin) {
		this.latestInvitationBegin = latestInvitationBegin;
	}
	public String getLatestInvitationEnd() {
		return latestInvitationEnd;
	}
	public void setLatestInvitationEnd(String latestInvitationEnd) {
		this.latestInvitationEnd = latestInvitationEnd;
	}
	
	@Override
	public String toString() {
		return "UserMemberInfoVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", registerDate=" + registerDate + ", latestInvitationBegin=" + latestInvitationBegin
				+ ", latestInvitationEnd=" + latestInvitationEnd + "]";
	}
}
```

- VO alias 추가
```xml
<!-- sql-map-config.xml -->

		<!-- ~~~ -->
		<typeAlias alias="userMemberList" type="com.invitation.biz.member.user.UserMemberListVO"></typeAlias> 
		<typeAlias alias="userMemberInfo" type="com.invitation.biz.member.user.UserMemberInfoVO"></typeAlias> 
	</typeAliases>
	
	<mappers>
		<mapper resource="mappings/AdminUseres-mapping.xml" />
		<mapper resource="mappings/MemberUseres-mapping.xml" />
	</mappers>
</configuration>
```

- 쿼리 추가
```xml
<!-- MemberUseres-mapping.xml -->

	<!-- ~~~ -->
	</insert>
	
	<select id="getMemberInfo" resultType="userMemberInfo">
		SELECT
			U.ID
			, U.PASSWORD
			, U.NAME
			, U.PHONE
			, DATE_FORMAT(U.DATETIME_REGISTER, '%Y-%m-%d') AS REGISTERDATE
			, ( 
				SELECT
					IL_A.DATE_BEGIN
				FROM 
					INVITATION_LIST IL_A
				WHERE 
					IL_A.DATE_BEGIN = (
						SELECT
							MAX(IL_B.DATE_BEGIN)
						FROM
							INVITATION_LIST IL_B
						WHERE
							IL_B.ID = #{id}
					)
			) AS LATESTINVITATIONBEGIN
			, ( 
				SELECT
					IL_A.DATE_END
				FROM 
					INVITATION_LIST IL_A
				WHERE 
					IL_A.DATE_BEGIN = (
						SELECT
							MAX(IL_B.DATE_BEGIN)
						FROM
							INVITATION_LIST IL_B
						WHERE
							IL_B.ID = #{id}
					)
			) AS LATESTINVITATIONEND
		FROM
			USERES U
		WHERE U.ID = #{id}
		;
	</select>
</mapper>
```

- DAO 추가
```java
// UserMemberDAOMybatis.java

		// ~~~
		mybatis.insert("MemberUserDAO.registerMember", vo);
	}
	
	public UserMemberInfoVO getMemberInfo(String id) {
		return mybatis.selectOne("MemberUserDAO.getMemberInfo", id);
	}
}
```

- serivce 추가
```java
// UserMemberService.java

	// ~~~
	void registerMember(UserMemberVO vo);
	
	UserMemberInfoVO getMemberInfo(String id);
}
```

- serviceImpl 추가
```java
// UserMemberServiceImpl.java

		// ~~~
		userMemberDAO.registerMember(vo);
	}
	
	@Override
	public UserMemberInfoVO getMemberInfo(String id) {
		return userMemberDAO.getMemberInfo(id);
	}
}
```

- controller 추가
```java
// MemberController.java

			// ~~~
			result.put("resMessage",  resMessage);
		}
		
		return result;
	}
	
	@GetMapping(value="/getMemberInfo")
	@ResponseBody
	public Map<String, Object> getMemberInfo(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		UserMemberInfoVO resMemberInfo = null;
		
		LOGGER.info("getMemberInfo");
		try {
			resMemberInfo = userMemberService.getMemberInfo(id);
			
			if(resMemberInfo == null) {
				throw new CommonException("회원정보 불일치!!");
			}
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "일치하는 회원이 없습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = true;
			resMessage = "회원 상세조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resMemberInfo", resMemberInfo);
		}
		
		return result;
	}
}
```

- jsp 수정
```jsp
<!-- memberList.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>회원 관리</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>회원 관리</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card dataTables_wrapper">
							<div class="card-header">
								<!-- <h3 class="card-title">추가/삭제/검색 영역</h3> -->
								<div class="row">
									<div class="col-md-6">
										<button type="button" class="btn btn-danger">삭제</button>
										<button type="button" class="btn btn-primary" id="btnMemberRegister" data-toggle="modal" data-target="#modal-memberRegister">추가</button>
									</div>
									<div class="col-md-6">
										<div class="dataTables_filter">
											<select id="selectCondition">
												<option value="id">아이디</option>
												<option value="name">이름</option>
												<option value="phone">핸드폰</option>
											</select>
											<input type="text" id="inputKeyword" />
											<button type="button" class="btn btn-default" id="btnSearch">검색</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table id="tableMemberList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;"></th>
													<th style="width:25%;">아이디</th>
													<th style="width:20%;">이름</th>
													<th style="width:30%;">핸드폰</th>
													<th style="width:20%;">게시상태</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<div class="modal fade" id="modal-memberRegister">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 추가</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group">
										<label>아이디</label> 
									</div>
									<div class="input-group mb-3">
										<input type="text" class="form-control resetInput" id="inputRegisterId" maxlength="20">
										<span class="input-group-append">
											<button type="button" class="btn btn-info btn-flat" id="btnOverlapCheck">중복확인</button>
										</span>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterPassword" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterName" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="number" class="form-control resetInput" id="inputRegisterPhone" maxlength="11">
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnRegist">추가</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<div class="modal fade" id="modal-memberModify">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 상세 및 수정</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group mb-3">
										<label>아이디</label> 
										<input type="text" class="form-control resetInput" id="inputModifyId" disabled>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="inputModifyPassword" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="inputModifyName" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="text" class="form-control resetInput" id="inputModifyPhone" maxlength="11">
									</div>
									<div class="form-group mb-3">
										<label>가입일</label> 
										<input type="text" class="form-control resetInput" id="inputModifyRegDate" disabled>
									</div>
									<div class="form-group mb-3">
										<label>청첩장 게시일</label> 
										<input type="text" class="form-control resetInput" id="inputModifyLatestInvitation" disabled>
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnModify">수정</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
```

- js 수정
```js
// memberList.js

/**
 * 
 */

console.log("########## memberList.js ##########");

var modifyTargetId = "",
	isOverlapCheck = false,
	overlapCheckedId = ""
	isSuccess = false;

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#btnOverlapCheck").on("click", function(){
		var id = $("#inputRegisterId").val();
		
		if(id == "") {
			alert("ID를 입력해주세요.");
		} else if(id.length > 20) {
			alert("ID는 20자 이내로 입력해주세요.");
		} else {
			overlapCheck(id);
		}
	});
	
	$("#modal-memberRegister").on("hide.bs.modal", function(e){
		$(".resetInput").val("");
		
		if(isSuccess) {
			isSuccess = false;
			
			utilDataTableDestroy("tableMemberList");
			getMemberList(1);
		}
	})
	$("#btnMemberRegister").on("click", function(){
		$("#modal-memberRegister").modal("show");
	});
	
	$("#btnRegist").on("click", function() {
		if(validateRegistInfo()) {
			registerMember();
		}
	});
	
	$("#modal-memberModify").on("show.bs.modal", function (e) {
		console.log("target ID : ", modifyTargetId);
		
		getMemberInfo();
	});
	$("#modal-memberModify").on("hide.bs.modal", function (e) {
		modifyTargetId = "";
		
		console.log(modifyTargetId);
		
		$("#btnModify").removeData("resMemberInfo");
		$(".resetInput").val("");
	});
	$("#tableMemberList").on("dblclick", "tr", function(){
		modifyTargetId = $(this).attr("id");
		
		$("#modal-memberModify").modal("show");
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) + 1;
		} else if($this.data("dt-idx") == "previous") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) - 1;
		}
		
		utilDataTableDestroy("tableMemberList");
		getMemberList(Number(tabindex));
	});
});

function getMemberList(pageItem) {
	var requestParam = {
			page : pageItem,
			searchCondition : "",
			searchKeyword : $("#inputKeyword").val().trim()
	};
	
	if(requestParam.searchKeyword != "") {
		requestParam.searchCondition = $("#selectCondition").val();
	}
	
	$.ajax({
		url : "/admin/member/getMemberList?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			var option = {
				data : result.list,
				columnDefs : [{
					targets : 0,
					defaultContent : '',
					data : null,
					className : 'select-checkbox'
				}, {
					targets : 1,
					data : 'id'
				}, {
					targets : 2,
					data : 'name'
				}, {
					targets : 3,
					data : 'phone'
				}, {
					targets : 4,
					data : function(row, type, val, meta) {
						var statusSee = '';
						
						if(row.statusSee == 'Y') {
							statusSee = '게시';
						} else if(row.statusSee == 'N') {
							statusSee = '비게시'; 
						} else {
							statusSee = '-';
						}
						
						return statusSee;
					}
				}],
				select : {
					style : 'os',
					selector : 'td:first-child'
				},
				rowId : function(row) {
					return row.id;
				}
			};
			
			utilDataTable("tableMemberList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableMemberList", result.pageMaker);
		}
	});
};

function overlapCheck(id) {
	$.ajax({
		url : "/admin/member/getOverlapCheck?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			isOverlapCheck = false;
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag || (result.resOverlapCheckedId != "")) {
				isOverlapCheck = true;
				overlapCheckedId = result.resOverlapCheckedId;
			} else {
				isOverlapCheck = false;
			}
			
			alert(result.resMessage);
		}
	});
};

function validateRegistInfo() {
	if($("#inputRegisterId").val() == "") {
		alert("아이디를 입력해주세요");
		isOverlapCheck = false;
		return false;
	} else if(isOverlapCheck == false) {
		alert("아이디 중복확인을 해주세요");
		return false;
	} else if($("#inputRegisterPassword").val() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#inputRegisterName").val() == "") {
		alert("이름을 입력해주세요");
		return false;
	} else if($("#inputRegisterPhone").val() == "") {
		alert("전화번호를 입력해주세요.");
		return false;
	} else {
		return true;
	}
};

function registerMember() {
	if(overlapCheckedId != $("#inputRegisterId").val()) {
		overlapCheckedId = "";
		isOverlapCheck = false;
		
		alert("아이디가 변경되었습니다. 아이디 중복확인을 해주세요.");
		return false;
	}
	
	var data = {
		id : overlapCheckedId,
		password : $("#inputRegisterPassword").val(),
		name : $("#inputRegisterName").val(),
		phone : $("#inputRegisterPhone").val()
	};
	
	$.ajax({
		url : "/admin/member/registerMember",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json",
		mimeType : "application/json",
		error : function(xhr, status, msg) {
			isOverlapCheck = true;
			isSuccess = false;
			
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				isOverlapCheck = false;
				overlapCheckedId = "";
				isSuccess = true;
				
				alert(result.resMessage);
				$("#modal-memberRegister").modal("hide");
			} else {
				isOverlapCheck = true;
				isSuccess = false;
				
				alert(result.resMessage);
			}
		}
	})
};

function getMemberInfo() {
	$.ajax({
		url : "/admin/member/getMemberInfo?" + $.param({id : modifyTargetId}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			$("#btnModify").data("resMemberInfo", result.resMemberInfo);
			$("#inputModifyId").val(result.resMemberInfo.id);
			$("#inputModifyPassword").val(result.resMemberInfo.password);
			$("#inputModifyName").val(result.resMemberInfo.name);
			$("#inputModifyPhone").val(result.resMemberInfo.phone);
			$("#inputModifyRegDate").val(result.resMemberInfo.registerDate);
			if(result.resMemberInfo.latestInvitationBegin != null && result.resMemberInfo.latestInvitationEnd != null) {
				$("#inputModifyLatestInvitation").val(result.resMemberInfo.latestInvitationBegin.substr(0,4)
						+ result.resMemberInfo.latestInvitationBegin.substr(4,2)
						+ result.resMemberInfo.latestInvitationBegin.substr(6,2)
						+ " ~ " 
						+ result.resMemberInfo.latestInvitationEnd.substr(0,4)
						+ result.resMemberInfo.latestInvitationEnd.substr(4,2)
						+ result.resMemberInfo.latestInvitationEnd.substr(6,2));
			}
		}
	});
};
```

---

## 11-1 청첩장 화면 구성

- adminlte3 프레임워크 customize css 파일 추가
  - `src/main/webapp/resources/css/customizeBootstrap.css` 파일 생성
```css
/* customizeBootstrap.css */

@charset "UTF-8";

.card-title.header-padding-top {
	padding-top: 5px;
}

.img-thumnail-h100px {
	max-height: 100px;
	width: auto;
}
```

- head.jsp에 customize css 파일 경로 추가
```jsp
<!-- head.jsp -->

	<!-- ~~~ -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	
	<!-- Customize CSS -->
	<link rel="stylesheet" href="../css/customizeBootstrap.css">
```

- servlet-config.xml에 css 파일 매핑
```xml
<!-- servlet-config.xml -->

	<!-- ~~~ -->
	<mvc:resources mapping="adminlte3/**" location="/resources/bootstrap/adminlte3/"></mvc:resources>
	<!-- css 추가 -->
	<mvc:resources mapping="css/**" location="/resources/css/"></mvc:resources>
	<!-- js파일 매핑 -->
	<!-- ~~~ -->
```

- 청첩장 상세와 청첩장 추가 화면 구현
  - `src/main/webapp/WEB-INF/views/invitation/invitationDetail.jsp` 파일 생성
  - `src/main/webapp/WEB-INF/views/invitation/invitationAdd.jsp` 파일 생성
```jsp
<!-- invitationDetail.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- daterange picker -->
	<link rel="stylesheet" href="../adminlte3/plugins/daterangepicker/daterangepicker.css">
	<!-- Ekko Lightbox -->
	<link rel="stylesheet" href="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.css">
	<!-- dataTable -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	
	<title>청첩장 상세</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 상세</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>아이디 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<button type="button" class="btn btn-default btn-sm" id="">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 상태 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<input type="checkbox" id="" />
										<label>체크 시 비공개로 전환</label>
									</div>
									<div class="col-md-2">
										<span>게시 기간 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="" id="inputDateView" />
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Home, Groom &amp; Bride (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>결혼 일자 및 일시 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="" id="inputDateTimeWedding" />
									</div>
									<div class="col-md-2">
										<span>장소 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
									</div>
									<div class="col-md-2">
										<span>신부 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>메인 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
									<div class="col-md-6">
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 &amp; 신부 사진 사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
									<div class="col-md-2">
										<span>신부 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Love story (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<span>●를 마우스로 끌어서 순서 변경 가능합니다.</span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- start : tableRecordLoveStory -->
										<table id="tableRecordLoveStory" class="table table-valign-middle table-bordered dataTable">
											<tbody>
												<tr>
													<td rowspan="3" style="width:10%;" class="text-center">●</td>
													<td rowspan="3" style="width:20%;" class="text-center">
														<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
														</a>
													</td>
													<td style="width:10%;" class="text-center">일자</td>
													<td style="width:60%;">
														<input type="text" class="inputDateLoveStory" />
													</td>
												</tr>
												<tr>
													<td class="text-center">제목</td>
													<td>
														<input type="text" class="w-100" id="" />
													</td>
												</tr>
												<tr>
													<td rowspan="2" class="text-center">내용</td>
													<td rowspan="2">
														<textarea rows="3" class="form-control" id="" style="resize:none;"></textarea>
													</td>
												</tr>
												<tr>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
													</td>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- end : tableRecordLoveStory -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-default btn-sm" id="">추가</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ When Where (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">결혼식</td>
												<td>
													<input type="checkbox" id="" checked disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" id="" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">폐백</td>
												<td>
													<input type="checkbox" id="" />
													<span>(선택)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="inputDateWhenWhere" />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" id="" disabled />
													<button type="button" class="btn btn-default btn-sm" id="">검색</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Gallery (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Sweet Message (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-default btn-sm" id="">방명록 다운로드</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table id="tableMemberList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;" class="text-center">순번</th>
													<th style="width:10%;" class="text-center">일시</th>
													<th style="width:10%;" class="text-center">이름</th>
													<th style="width:50%;" class="text-center">내용</th>
													<th style="width:10%;" class="text-center">비밀번호</th>
													<th style="width:5%;" class="text-center">삭제</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- date-range-picker -->
	<script src="../adminlte3/plugins/daterangepicker/moment.min.js"></script>
	<script src="../adminlte3/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Ekko Lightbox -->
	<script src="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.min.js"></script>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationDetail.js"></script>
</body>
</html>
```

```jsp
<!-- invitationAdd.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- daterange picker -->
	<link rel="stylesheet" href="../adminlte3/plugins/daterangepicker/daterangepicker.css">
	<!-- Ekko Lightbox -->
	<link rel="stylesheet" href="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.css">
	<!-- dataTable -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	
	<title>청첩장 추가</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 추가</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content" id="sectionContent">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>아이디 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<button type="button" class="btn btn-default btn-sm" id="">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 상태 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<input type="checkbox" id="" />
										<label>체크 시 비공개로 전환</label>
									</div>
									<div class="col-md-2">
										<span>게시 기간 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="" id="inputDateView" />
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Home, Groom &amp; Bride (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>결혼 일자 및 일시 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="" id="inputDateTimeWedding" />
									</div>
									<div class="col-md-2">
										<span>장소 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" />
										<button type="button" class="btn btn-default btn-sm btnGetAddress">검색</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
									</div>
									<div class="col-md-2">
										<span>신부 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>메인 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
									<div class="col-md-6">
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 &amp; 신부 사진 사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
									<div class="col-md-2">
										<span>신부 사진 : </span>
									</div>
									<div class="col-md-4">
										<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/sample1.jpg" class="mb-2 img-thumnail-h100px">
										</a>
										<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Love story (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<span>●를 마우스로 끌어서 순서 변경 가능합니다.</span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<!-- start : tableRecordLoveStory -->
										<table id="tableRecordLoveStory" class="table table-valign-middle table-bordered dataTable">
											<tbody>
												<tr>
													<td rowspan="3" style="width:10%;" class="text-center">●</td>
													<td rowspan="3" style="width:20%;" class="text-center">
														<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
														</a>
													</td>
													<td style="width:10%;" class="text-center">일자</td>
													<td style="width:60%;">
														<input type="text" class="inputDateLoveStory" />
													</td>
												</tr>
												<tr>
													<td class="text-center">제목</td>
													<td>
														<input type="text" class="w-100" id="" />
													</td>
												</tr>
												<tr>
													<td rowspan="2" class="text-center">내용</td>
													<td rowspan="2">
														<textarea rows="3" class="form-control" id="" style="resize:none;"></textarea>
													</td>
												</tr>
												<tr>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
													</td>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- end : tableRecordLoveStory -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-default btn-sm" id="">추가</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ When Where (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">결혼식</td>
												<td>
													<input type="checkbox" id="" checked disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" id="" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">폐백</td>
												<td>
													<input type="checkbox" id="" />
													<span>(선택)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="inputDateWhenWhere" />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" id="" disabled />
													<button type="button" class="btn btn-default btn-sm" id="">검색</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Gallery (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td style="width:20%;" class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
												<td class="text-center">
													<a href="../css/img/sample2.jpg" data-toggle="lightbox" data-title="image title">
														<img src="../css/img/sample1.jpg" class="img-thumnail-h100px">
													</a>
												</td>
											</tr>
											<tr>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
												<td class="text-center">
													<button type="button" class="btn btn-default btn-sm" id="">사진 첨부</button>
													<button type="button" class="btn btn-default btn-sm" id="">삭제</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Sweet Message (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" id="" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-default btn-sm" id="">방명록 다운로드</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table id="tableMemberList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;" class="text-center">순번</th>
													<th style="width:10%;" class="text-center">일시</th>
													<th style="width:10%;" class="text-center">이름</th>
													<th style="width:50%;" class="text-center">내용</th>
													<th style="width:10%;" class="text-center">비밀번호</th>
													<th style="width:5%;" class="text-center">삭제</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-primary btn-lg" id="">저장</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- date-range-picker -->
	<script src="../adminlte3/plugins/daterangepicker/moment.min.js"></script>
	<script src="../adminlte3/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Ekko Lightbox -->
	<script src="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.min.js"></script>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationAdd.js"></script>
</body>
</html>
```

- 청첩장 상세와 청첩장 추가 js 구현
  - `src/main/webapp/resources/js/invitation/invitationDetail.js` 파일 생성
  - `src/main/webapp/resources/js/invitation/invitationAdd.js` 파일 생성
```js
// invitationDetail.js

/**
 * 
 */

console.log("########## invitationDetail.js ##########");

$(function(){
	setActiveSidebar();
	
	$("#inputDateView").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
		/*autoUpdateInput: false
		
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		  });*/
	});
	
	$("#inputDateTimeWedding").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	// 유동적으로 추가한것도 먹힐려나??
	$(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$("#inputDateWhenWhere").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	$(document).on('click', '[data-toggle="lightbox"]', function(event) {
		event.preventDefault();
		$(this).ekkoLightbox({
			alwaysShowClose: true
		});
	});
});
```

```js
// invitationAdd.js

/**
 * 
 */

console.log("########## invitationAdd.js ##########");

$(function(){
	setActiveSidebar();
	
	$("#inputDateView").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
		/*autoUpdateInput: false
		
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		  });*/
	});
	
	$("#inputDateTimeWedding").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	// 유동적으로 추가한것도 먹힐려나??
	$(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$("#inputDateWhenWhere").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	$("#sectionContent").on("click", "[data-toggle='lightbox']", function(event) {
		event.preventDefault();
		
		$(this).ekkoLightbox({
			alwaysShowClose: true
		});
	});
	
	$(".btnGetAddress").click(function(){
		var pop = window.open("/admin/popup/jusoPopup.jsp", "pop", "scrollbars=yes, resizeable=yes");
	});
});

function jusoCallBack(...res) {
	// res = ["서울특별시 중구 청구로 지하 77, 걍 써봄 (신당동)", "서울특별시 중구 청구로 지하 77", "걍 써봄", "(신당동)", "B 77, Cheonggu-ro, Jung-gu, Seoul", "서울특별시 중구 신당동 295-2 청구역 5,6호선", "04608", "1114016200", "111403101008", "1114016200102950002000001", "5,6호선", "청구역 5,6호선", "0", "서울특별시", "중구", "신당동", "", "청구로", "1", "77", "0", "0", "295", "2", "01", "957058.9352199801", "1951330.378632207"]
	console.log(res);
}
```

- 주소 검색용 페이지 추가
  - `src/main/webapp/popup/jusoPopup.jsp`파일 추가
  - 도로명 주소 api 발급 후 키 정보 등록
```jsp
<!-- jusoPopup.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
	//request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
	String inputYn = request.getParameter("inputYn"); 
	String roadFullAddr = request.getParameter("roadFullAddr"); 
	String roadAddrPart1 = request.getParameter("roadAddrPart1"); 
	String roadAddrPart2 = request.getParameter("roadAddrPart2"); 
	String engAddr = request.getParameter("engAddr"); 
	String jibunAddr = request.getParameter("jibunAddr"); 
	String zipNo = request.getParameter("zipNo"); 
	String addrDetail = request.getParameter("addrDetail"); 
	String admCd    = request.getParameter("admCd");
	String rnMgtSn = request.getParameter("rnMgtSn");
	String bdMgtSn  = request.getParameter("bdMgtSn");
	String detBdNmList  = request.getParameter("detBdNmList");
	String bdNm  = request.getParameter("bdNm");
	String bdKdcd  = request.getParameter("bdKdcd");
	String siNm  = request.getParameter("siNm");
	String sggNm  = request.getParameter("sggNm");
	String emdNm  = request.getParameter("emdNm");
	String liNm  = request.getParameter("liNm");
	String rn  = request.getParameter("rn");
	String udrtYn  = request.getParameter("udrtYn");
	String buldMnnm  = request.getParameter("buldMnnm");
	String buldSlno  = request.getParameter("buldSlno");
	String mtYn  = request.getParameter("mtYn");
	String lnbrMnnm  = request.getParameter("lnbrMnnm");
	String lnbrSlno  = request.getParameter("lnbrSlno");
	String emdNo  = request.getParameter("emdNo");
	String entX  = request.getParameter("entX");
	String entY  = request.getParameter("entY");

%>
</head>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

/*
			모의 해킹 테스트 시 팝업API를 호출하시면 IP가 차단 될 수 있습니다. 
			주소팝업API를 제외하시고 테스트 하시기 바랍니다.
*/

function init(){
	var url = location.href;
	var confmKey = "API KEY";
	var resultType = "4"; // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명
	var inputYn= "<%=inputYn%>";
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrCoordUrl.do"; //인터넷망
		document.form.submit();
	}else{
		opener.jusoCallBack("<%=roadFullAddr%>","<%=roadAddrPart1%>","<%=addrDetail%>","<%=roadAddrPart2%>","<%=engAddr%>","<%=jibunAddr%>","<%=zipNo%>", "<%=admCd%>", "<%=rnMgtSn%>", "<%=bdMgtSn%>", "<%=detBdNmList%>", "<%=bdNm%>", "<%=bdKdcd%>", "<%=siNm%>", "<%=sggNm%>", "<%=emdNm%>", "<%=liNm%>", "<%=rn%>", "<%=udrtYn%>", "<%=buldMnnm%>", "<%=buldSlno%>", "<%=mtYn%>", "<%=lnbrMnnm%>", "<%=lnbrSlno%>", "<%=emdNo%>", "<%=entX%>", "<%=entY%>");
		window.close();
		}
}
</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
		<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
	</form>
</body>
</html>
```

## 11-2 청첩장 추가 - 기본 정보
- 아이디 검색용 vo 작성
```java
// MemberInfoVO.java

package com.invitation.biz.invitation;

public class MemberInfoVO {

	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "UserMemberInfoVO [id=" + id + ", name=" + name + "]";
	}
}
```

- 아이디 검색용 xml 작성
  - `src/main/resources/mappings/Invitation-mapping.xml` 파일 생성
```xml
<!-- sql-map-config.xml -->

		<!-- ~~~ -->
		<typeAlias alias="userMemberInfo" type="com.invitation.biz.member.user.UserMemberInfoVO"></typeAlias> 
		<typeAlias alias="memberInfo" type="com.invitation.biz.invitation.MemberInfoVO"></typeAlias> 
	</typeAliases>
	
	<mappers>
		<mapper resource="mappings/AdminUseres-mapping.xml" />
		<mapper resource="mappings/MemberUseres-mapping.xml" />
		<mapper resource="mappings/Invitation-mapping.xml" />
	</mappers>
</configuration>
```

```xml
<!-- invitation-mapping.xml -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="InvitationDAO">
	<select id="getMemberInfo" resultType="memberInfo">
		SELECT
			ID,
			NAME
		FROM
			USERES
		WHERE
			ID = #{id}
		;
	</select>
</mapper>
```

- 아이디 검색용 DAO, service, sericeImpl 파일 생성
  - `src/main/java/com/invitation/biz/invitation/impl 생성
```java
// InvitationDAOMybatis.java

package com.invitation.biz.invitation.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.invitation.MemberInfoVO;

@Repository
public class InvitationDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberInfoVO getMemberInfo(String id) {
		return mybatis.selectOne("InvitationDAO.getMemberInfo", id);
	}
}
```

```java
// InvitationService.java

package com.invitation.biz.invitation;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id);

}
```

```java
// InvitationServiceImpl.java

package com.invitation.biz.invitation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.MemberInfoVO;

@Service("invitation")
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationDAOMybatis invitationDAO;
	
	@Override
	public MemberInfoVO getMemberInfo(String id) {
		return invitationDAO.getMemberInfo(id);
	}
}
```

- 아이디 검색용 controller 수정
```java
// InvitationController.java

		// ~~~
		return "invitation/invitationList";
	}
	
	@GetMapping(value="/getMemberInfo")
	@ResponseBody
	public Map<String, Object> getMemberInfo(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		MemberInfoVO resMemberInfo = null;
		
		LOGGER.info("getMemberInfo");
		try {
			resMemberInfo = invitationService.getMemberInfo(id);
			
			if(resMemberInfo == null) {
				throw new CommonException("조회 결과 없음!!");
			}
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "일치하는 회원이 없습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage",  resMessage);
			result.put("resMemberInfo", resMemberInfo);
		}
		
		return result;
	}
}
```

- 기본 정보 화면 구현
  - 아이디 검색 추가
  - 등록 전 유효성 검사 체크 로직 추가
```jsp
<!-- invitationAdd.jsp -->

						<!-- ~~~ -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>아이디 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="inputId" />
										<button type="button" class="btn btn-default btn-sm" id="btnMemberSearch">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="inputName" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 기간 : </span>
									</div>
									<div class="col-md-10">
										<input type="text" class="" id="inputDatePeriod" /> <label><input type="checkbox" name="checkboxOpenYN" />체크 시 비공개로 등록</label>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- ~~~ -->
```

```js
// invitationAdd.js

$(function(){
	setActiveSidebar();
	
	$("#btnMemberSearch").on("click", function(){
		var inputId = $("#inputId").val();
		
		if(inputId == "") {
			alert("아이디를 입력해주세요.");
		} else {
			getMemberInfo(inputId);
		}
	});
	
	$("#inputDatePeriod").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
		/*autoUpdateInput: false
		
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		  });*/
	});
	
	//------------------------------------------------------------------------------------------------------
	
	//#inputDateWhenWhere 형식 같은데 묶어봐
	$("#inputDateTimeWedding").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	// 유동적으로 추가한것도 먹힐려나??
	$(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$("#inputDateWhenWhere").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	
	$("#sectionContent").on("click", "[data-toggle='lightbox']", function(event) {
		event.preventDefault();
		
		$(this).ekkoLightbox({
			alwaysShowClose: true
		});
	});
	
	$(".btnGetAddress").on("click", function(){
		$btnGetAddress = $(".btnGetAddress").index($(this));
		var pop = window.open("/admin/popup/jusoPopup.jsp", "pop", "scrollbars=yes, resizeable=yes");
	});
	
	$("#btnRegisterInvitation").on("click", function(){
		var result = validateData();
		
		if(result.resFlag) {
			console.log(result.resData);
		} else {
			alert(result.resMessage);
		}
	});
});

function getMemberInfo(id) {
	$("#inputId").removeData("id");
	$("#inputName").val("");
	
	$.ajax({
		url : "/admin/invitation/getMemberInfo?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : ", status, "\nHttp error msg : ", msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				$("#inputId").data("id", result.resMemberInfo.id);
				$("#inputName").val(result.resMemberInfo.name);
			} else {
				alert(result.resMessage);
			}
		}
	});
}

function jusoCallBack(...res) {
	// res = ["서울특별시 중구 청구로 지하 77, 걍 써봄 (신당동)", "서울특별시 중구 청구로 지하 77", "걍 써봄", "(신당동)", "B 77, Cheonggu-ro, Jung-gu, Seoul", "서울특별시 중구 신당동 295-2 청구역 5,6호선", "04608", "1114016200", "111403101008", "1114016200102950002000001", "5,6호선", "청구역 5,6호선", "0", "서울특별시", "중구", "신당동", "", "청구로", "1", "77", "0", "0", "295", "2", "01", "957058.9352199801", "1951330.378632207"]
	console.log(res);
}

function validateData() {
	var result = {
			resFlag : true,
			resData : {
				id : $("#inputId").data("id") || "",
				invitationBegin : ($("#inputDatePeriod").val().split(" - "))[0] || "",
				invitationEnd : ($("#inputDatePeriod").val().split(" - "))[1] || "",
				openYN : "Y"
			},
			resMessage : ""
	};
	
	if(id == "") {
		result.resFlag = false;
		result.resMessage = "아이디를 입력해주세요."
	} else if(invitationBegin == "" || invitationEnd == "") {
		result.resFlag = false;
		result.resMessage = "게시기간을 선택해주세요.";
	}
	
	return result;
}	
```

---

## 11-3 청첩장 추가 - 파일업로드/이미지보기/썸네일 구현
- 파일업로드 구현
	- `com.invitation.biz.common.fileUpload` 패키지 생성
	- MediaUtils.java, UploadFileUtils.java 클래스 작성

	- java util 클래스 구현
		- 파일업로드 시 저장될 파일을 담을 폴더 생성 `upload\images`
			로컬에서 작업할 경우 : `[해당 프로젝트 워크스페이스]\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\[프로젝트명]\resources\` 하위에 위의 폴더 생성
```java
// MediaUtils.java

package com.invitation.biz.common.fileUpload;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {

	private static Map<String, MediaType> mediaTypeMap;
	
	static {
		mediaTypeMap = new HashMap<String, MediaType>();
		mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
		mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String fileName) {
		String formatName = getFormatName(fileName);
		
		return mediaTypeMap.get(formatName);
	}
	
	static String getFormatName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
	}
}

```

```java
// UploadFileUtils.java

package com.invitation.biz.common.fileUpload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
		String originalFileName = file.getOriginalFilename();
		byte[] fileData = file.getBytes();
		
		LOGGER.debug("uploadFile util : " + originalFileName);
		String uuidFileName = getUuidFileName(originalFileName);
		
		String rootPath = getRootPath(originalFileName, request);
		String datePath = getDatePath(rootPath);
		
		File target = new File(rootPath + datePath, uuidFileName);
		FileCopyUtils.copy(fileData, target);
		
		if(MediaUtils.getMediaType(originalFileName) != null) {
			uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
		}
		
		return replaceSavedFilePath(datePath, uuidFileName);
	}
	
	public static void deleteFile(String fileName, HttpServletRequest request) {
		String rootPath = getRootPath(fileName, request);
		
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		if(mediaType != null) {
			LOGGER.debug("fileName 0 to end : " + fileName);
			LOGGER.debug("fileName 0 to 12 : " + fileName.substring(0, 12));
			LOGGER.debug("fileName 14 to end : " + fileName.substring(14));
			String originalImg = fileName.substring(0, 12) + fileName.substring(14);
			
			new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
		}
		
		new File(rootPath + fileName.replace('/', File.separatorChar)).delete();
	}
	
	public static HttpHeaders getHttpHeaders(String fileName) throws Exception {
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		if(mediaType != null) {
			httpHeaders.setContentType(mediaType);
			return httpHeaders;
		}
		
		fileName = fileName.substring(fileName.indexOf("_") + 1);
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.add("Content-Disposition", "attachment); filename=\''" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\''");
		
		LOGGER.debug("################ 여로모로 이상한 라인인디;; ##################");
		LOGGER.debug("attachment); filename=\''" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\''");
		return httpHeaders;
	}
	
	public static String getRootPath(String fileName, HttpServletRequest request) {
		String rootPath = "/resources/upload";
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		
		LOGGER.debug("getRootPath : " + request.getSession().getServletContext().getRealPath(rootPath));
		if(mediaType != null) {
			return request.getSession().getServletContext().getRealPath(rootPath + "/images");
		}
		
		return request.getSession().getServletContext().getRealPath(rootPath + "/files");
	}
	
	private static String getDatePath(String uploadPath) {
		Calendar calendar = Calendar.getInstance();
		String yearPath = File.separator + calendar.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		
		makeDateDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	private static void makeDateDir(String uploadPath, String... paths) {
		if(new File(uploadPath + paths[paths.length - 1]).exists()) {
			return;
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	private static String replaceSavedFilePath(String datePath, String fileName) {
		String savedFilePath = datePath + File.separator + fileName;
		
		return savedFilePath.replace(File.separatorChar, '/');
	}
	
	private static String getUuidFileName(String originalFileName) {
		return UUID.randomUUID().toString() + "_" + originalFileName;
	}
	
	private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
		BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
		
		BufferedImage thumnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumbnailImgName = "s_" + fileName;
		
		String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
		File newFile = new File(fullPath);
		String formatName = MediaUtils.getFormatName(fileName);
		
		ImageIO.write(thumnailImg, formatName, newFile);
		
		return thumbnailImgName;
	}
}
```

	- 파일업로드를 위한 설정 추가
```xml
<!-- pom.xml -->

	<!-- ~~~ -->
	<dependencies>
		<!-- file upload -->
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
		    <groupId>commons-fileupload</groupId>
		    <artifactId>commons-fileupload</artifactId>
		    <version>1.4</version>
		</dependency>
		
		<!-- image resize -->
		<!-- https://mvnrepository.com/artifact/org.imgscalr/imgscalr-lib -->
		<dependency>
		    <groupId>org.imgscalr</groupId>
		    <artifactId>imgscalr-lib</artifactId>
		    <version>4.2</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-core</artifactId>
		    <version>2.11.0</version>
		</dependency>
	
		<!-- json -->
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.11.0</version>
		</dependency>
	
		<!-- database -->
		<!-- ~~~ -->
```

```xml
<!-- servlet-config.xml -->

	<!-- ~~~ -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="10485760"></property>
	</bean>
</beans>
```

	- 파일업로드 컨트롤러 추가 (파일업로드, 썸네일 이미지, 이미지 보기 구현)
		- `com.invitation.controller.common.fileUpload` 패키지 생성
```java
// FileUploadController.java

package com.invitation.controller.common.fileUpload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.invitation.biz.common.fileUpload.MediaUtils;
import com.invitation.biz.common.fileUpload.UploadFileUtils;

@Controller
@RequestMapping(value="/common")
public class FileUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) {
		String resSavedFilePath = "";
		
		ResponseEntity<String> entity = null;
		
		LOGGER.info("fileUpload.do");
		try {
			resSavedFilePath = UploadFileUtils.uploadFile(file, request);
//			/2020/05/18/s_88385895-783f-4dd8-bb2a-35fa13ff12ae_sample1.jpg
			
			entity = new ResponseEntity<>(resSavedFilePath, HttpStatus.CREATED);
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/fileDisplay.do", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		String rootPath = UploadFileUtils.getRootPath(fileName, request);
		ResponseEntity<byte[]> entity = null;
		
		LOGGER.info("fileDisplay.do");
		try(InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.OK);
		} catch(FileNotFoundException e) {
			LOGGER.info("info message : 파일을 찾지 못함!! " + e.getMessage());
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error treace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
```

- 청첩창 주가 화면 수정 (파일업로드 및 기타 이벤트)
	- 파일업로드용 input 태그 customize
```css
/* customizeBootstrap.css */

/* ~~~ */
.w70p {
	width: 70%;
}

.uploadbox label {
	display: inline-block;
	padding: .25rem .5rem;
	color: #444;
	font-size: .875rem;
	font-weight: 400 !important;
	line-height: 1.5;
	vertical-align: middle;
	background-color: #f8f9fa;
	cursor: pointer;
	border: 1px solid #ddd;
	border-radius: .2rem;
	margin-bottom: 0;
}
.uploadbox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0,0,0,0);
	border: 0;
}
```

	- 파일 업로드 전 `src/main/webapp/resources/css/img`에 기본 썸네일 이미지 파일(uploadImage.png) 추가
```jsp
<!-- invitationAdd.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- daterange picker -->
	<link rel="stylesheet" href="../adminlte3/plugins/daterangepicker/daterangepicker.css">
	<!-- Ekko Lightbox -->
	<link rel="stylesheet" href="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.css">
	<!-- dataTable -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	
	<title>IA</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 추가</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content" id="sectionContent">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>아이디 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="inputId" />
										<button type="button" class="btn btn-default btn-sm" id="btnMemberSearch">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="inputName" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 기간 : </span>
									</div>
									<div class="col-md-10">
										<input type="text" class="" id="inputDatePeriod" /> <label><input type="checkbox" name="checkboxVisibleYN" />체크 시 비공개로 등록</label>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card" id="divHomeGroomBride">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Home, Groom &amp; Bride (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>결혼 일자 및 일시 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="inputDateTime" id="inputDateTimeWedding" />
									</div>
									<div class="col-md-2">
										<span>장소 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="w70p inputAddrWeddingPlace" id="infoWeddingPlace" disabled />
										<button type="button" class="btn btn-default btn-sm btnGetAddress">검색</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="contentGroom" style="resize:none;"></textarea>
									</div>
									<div class="col-md-2">
										<span>신부 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="contentBride" style="resize:none;"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>메인 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgHGB1">업로드</label>
										<input type="file" class="btnUploadFile" id="imgHGB1" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<!-- <form method="post" enctype="multipart/form-data">
											<input type="file" class="btnUploadFile" />
										</form> -->
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
									</div>
									<div class="col-md-6">
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 &amp; 신부 사진 사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxEachImgYN" checked />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgHGB2">업로드</label>
										<input type="file" class="btnUploadFile" id="imgHGB2" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
									</div>
									<div class="col-md-2">
										<span>신부 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgHGB3">업로드</label>
										<input type="file" class="btnUploadFile" id="imgHGB3" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Love story (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseLSYN" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<span>●를 마우스로 끌어서 순서 변경 가능합니다.</span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="wrapListLS">
										<!-- start : tableRecordLoveStory -->
										<table id="tableRecordLoveStory" class="table table-valign-middle table-bordered dataTable itemLoveStory">
											<tbody class="uploadbox wrapUploadFile">
												<tr>
													<td rowspan="3" style="width:10%;" class="text-center tdSortable">●</td>
													<td rowspan="3" style="width:20%;" class="text-center">
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</td>
													<td style="width:10%;" class="text-center">일자</td>
													<td style="width:60%;">
														<input type="text" class="inputDateLoveStory" />
													</td>
												</tr>
												<tr>
													<td class="text-center">제목</td>
													<td>
														<input type="text" class="w-100 inputTitleLS" />
													</td>
												</tr>
												<tr>
													<td rowspan="2" class="text-center">내용</td>
													<td rowspan="2">
														<textarea rows="3" class="form-control inputContentLS" style="resize:none;"></textarea>
													</td>
												</tr>
												<tr>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm btnRemoveLS">삭제</button>
													</td>
													<td class="text-center">
														<label for="">업로드</label>
														<input type="file" class="btnUploadFile" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- end : tableRecordLoveStory -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-default btn-sm" id="btnAddLS">추가</button>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ When Where (필수)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">결혼식</td>
												<td>
													<input type="checkbox" checked disabled />
													<span>(필수)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="inputDateTimeWedding_copy" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" class="w70p inputAddrWeddingPlace" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="inputTitleWedingWW" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="inputContentWedingWW" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">폐백</td>
												<td>
													<input type="checkbox" name="checkboxDoPyebaek" />
													<span>(선택)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" class="inputDateTime" id="inputDatePyebaek" />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" class="w70p" id="inputAddrPyebaek" disabled />
													<button type="button" class="btn btn-default btn-sm btnGetAddress">검색</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="inputTitlePyebaekWW" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="inputContentPyebaekWW" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Gallery (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseG" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG1">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG1" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG2">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG2" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG3">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG3" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG4">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG4" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG5">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG5" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
											</tr>
											<tr>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG6">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG6" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG7">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG7" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG8">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG8" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG9">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG9" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG10">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG10" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Sweet Message (선택)</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseSM" />
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-primary btn-lg" id="btnRegisterInvitation">저장</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- jQuery-ui -->
	<script src="../adminlte3/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- date-range-picker -->
	<script src="../adminlte3/plugins/daterangepicker/moment.min.js"></script>
	<script src="../adminlte3/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Ekko Lightbox -->
	<script src="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.min.js"></script>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationAdd.js"></script>
</body>
</html>
```

	- 이미지 보기 요청용 url 상수 추가
```js
// def.js

// ~~~
var GO_TO_MAIN = "/admin/member/memberList.do";

// upload file info
var IMG_SRC = "/admin/common/fileDisplay.do?fileName=";
var IMG_SRC_ICO = "resources/upload/files/fileIcon.png";
var ORIGINAL_FILE_URL = "/admin/common/fileDisplay.do?fileName=";
var DEFAULT_IMG_SRC = "../css/img/uploadImage.png";

var MAP_SIDEBAR = {
// ~~~
```

- js파일 수정 (파일업로드 및 기타 이벤트)
	- 이미지 업로드, 썸네일 보기, 이미지 보기, 이미지 삭제 기능 구현
```js
// util.js

			// ~~~
			$idTarget.find(".last").removeClass("disabled");
		}
	}
};

function uploadFile($this, callback) {
	var file = $($this)[0].files[0],
		$fileUploadForm = $("<form>"),
		formData = new FormData($fileUploadForm[0]);
	
	formData.append("file", file);
	
	$.ajax({
		url : "/admin/common/fileUpload.do",
		data : formData,
		dataType : "text",
		processData : false,
		contentType : false,
		type : "POST",
		enctype : "multipart/form-data",
		cache : false,
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			callback(setFileInfo(result));
		}
	});
};

function setFileInfo(fullName) {
	var originalFileName, imgSrc, originalFileUrl, uuidFileName;
	
	if(checkImageType(fullName)) {
		imgSrc = IMG_SRC + fullName;
		uuidFileName = fullName.substr(14);
		var originalImg = fullName.substr(0, 12) + fullName.substr(14);
		
		originalFileUrl = ORIGINAL_FILE_URL + originalImg;
	} else {
		imgSrc = IMG_SRC_ICO;
		uuidFileName = fullName.substr(12);
		originalFileUrl = ORIGINAL_FILE_URL + fullName;
	}
	originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);
	
	return {
		originalFileName : originalFileName,
		imgSrc : imgSrc,
		originalFileUrl : originalFileUrl,
		fullName : fullName
	}
};

function checkImageType(fullName) {
	return fullName.match(/jpg$|gif$|jpeg$|png$/i);
};

$(function(){
	// ~~~
```

```js
// invitationAdd.js

	// ~~~
	$("#btnRegisterInvitation").on("click", function(){
		var result = validateData();
		
		if(result.resFlag) {
			console.log(result.resData);
		} else {
			alert(result.resMessage);
		}
	});
	
	$("#sectionContent").on("change", ".btnUploadFile", function(){
		var $this = $(this);
		
		uploadFile($(this), function(res){
			$this.prev().hide();
			$this.hide()
					.parents(".wrapUploadFile")
						.find("a").attr("href", res.originalFileUrl).data("title", res.originalFileName)
						.find("img").attr("src", res.imgSrc).data("fullName", res.fullName);
			$this.next().show();
		});
	});
	
	$("#sectionContent").on("click", "[data-toggle='lightbox']", function(event) {
		event.preventDefault();
		var $this = $(this);

		if($this.attr("href") != "" && $this.find("img").attr("src") != DEFAULT_IMG_SRC) {
			$this.ekkoLightbox({
				alwaysShowClose: true
			});
		}
	});
	
	$(".btnDeleteImage").on("click", function(){
		var $wrapUploadFile = $(this).parents(".wrapUploadFile");
		
		$wrapUploadFile.find("a").attr("href", "").removeData("title", "")
								.find("img").attr("src", DEFAULT_IMG_SRC).removeData("fullName");
		$wrapUploadFile.find(".btnUploadFile").val("").show()
														.prev().show()
														.next().next().hide();
	});
	
	$("#btnAddLS").on("click", function(){
		cloneLoveStory();
	});
	
	$("#wrapListLS").on("click", ".btnRemoveLS", function(){
		$(this).parents(".itemLoveStory").remove();
		
		resetTagId();
	});
	
	//------------------------------------------------------------------------------------------------------
	
	// 유동적으로 추가한것도 먹힐려나??
	/*$(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});*/
});

function setClone() {
	$tableRecordLoveStory = $("#tableRecordLoveStory").clone();
	$("#tableRecordLoveStory").remove();
	
	cloneLoveStory();
	$("#wrapListLS").sortable();
}

function cloneLoveStory() {
	var $itemLoveStory = $tableRecordLoveStory.clone();
	
	$itemLoveStory.find(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	$("#wrapListLS").append($itemLoveStory);
	
	resetTagId();
}

function resetTagId() {
	var index = 1;
	
	$(".itemLoveStory").each(function(i){
		$(this).find(".btnUploadFile").attr("id", "imgLS" + String(index))
					.prev().attr("for", "imgLS" + String(index));
		
		index = index + 1;
	});
}

function getMemberInfo(id) {
	$("#inputId").removeData("id");
	$("#inputName").val("");
	
	$.ajax({
		url : "/admin/invitation/getMemberInfo?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : ", status, "\nHttp error msg : ", msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				$("#inputId").data("id", result.resMemberInfo.id);
				$("#inputName").val(result.resMemberInfo.name);
			} else {
				alert(result.resMessage);
			}
		}
	});
}

function jusoCallBack(...res) {
	// res = ["서울특별시 중구 청구로 지하 77, 걍 써봄 (신당동)", "서울특별시 중구 청구로 지하 77", "걍 써봄", "(신당동)", "B 77, Cheonggu-ro, Jung-gu, Seoul", "서울특별시 중구 신당동 295-2 청구역 5,6호선", "04608", "1114016200", "111403101008", "1114016200102950002000001", "5,6호선", "청구역 5,6호선", "0", "서울특별시", "중구", "신당동", "", "청구로", "1", "77", "0", "0", "295", "2", "01", "957058.9352199801", "1951330.378632207"]
	console.log(res);
	
	if($btnGetAddress == 0) {
		$(".inputAddrWeddingPlace").val(res[0]);
		$("#infoWeddingPlace").data("infoWeddingPlace", {
			addr : res[0],
			placeX : res[25],
			placeY : res[26]
		});
	} else {
		$("#inputAddrPyebaek").val(res[0]).data("infoPyebaek", {
			addr : res[0],
			placeX : res[25],
			placeY : res[26]
		});
	}
}

function validateData() {
	var id = $("#inputId").data("id") || "",
		invitation = {
			datePeriod : $("#inputDatePeriod").val().split(" - "),
			visible : $("input[name=checkboxVisibleYN]").prop("checked") == true ? "N" : "Y"
		},
		HGB = {
			weddingDateTime : $("#inputDateTimeWedding").val(),
			weddingPlaceInfo : $("#infoWeddingPlace").data("infoWeddingPlace") || "",
			contentGroom : $("#contentGroom").val(),
			contentBride : $("#contentBride").val(),
			imgMain : $("#imgHGB1").parents(".wrapUploadFile").find(".img").data("fullName"),
			ynUseImage : $("input[name=checkboxEachImgYN]").prop("checked") == true ? "Y" : "N",
			imgGroom : $("#imgHGB2").parents(".wrapUploadFile").find(".img").data("fullName"),
			imgBride : $("#imgHGB3").parents(".wrapUploadFile").find(".img").data("fullName"),
		}
	
	
	var result = {
			resFlag : true,
			resData : {
				id : $("#inputId").data("id") || "",
				invitationBegin : ($("#inputDatePeriod").val().split(" - "))[0] || "",
				invitationEnd : ($("#inputDatePeriod").val().split(" - "))[1] || "",
				openYN : $("input[name=checkboxVisibleYN]").prop("checked") == true ? "N" : "Y",
				dateTimeWedding : $("#inputDateTimeWedding").val() || "",
				weddingPlace : $("#infoWeddingPlace").data("infoWeddingPlace") || "",
				contentGroom : $("#contentGroom").val() || "",
				contentBride : $("#contentBride").val() || ""
			},
			resMessage : ""
	};
	
	if(result.resData.id == "") {
		result.resFlag = false;
		result.resMessage = "아이디를 입력해주세요."
	} else if(result.resData.invitationBegin == "" || result.resData.invitationEnd == "") {
		result.resFlag = false;
		result.resMessage = "게시기간을 선택해주세요.";
	} else if(result.resData.dateTimeWedding == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 일자를 선택해주세요.";
	} else if(result.resData.weddingPlace == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 입력해주세요.";
	} else if(result.resData.contentGroom.length >= 500) {
		result.resFlag = false;
		result.resMessage = "신랑 간단소개는 500자 이내로 입력해주세요.";
	} else if(result.resData.contentBride.length >= 500) {
		result.resFlag = false;
		result.resMessage = "신부 간단소개는 500자 이내로 입력해주세요.";
	}
	
	return result;
}
```