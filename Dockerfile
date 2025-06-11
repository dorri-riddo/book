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

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}  
ENV DB_PASSWORD=${DB_PASSWORD}
ENV MAIL_HOST=${MAIL_HOST}
ENV MAIL_NAME=${MAIL_NAME}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]