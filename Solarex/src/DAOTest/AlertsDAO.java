package DAOTest;

import model.Alerts;
import model.Notification;
import model.PhotovoltaicPanel;

import java.sql.*;
import java.util.ArrayList;

public class AlertsDAO
{
  private static AlertsDAO instance;

  private AlertsDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AlertsDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new AlertsDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<Alerts> readAlerts() throws SQLException{
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT alert.id,solar_panel_sn, notification_id,description From solarex.alert JOIN solarex.notification n ON n.id = alert.notification_id ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Alerts> alerts = new ArrayList<>();
      while (resultSet.next()){
        int alertId = resultSet.getInt(1);
        double solarPanelSn = resultSet.getDouble(2);
        int notificationId = resultSet.getInt(3);
        String description = resultSet.getString(4);
        Alerts alert = new Alerts(alertId, notificationId,solarPanelSn);
        alerts.add(alert);
      }
      return alerts;
   }
  }



  public void createNotificationIntensity(double intensity) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    ArrayList<PhotovoltaicPanel> panels = solarPanelDAO.readPvValues();


    for(int i = 0; i < panels.size(); i++){
      if(panels.get(i).getIntensity() < intensity){
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO solarex.alert(notification_id, solar_panel_sn) VALUES (?,?)");
          statement.setInt(1,1);
          statement.setDouble(2,panels.get(i).getSerial_number());
          statement.executeUpdate();
        }
      }
    }
  }
  public void createNotificationVoltage(double voltage) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    ArrayList<PhotovoltaicPanel> panels = solarPanelDAO.readPvValues();


    for(int i = 0; i < panels.size(); i++){
      if(panels.get(i).getVoltage() < voltage){
        try (Connection connection = getConnection())
        {
          PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO solarex.alert(notification_id, solar_panel_sn) VALUES (?,?)");
          statement.setInt(1,2);
          statement.setDouble(2,panels.get(i).getSerial_number());
          statement.executeUpdate();
        }
      }
    }
  }
}
