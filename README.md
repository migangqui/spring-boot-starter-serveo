# Spring Boot Starter Serveo

Spring Boot Starter to share your localhost Spring Application with an external url.

## What is Serveo?

From the official website, Serveo is an SSH server just for remote port forwarding. When a user connects to Serveo, they get a public URL that anybody can use to connect to their localhost server.

## How works this starter?

When you add this dependency to your pom.xml and configure it, you'll automatically get a public url to your local server.

 ## Dependency
 
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