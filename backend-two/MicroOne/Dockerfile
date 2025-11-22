# ---------- STAGE 1: BUILD ----------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Копируем только pom.xml и скачиваем зависимости — это ускоряет последующие сборки
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Копируем весь проект
COPY . .

# Собираем jar
RUN mvn -q package -DskipTests


# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Копируем JAR из билд-стадии
COPY --from=builder /app/target/*-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "echo 'Контейнер стартовал!' && java -jar app.jar"]