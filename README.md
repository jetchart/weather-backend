# WeatherBackend

This project was generated with [Spring Boot 2.0.4].

## Development server

Run "Spring Boot App" for a dev server (http://localhost:8080/).

## Database

This project use MongoDB.<br>
	URL: mongodb://localhost:27017/weather<br>
	Database name: weather<br>
Use the dump located in "/src/main/resources/static/dbWeatherApp".
To do that, you can run the following command (first place at the root of the project): `mongorestore --db weather src/main/resources/static/dbWeatherApp/weather/`

