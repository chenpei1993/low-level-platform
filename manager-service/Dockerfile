FROM openjdk:17-jdk-slim


ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8090

ADD /target/*.jar /manager-service.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /manager-service.jar +HeapDumpOnOutOfMemoryError --spring.profiles.active=$SPRING_PROFILES_ACTIVE"]

EXPOSE $PORT