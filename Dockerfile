FROM openjdk:8-jre-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
   APP_SLEEP=0 \
   JAVA_OPTS=""
ADD target/*.jar /discounts.jar
CMD echo "The application will starting..." && \
   sleep ${APP_SLEEP} && \
   java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /discounts.jar
EXPOSE 8080