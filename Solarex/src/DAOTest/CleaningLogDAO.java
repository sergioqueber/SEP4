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

  public ArrayList<SolarPanel> getAllCleaningLogByTime(String startDate, String endDate) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT sp.serial_number, cl.cleaning_time, sp.location, sp.type FROM solarex.cleaning_log cl JOIN solarex.solar_panel sp on sp.serial_number = cl.serial_number WHERE cl.cleaning_time BETWEEN ? AND ?");
      statement.setString(1, startDate);
      statement.setString(2, endDate);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> results = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        String cleaningDate = resultSet.getString(2);
        int location = resultSet.getInt(3);
        String type = resultSet.getString(4);
        SolarPanel solarPanel = new SolarPanel(serialNo, cleaningDate, location,
            type);
        results.add(solarPanel);
      }
      return results;
    }
  }

  public ArrayList<SolarPanel> readCleaningLog() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT sp.serial_number, cl.cleaning_time, sp.location, sp.type FROM solarex.cleaning_log cl JOIN solarex.solar_panel sp on sp.serial_number = cl.serial_number");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> results = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        String cleaningDate = resultSet.getString(2);
        int location = resultSet.getInt(3);
        String type = resultSet.getString(4);
        SolarPanel solarPanel = new SolarPanel(serialNo, cleaningDate, location,
            type);
        results.add(solarPanel);
      }
      return results;
    }
  }

  public ArrayList<SolarPanel> getCleaningTimes() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT serial_number, location, cleaning_time, type FROM solarex.solar_panel");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> results = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        String cleaningDate = resultSet.getString(3);
        int location = resultSet.getInt(2);
        String type = resultSet.getString(4);
        SolarPanel solarPanel = new SolarPanel(serialNo, cleaningDate, location,
            type);
        results.add(solarPanel);
      }
      return results;
    }
  }

  public void addCleaningTime(double sn, String cleaningDate) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.solar_panel SET cleaning_time = ? WHERE serial_number = ?");
      statement.setString(1, cleaningDate);
      statement.setDouble(2, sn);
      statement.executeUpdate();
    }
  }

  public void editCleaningLogTime(double sn, String cleaningDate) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.cleaning_log SET cleaning_time = ? WHERE serial_number = ?");
      statement.setString(1, cleaningDate);
      statement.setDouble(2, sn);
      statement.executeUpdate();
    }
  }
}

