FROM gradle:8.6-alpine AS BUILD
WORKDIR /usr/app
COPY . .
RUN gradle :spotlessApply
RUN gradle build -x test

FROM eclipse-temurin:21-alpine
WORKDIR /root/
COPY --from=BUILD /usr/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
