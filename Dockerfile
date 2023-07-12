FROM openjdk:17
WORKDIR /app
COPY . /app
RUN ./mvnw clean
EXPOSE 8080
CMD ["java", "-jar", "target/WeatherForecastTelegramBot.jar"]