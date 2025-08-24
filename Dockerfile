FROM ghcr.io/graalvm/native-image:21 as builder

WORKDIR /home/app

COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew nativeCompile --no-daemon

FROM oraclelinux:9-slim

ARG ARTIFACT_NAME=demo-mn-api

EXPOSE 8080

COPY --from=builder /home/app/build/native/nativeCompile/${ARTIFACT_NAME} .

ENTRYPOINT ["./${ARTIFACT_NAME}"]