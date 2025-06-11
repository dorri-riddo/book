FROM gradle:8.5-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle/ gradle/
RUN gradle dependencies --no-daemon

COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine-jdk
WORKDIR /app

RUN apk add --no-cache curl tzdata && \
    addgroup -g 1001 appgroup && \
    adduser -D -u 1001 -G appgroup appuser

COPY --from=build /app/build/libs/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]