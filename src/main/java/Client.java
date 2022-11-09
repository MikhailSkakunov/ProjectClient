import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {

        final String sensorName = "sensor_11";

        sendSensorRegistration(sensorName);

        Random random = new Random();

        double maxTemperature = 35;

        for (int i = 0; i < 50; i ++) {
            System.out.println(i);
            double temperature =  random.nextDouble() * maxTemperature;
            boolean conditionsRaining = random.nextBoolean();
            sendMeasurement(temperature, conditionsRaining, sensorName);
        }


    }

    private static void sendSensorRegistration(String sensorName) {
        final String url = "http://localhost:8080/sensor/registration";

        Map<String, Object> jsonDate = new HashMap<>();
        jsonDate.put("name", sensorName);

        sendJsonRequest(url, jsonDate);
    }


    private static void sendMeasurement(double airTemperature, boolean conditionsRaining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonDate = new HashMap<>();
        jsonDate.put("airTemperature", airTemperature);
        jsonDate.put("conditionsRaining", conditionsRaining);
        jsonDate.put("sensor", Map.of("name", sensorName));

        sendJsonRequest(url, jsonDate);
    }

    private static void sendJsonRequest(String url, Map<String, Object> jsonDate) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonDate, httpHeaders);

        try {
            restTemplate.postForObject(url,request, String.class);
            System.out.println("Успешно! Запрос отправлен на сервер.");
        } catch (HttpClientErrorException e) {
            System.out.println("Запрос отправлен с ОШИБКОЙ!");
            System.out.println(e.getMessage());
        }
    }
}
