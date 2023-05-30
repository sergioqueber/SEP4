package DAOTest;

import model.SolarPanel;

import java.sql.*;
import java.util.ArrayList;

public class CleaningLogDAO
{
  private static CleaningLogDAO instance;

  private CleaningLogDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized CleaningLogDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new CleaningLogDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<SolarPanel> readCleaningLog(double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT cleaning_time FROM solarex.cleaning_log WHERE serial_number = ?");
      statement.setDouble(1, sn);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> results = new ArrayList<>();
      while (resultSet.next())
      {
        String cleaningDate = resultSet.getString(1);
        results.add(new SolarPanel(sn, cleaningDate));
      }
      return results;
    }

  }
}
