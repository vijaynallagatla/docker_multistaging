FROM openjdk:8u171-jre-alpine
RUN mkdir -p /usr/src/app
COPY 'target/JAXRS.jar' /usr/src/app
RUN chmod -R 777 /usr/src/app
WORKDIR /usr/src/app
ENTRYPOINT [ "java", "-jar" , "/usr/src/app/JAXRS.jar" ] 
