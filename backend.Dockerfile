FROM gradle:8.6-alpine AS BUILD
WORKDIR /usr/app
COPY . .
RUN gradle :spotlessApply
RUN gradle build

FROM openjdk:17-alpine
WORKDIR /root/
COPY --from=BUILD /usr/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
