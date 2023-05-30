package DAOTest;

import model.Factory;
import model.Model;
import model.PhotovoltaicPanel;

import java.sql.*;
import java.util.ArrayList;

public class PvPanelLogDAO
{
  private static PvPanelLogDAO instance;

  private PvPanelLogDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized PvPanelLogDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new PvPanelLogDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<PhotovoltaicPanel> readValues(String startTime,
      String endTime, Double solarPanelSn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT intensity, voltage, timestamp, solar_flux FROM solarex.photovoltaic_panel_log WHERE timestamp BETWEEN ? and ? and solar_panel_sn = ?");
      statement.setString(1, startTime);
      statement.setString(2, endTime);
      statement.setDouble(3, solarPanelSn);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<PhotovoltaicPanel> result = new ArrayList<>();
      while (resultSet.next())
      {
        double intensity = resultSet.getDouble(1);
        double voltage = resultSet.getDouble(2);
        String timestamp = resultSet.getString(3);
        double solarFlux = resultSet.getDouble(4);
        PhotovoltaicPanel pv = new PhotovoltaicPanel(intensity, voltage,
            solarFlux, timestamp);
        result.add(pv);
      }
      return result;
    }
  }

  public ArrayList<String> getDatesInTimePeriod(String startTime,
      String endTime) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT DISTINCT timestamp FROM solarex.photovoltaic_panel_log WHERE timestamp BETWEEN ? and ?");
      statement.setString(1, startTime);
      statement.setString(2, endTime);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<String> results = new ArrayList<>();
      while (resultSet.next())
      {
        String timestamp = resultSet.getString(1);
        results.add(timestamp);
      }
      return results;
    }
  }

  public ArrayList<Double> readByTimePeriod(String startTime, String endTime)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT intensity, voltage, timestamp,solar_flux FROM solarex.photovoltaic_panel_log WHERE timestamp BETWEEN ? and ?");
      statement.setString(1, startTime);
      statement.setString(2, endTime);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<PhotovoltaicPanel> result = new ArrayList<>();
      ArrayList<Double> dailyEnergy = new ArrayList();
      while (resultSet.next())
      {
        double intensity = resultSet.getDouble(1);
        double voltage = resultSet.getDouble(2);
        String timestamp = resultSet.getString(3);
        double solarFlux = resultSet.getDouble(4);
        PhotovoltaicPanel pv = new PhotovoltaicPanel(intensity, voltage,
            solarFlux, timestamp);
        result.add(pv);
      }

      ArrayList<String> dates = getDatesInTimePeriod(startTime, endTime);

      for (int i = 0; i < dates.size(); i++)
      {
        double total = 0;
        System.out.println(dates.size());
        for (int j = 0; j < result.size(); j++)
        {
          if (result.get(j).getTimestamp().equals(dates.get(i)))
          {
            total = total + result.get(j).getPower();
          }
        }
        dailyEnergy.add(total);
      }
      System.out.println(dailyEnergy);
      return dailyEnergy;
    }
  }

  public void update(double intensity, double voltage, double solar_flux,
      String timeStamp, Double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE photovoltaic_panel SET intensity = ?,voltage =?, solar_flux = ?, timestamp = ? WHERE solar_panel_sn = ?");
      statement.setDouble(1, intensity);
      statement.setDouble(2, voltage);
      statement.setDouble(3, solar_flux);
      statement.setString(4, timeStamp);
      statement.setDouble(5,sn);
      statement.executeUpdate();
    }
  }
}
