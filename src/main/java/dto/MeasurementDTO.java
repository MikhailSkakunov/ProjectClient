package dto;

public class MeasurementDTO {
    private Double airTemperature;

    private Boolean conditionsRaining;

    private SensorDTO sensor;

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Boolean isConditionsRaining() {
        return conditionsRaining;
    }

    public void setConditionsRaining(Boolean conditionsRaining) {
        this.conditionsRaining = conditionsRaining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
