# Serveo Spring Boot Starter

Spring Boot Starter to share your localhost Spring Application with an external url.

## What is Serveo?

From the official website, Serveo is an SSH server just for remote port forwarding. When a user connects to Serveo, they get a public URL that anybody can use to connect to their localhost server.

## How works this starter?

When you add this dependency to your pom.xml and configure it, you'll automatically get a public url to your local server.

 ## Dependency
 
 Old dependency:
 
  * Maven:
  ```xml
 <dependency>
     <groupId>com.github.migangqui</groupId>
     <artifactId>spring-boot-starter-serveo</artifactId>
     <version>1.0</version>
 </dependency>
 ```
 
 * Gradle:
  ```xml
 compile('com.github.migangqui:spring-boot-starter-serveo:1.0')
 ```
 
 Actual version is 1.0
 
 * Maven:
 ```xml
<dependency>
    <groupId>com.github.migangqui</groupId>
    <artifactId>serveo-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

* Gradle:
 ```xml
compile('com.github.migangqui:serveo-spring-boot-starter:1.0')
```

## Configuration

You must enable Serveo runner adding the following property:
```yaml
serveo:
    enabled: true
```

As optional property, you can choose a subdomain:
```yaml
serveo:
    custom:
      domain: mysubdomain
```

### References

* Based in similar ngrok starter: https://github.com/kilmajster/ngrok-spring-boot-starter
* http://serveo.net/#manual

### Disclaimer

ArtifactId has been modified from <b>spring-boot-starter-serveo</b> to <b>serveo-spring-boot-starter</b> to follow Spring instruction starters naming
https://docs.spring.io/spring-boot/docs/2.0.0.M5/reference/html/boot-features-developing-auto-configuration.html#boot-features-custom-starter-naming