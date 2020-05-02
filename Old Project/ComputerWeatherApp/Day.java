

/**
 * Represents the data for a single day.
 *
 * @author Elijah (@elijahff@uw.edu)
 * @version 1.0 (April 11, 2020)
 */
public class Day {

    /**Wind speed for this day.*/
    private String windSpeed;
    /**Wind direction for this day.*/
    private String windDirection;
    /**Temperature for this day.*/
    private String temperature;
    /**Humidity for this day.*/
    private String humidity;
    /**Barometric pressure for this day.*/
    private String barometricPressure;


    /**
     * Constructs an empty Day. Fills fields will null data.
     */
    public Day() {
    }

    /**
     * Overloaded constructor for Day object.
     *
     * @param windSpeed the speed of the wind.
     * @param windDirection direction of the wind.
     * @param temperature temperature of the day.
     * @param humidity humidity of the day.
     * @param barometricPressure barometric pressure of the day.
     */
    public Day(String windSpeed, String windDirection, String temperature, String humidity,String barometricPressure) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.temperature = temperature;
        this.humidity = humidity;
        this.barometricPressure = barometricPressure;
    }

    /**
     * Getter method for windSpeed.
     *
     * @return windSpeed.
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * Setter method for windSpeed.
     *
     * @param windSpeed wind speed for this day.
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Getter method for wind direction.
     *
     * @return windDirection
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Setter method for wind direction.
     *
     * @param windDirection direction of wind.
     */
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Getter method for temperature.
     *
     * @return temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Setter method for temperature.
     *
     * @param temperature temperature for the day
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * Getter method for humidity.
     *
     * @return humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Setter method for humidity.
     *
     * @param humidity humidity for the day.
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * Getter method for barometric pressure.
     *
     * @return barometricPressure
     */
    public String getBarometricPressure() {
        return barometricPressure;
    }

    /**
     * Setter method for Barometric Pressure.
     *
     * @param barometricPressure barometric pressure.
     */
    public void setBarometricPressure(String barometricPressure) {
        this.barometricPressure = barometricPressure;
    }


}
