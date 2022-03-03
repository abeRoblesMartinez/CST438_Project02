
FROM --platform=linux/amd64 openjdk:11
VOLUME /tmp
COPY target/CST438_Project02-0.0.1-SNAPSHOT.jar  CST438_Project02-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/CST438_Project02-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:8080
