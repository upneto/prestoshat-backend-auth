# Etapa 1: Maven
FROM maven:alpine as build
ENV HOME=/prestoshat-backend-auth
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean package
#RUN mvn spring-boot:build-image

# Etapa 2: Java App
FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY --from=build "/prestoshat-backend-auth/target/prestoshat-backend-auth-*.jar" app.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]