FROM openjdk:17-jdk-slim


ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8091

ADD /target/*.jar /question-service.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /question-service.jar +HeapDumpOnOutOfMemoryError --spring.profiles.active=$SPRING_PROFILES_ACTIVE"]

EXPOSE $PORT