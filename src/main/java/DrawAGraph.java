import dto.MeasurementDTO;
import dto.MeasurementsResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawAGraph {

    public static void main(String[] args) {

        final List<Double> airTemperature = getResponseTemperature();

        drawGraph(airTemperature);

    }

    private static List<Double> getResponseTemperature() {

        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements/all";

        MeasurementsResponse measurementsResponse = restTemplate.getForObject(url, MeasurementsResponse.class);
        if (measurementsResponse == null || measurementsResponse.getMeasurementsDTO() == null)
            return Collections.emptyList();
        return measurementsResponse.getMeasurementsDTO().stream().map(MeasurementDTO::getAirTemperature)
                .collect(Collectors.toList());
    }


    private static void drawGraph(List<Double> airTemperature) {
        double[] xData = IntStream.range(0, airTemperature.size()).asDoubleStream().toArray();
        double[] yData = airTemperature.stream().mapToDouble(x -> x).toArray();

        XYChart xyChart = QuickChart.getChart("Air Temperatures", "X", "t", "temperature", xData, yData);

        SwingWrapper swingWrapper = new SwingWrapper<>(xyChart);
        swingWrapper.displayChart();
    }







}
