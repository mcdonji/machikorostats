# machikorostats
MachiKoro Strategy Tester

This is a concrete example of creating a Spring Boot Microservice that has a domain that is amiable to playing with Machine Learning.



To get going from scratch on a windows box:

https://chocolatey.org/install

admin powershell

choco install gradle

gihub poof repo

git clone

powershell in new repo

gradle init 

create .ignore file
/build/*

add directory 
/src/main/java/boo

create file ApplicationStartup.java in boo directory
```
package boo;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationStartup {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }
}
```

create file BooController.java in boo directory
```
package boo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BooController {

    @RequestMapping("/")
    public String index() {
        return "Boo!";
    }
}
```


update file build.gradle

```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.3.RELEASE")
    }
}

plugins {
    id 'java'
    id 'idea'
	id 'org.springframework.boot' version '2.0.4.RELEASE'
    id 'io.spring.dependency-management' version '1.0.6.RELEASE'
    id 'application'
}

bootJar {
    baseName = 'gs-rest-service'
    version =  '0.1.0'
}

mainClassName = 'com.mcdonji.machikorostats.ApplicationStartup'

dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}

bootRun {
    systemProperty 'spring.config.location', 'C:\\Dev\\signal\\config\\application.properties'
}
```







