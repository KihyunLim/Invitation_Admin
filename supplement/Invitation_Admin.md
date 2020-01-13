# Invitation_Admin 프로젝트
<span style="color:red">추후 확인해봐야 할것</span>
- 1)에 pom.xml 설정하는거에서 `javax.servlet.jsp.jstl-ap`는 왜 안되는지 모르겠다;;
  - spring web mvc에서 해당 스프링 버전의 compile dependencies 참고하면 이거 쓰는게 맞는데..
    - 쓰면 home.jsp에서 Can not find the tag library descriptor for "http://java.sun.com/
 jsp/jstl/core" 에러 남

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