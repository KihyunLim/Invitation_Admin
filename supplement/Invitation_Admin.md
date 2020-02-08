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
	<mvc:resources mapping="/adminlte3/**" location="/resources/bootstrap/adminlte3/"></mvc:resources>

	<!-- resolver 설정 추가 -->
	<!-- ~~~ -->
```

- 로그인 페이지 작성
  - `src/main/webapp/resources/pages/examples/login.html` 소스 복사
  - `src/main/WEB-INF/views/login/login.jsp` 생성 후 위의 소스 붙여넣기
  - 불필요한 부분 제거 및 수정
```jsp
<!-- login.jsp -->

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 3 | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="adminlte3/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="adminlte3/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="adminlte3/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
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

				<form action="login.do" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="아이디">
						<div class="input-group-append">
							<div class="input-group-text"></div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="비밀번호">
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
							<button type="submit" class="btn btn-primary btn-block">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="adminlte3/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="adminlte3/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="adminlte3/dist/js/adminlte.min.js"></script>

</body>
</html>
```

---

## 8. restful 적용, 로그인 구현
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

  - 로그인 화면 js 파일 생성