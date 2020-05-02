
import java.util.ArrayList;

/**
 * Provides a mechanism to calculate information about the weather
 * provided limited data from the sensors.
 *
 * @author Elijah (@elijahff@uw.edu)
 * @version 1.0 (April 11, 2020)
 */
public class WeatherCalculations {

    /**
     * Calculates the average temperature for a year (365 days).
     *
     * @param days contains data for each day.
     * @return average temperature in a year.
     */
    public double tempAvgYear(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 365) {
            for (int i = 0; i < 365; i++) {
                avg += Integer.parseInt(days.get(i).getTemperature());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getTemperature());
            }
        }
        return avg/365.0;
    }

    /**
     * Calculates average temperature over 30 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average temperature.
     */
    public double tempAvgMonth(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 30) {
            for (int i = 0; i < 30; i++) {
                avg += Integer.parseInt(days.get(i).getTemperature());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getTemperature());
            }
        }
        return avg/30.0;
    }

    /**
     * Calculates the average humidity over 30 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average humidity.
     */
    public double humidityAvgMonth(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 30) {
            for (int i = 0; i < 30; i++) {
                avg += Integer.parseInt(days.get(i).getHumidity());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getHumidity());
            }
        }
        return avg/30.0;
    }

    /**
     * Calculates the average humidity over 365 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average humidity
     */
    public double humidityAvgYear(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 365) {
            for (int i = 0; i < 365; i++) {
                avg += Integer.parseInt(days.get(i).getHumidity());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getHumidity());
            }
        }
        return avg/365.0;

    }

    /**
     * Calculates the average atmospheric pressure over 30 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average atmospheric pressure.
     */
    public double atmAvgMonth(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 30) {
            for (int i = 0; i < 30; i++) {
                avg += Integer.parseInt(days.get(i).getBarometricPressure());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getBarometricPressure());
            }
        }
        return avg/30.0;
    }

    /**
     * Calculates the average atmospheric pressure over 365 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average atmospheric pressure.
     */
    public double atmAvgYear(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 365) {
            for (int i = 0; i < 365; i++) {
                avg += Integer.parseInt(days.get(i).getBarometricPressure());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getBarometricPressure());
            }
        }
        return avg/365.0;
    }

    /**
     * Calculates the average wind speed over 30 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average wind speed.
     */
    public double windSpeedAvgMonth(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 30) {
            for (int i = 0; i < 30; i++) {
                avg += Integer.parseInt(days.get(i).getWindSpeed());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getWindSpeed());
            }
        }
        return avg/30.0;
    }

    /**
     * Calculates average wind speed over 365 days.
     *
     * @param days (list of days in which data was collected.)
     * @return average wind speed
     */
    public double windSpeedAvgYear(ArrayList<Day> days) {
        int avg = 0;
        if (days.size() >= 365) {
            for (int i = 0; i < 365; i++) {
                avg += Integer.parseInt(days.get(i).getWindSpeed());
            }
        } else {
            for (Day day : days) {
                avg += Integer.parseInt(day.getWindSpeed());
            }
        }
        return avg/365.0;
    }

    /**
     * Calculates the windchill based on formula:
     * Wind Chill = 35.74 + .6215*Temp - 35.75*((Wind Velocity)^.16) + .4275*Temp(Wind Velocity)^.16)
     *
     * @see <a href="https://sciencing.com/calculate-wind-chill-factor-5981683.html">Calculate WindChill</a> On how
     * to calculate windchill.
     * @param days a list of consecutive days.
     * @return windChill factor
     */
    public double windChill(ArrayList<Day> days) {

        Day lastDay = days.get(days.size()-1);
        double temp = Double.parseDouble(lastDay.getTemperature());
        double windSpeed = Double.parseDouble(lastDay.getWindSpeed());
        double windChillFactor = 35.74 + .6215*temp - 35.75*(Math.pow(windSpeed,.16)) + .4275*temp*Math.pow(windSpeed,.16);
        return windChillFactor;
    }

    /**
     * Calculates the heat Index for a given day.
     * Forumla is based on formula provided by the National Weather Service.
     * Multiple regression analysis carried out by Lans P. Rothfusz.
     *
     * @see <a href="https://www.wpc.ncep.noaa.gov/html/heatindex_equation.shtml">Calcuate Heat Index</a> references used.
     *
     */
    public double heatIndex(ArrayList<Day> days) {
        Day lastDay = days.get(days.size()-1);
        double temp = Double.parseDouble(lastDay.getTemperature());
        double humidity = Double.parseDouble(lastDay.getHumidity());

        double formula = -42.3798 + 2.049001523*temp + 10.14333127*humidity
                - .22475541*temp*humidity - .00683783*temp*temp - .05481717*humidity*humidity
                + .00122874*temp*temp*humidity + .00085252*temp*humidity*humidity
                -.00000199*temp*temp*humidity*humidity;

        //TODO - make temp adjustment correction according to original formula.
        return formula;
    }


    /**
     * Calculates the dew point for a given day.
     *
     * @see <a href="https://iridl.ldeo.columbia.edu/dochelp/QA/Basic/dewpoint.html">Dew Point</a>
     */
    public double dewPoint(ArrayList<Day> days) {
        double dewPointResult = -1;
        Day lastDay = days.get(days.size() - 1);
        double temp = Double.parseDouble(lastDay.getTemperature());
        double humidity = Double.parseDouble(lastDay.getHumidity());

        dewPointResult = temp - ((100-humidity)/5);

        return dewPointResult;
    }

    //TODO - get data for solar radiation.

//    /**
//     * Calculates the solar radiation.
//     */
//    public double solarRadtion(ArrayList<Day> days) {
//        return -1;
//    }

    //TODO - get data for rainfall.

//    /**
//     * Average Rainfall for a given month.
//     *
//     * @param days days of data collection.
//     * @return average rainfall for the month.
//     */
//    public double avgRainfallMonth(ArrayList<Day> days) {
//        int avg = 0;
//        if (days.size() >= 30) {
//            for (int i = 0; i < 30; i++) {
//                avg += Integer.parseInt(days.get(i).getRainfall());
//            }
//        } else {
//            for (int i = 0; i < days.size(); i++) {
//                avg += Integer.parseInt(days.get(i).getRainfall());
//            }
//        }
//        return avg/30.0;
//    }


    //TODO - get data for rainfall.

//    /**
//     * Average rainfall for a given year.
//     *
//     * @param days days of data collection.
//     * @return average rainfall for the year.
//     */
//    public double avgRainfallYear(ArrayList<Day> days) {
//        int avg = 0;
//        if (days.size() >= 365) {
//            for (int i = 0; i < 365; i++) {
//                avg += Integer.parseInt(days.get(i).getRainfall());
//            }
//        } else {
//            for (int i = 0; i < days.size(); i++) {
//                avg += Integer.parseInt(days.get(i).getRainfall());
//            }
//        }
//        return avg/365.0;
//    }

}
