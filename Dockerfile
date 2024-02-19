FROM openjdk:18-ea-8-jdk-slim

WORKDIR /app

# Копируем JAR файл в контейнер
#COPY target/app-1.0.jar /app/app-1.0.jar
COPY target/ /app
EXPOSE 8080

# Запускаем Java приложение при старте контейнера
CMD ["java", "-jar", "app.jar"]