
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

  public class Generator {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    private static final int SERIES_ID = 1;
    private static final BigDecimal MIN_COLLECTION_TIME_PERIOD = BigDecimal.valueOf(1.0);
    private static final BigDecimal MAX_COLLECTION_TIME_PERIOD = BigDecimal.valueOf(5.0);
    private static final BigDecimal MIN_WATER_MASS = BigDecimal.valueOf(10.0);
    private static final BigDecimal MAX_WATER_MASS = BigDecimal.valueOf(20.0);
    private static final BigDecimal MIN_OUTDOOR_TEMP = BigDecimal.valueOf(20.0);
    private static final BigDecimal MAX_OUTDOOR_TEMP = BigDecimal.valueOf(30.0);
    private static final BigDecimal MIN_HOT_WATER_TEMP = BigDecimal.valueOf(35.0);
    private static final BigDecimal MAX_HOT_WATER_TEMP = BigDecimal.valueOf(45.0);
    private static final BigDecimal MIN_COLD_WATER_TEMP = BigDecimal.valueOf(10.0);
    private static final BigDecimal MAX_COLD_WATER_TEMP = BigDecimal.valueOf(20.0);
    private static final BigDecimal MIN_SOLAR_FLUX = BigDecimal.valueOf(500.0);
    private static final BigDecimal MAX_SOLAR_FLUX = BigDecimal.valueOf(1000.0);
    private static final BigDecimal MIN_WATER_FLOW = BigDecimal.valueOf(2.0);
    private static final BigDecimal MAX_WATER_FLOW = BigDecimal.valueOf(5.0);

    public static void main(String[] args) {
      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        while (true) {
          LocalDateTime timestamp = LocalDateTime.now();
          BigDecimal collectionTimePeriod = generateRandomValue(MIN_COLLECTION_TIME_PERIOD, MAX_COLLECTION_TIME_PERIOD);
          BigDecimal collectedWaterMass = generateRandomValue(MIN_WATER_MASS, MAX_WATER_MASS);
          BigDecimal outdoorTemperature = generateRandomValue(MIN_OUTDOOR_TEMP, MAX_OUTDOOR_TEMP);
          BigDecimal hotWaterTemperature = generateRandomValue(MIN_HOT_WATER_TEMP, MAX_HOT_WATER_TEMP);
          BigDecimal coldWaterTemperature = generateRandomValue(MIN_COLD_WATER_TEMP, MAX_COLD_WATER_TEMP);
          BigDecimal solarFlux = generateRandomValue(MIN_SOLAR_FLUX, MAX_SOLAR_FLUX);
          BigDecimal waterFlow = generateRandomValue(MIN_WATER_FLOW, MAX_WATER_FLOW);
          BigDecimal pOut = BigDecimal.valueOf(0.95).multiply(solarFlux).multiply(waterFlow);
          BigDecimal pIn = BigDecimal.valueOf(0.95).multiply(solarFlux);
          BigDecimal efficiency = pOut.divide(pIn, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100.0));

          insertThermoMeasurement(connection, timestamp, SERIES_ID, collectionTimePeriod, collectedWaterMass, outdoorTemperature, hotWaterTemperature, coldWaterTemperature, solarFlux, waterFlow, pOut, pIn, efficiency);

          System.out.println("Inserted Thermo measurement: " + timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

          // Sleep for 1 hour
          Thread.sleep(3600000);
        }
      } catch (SQLException | InterruptedException e) {
        e.printStackTrace();
      }
    }

    private static void insertThermoMeasurement(Connection connection, LocalDateTime timestamp, int seriesId, BigDecimal collectionTimePeriod, BigDecimal collectedWaterMass, BigDecimal outdoorTemperature, BigDecimal hotWaterTemperature, BigDecimal coldWaterTemperature, BigDecimal solarFlux, BigDecimal waterFlow, BigDecimal pOut, BigDecimal pIn, BigDecimal efficiency) throws SQLException {
      String query = "INSERT INTO thermo_measurements (timestamp, series_id, collection_time_period, collected_water_mass, outdoor_temperature, hot_water_temperature, cold_water_temperature, solar_flux, water_flow, pout, pin, efficiency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setTimestamp(1, Timestamp.valueOf(timestamp));
        statement.setInt(2, seriesId);
        statement.setBigDecimal(3, collectionTimePeriod);
        statement.setBigDecimal(4, collectedWaterMass);
        statement.setBigDecimal(5, outdoorTemperature);
        statement.setBigDecimal(6, hotWaterTemperature);
        statement.setBigDecimal(7, coldWaterTemperature);
        statement.setBigDecimal(8, solarFlux);
        statement.setBigDecimal(9, waterFlow);
        statement.setBigDecimal(10, pOut);
        statement.setBigDecimal(11, pIn);
        statement.setBigDecimal(12, efficiency);
        statement.executeUpdate();
      }
    }

    private static BigDecimal generateRandomValue(BigDecimal minValue, BigDecimal maxValue) {
      Random random = new Random();
      BigDecimal range = maxValue.subtract(minValue);
      BigDecimal scaled = range.multiply(BigDecimal.valueOf(random.nextDouble()));
      return scaled.add(minValue);
    }
  }

