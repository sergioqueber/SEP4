package DAOTest;

import model.*;

import java.sql.*;
import java.util.ArrayList;
public class SolarPanelDAO
{
  private static SolarPanelDAO instance;

  private SolarPanelDAO() throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized SolarPanelDAO getInstance() throws SQLException{
    if (instance == null) {
      instance = new SolarPanelDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException{
    return DriverManager.getConnection("jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz", "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public PhotovoltaicPanel createPv(double serialNo, int location, String status, int angle, double modelNo, int  factoryId, String type) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.solar_panel(serial_number,location,status,\"angle(°)\",model_no,factory_id, type) VALUES(?,?,?,?,?,?,?)");
      statement.setDouble(1,serialNo);
      statement.setInt(2,location);
      statement.setString(3,status);
      statement.setInt(4,angle);
      statement.setDouble(5,modelNo);
      statement.setInt(6,factoryId);
      statement.setString(7,type);
      statement.executeUpdate();
      return new PhotovoltaicPanel(serialNo,location, status,angle,new Model(modelNo),new Factory(factoryId),type);

    }
  }

  public ThermalPanel createTh(double serialNo, int location, String status, int angle, double modelNo, int  factoryId, String type) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.solar_panel(serial_number,location,status,\"angle(°)\",model_no,factory_id, type) VALUES(?,?,?,?,?,?,?)");
      statement.setDouble(1,serialNo);
      statement.setInt(2,location);
      statement.setString(3,status);
      statement.setInt(4,angle);
      statement.setDouble(5,modelNo);
      statement.setInt(6,factoryId);
      statement.setString(7,type);
      statement.executeUpdate();
      return new ThermalPanel(serialNo,location, status, angle,new Model(modelNo),new Factory(factoryId),type);

    }
  }


  public ArrayList<PhotovoltaicPanel> readPv()throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel WHERE type = ?");
      statement.setString(1,"Photovoltaic");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<PhotovoltaicPanel> result = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        int location = resultSet.getInt(2);
        String status = resultSet.getString(3);
        int angle = resultSet.getInt(4);
        int model_no = resultSet .getInt(5);
        int factory_id = resultSet.getInt(6);
        String type = resultSet.getString(7);
        PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo, location,
            status, angle, new Model(model_no), new Factory(factory_id),
            type);
        result.add(pv);
      }
      return result;
    }
  }

  public ArrayList<ThermalPanel> readTh()throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel WHERE type = ?");
      statement.setString(1, "Thermal");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<ThermalPanel> result = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        int location = resultSet.getInt(2);
        String status = resultSet.getString(3);
        int angle = resultSet.getInt(4);
        int model_no = resultSet.getInt(5);
        int factory_id = resultSet.getInt(6);
        String type = resultSet.getString(7);
        ThermalPanel th = new ThermalPanel(serialNo, location,
            status, angle, new Model(model_no), new Factory(factory_id),
            type);
        result.add(th);
      }
      return result;
    }
  }

  public ArrayList<SolarPanel> readAllPanels() throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> result = new ArrayList<>();
      while (resultSet.next()){
        double serialNo = resultSet.getDouble(1);
        int location = resultSet.getInt(2);
        String status = resultSet.getString(3);
        int angle = resultSet.getInt(4);
        int model_no = resultSet.getInt(5);
        int factory_id = resultSet.getInt(6);
        String type = resultSet.getString(7);
        if(type.equals("Photovoltaic"))
        {
          PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo,location, status,angle,new Model(model_no),new Factory(factory_id),type);
          result.add(pv);
        }
        else if (type.equals("Thermal")){
          ThermalPanel th = new ThermalPanel(serialNo,location,status,angle,new Model(model_no),new Factory(factory_id),type);
          result.add(th);
        }
      }
      return result;
      }
    }

  public ArrayList<SolarPanel> readFilter(String filter, int search ) throws SQLException
  {
    String query = null;
    if (filter.equals("sn"))
    {
      query = "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel WHERE serial_number = ?";
    }
    else if (filter.equals("location"))
    {
      query = "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel WHERE location = ?";
    }

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, search);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> result = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        int location = resultSet.getInt(2);
        String status = resultSet.getString(3);
        int angle = resultSet.getInt(4);
        int model_no = resultSet.getInt(5);
        int factory_id = resultSet.getInt(6);
        String type = resultSet.getString(7);
        String installation_time = resultSet.getString(8);
        if (type.equals("Photovoltaic"))
        {
          PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo, location,
               status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(pv);
        }
        else if (type.equals("Thermal"))
        {
          ThermalPanel th = new ThermalPanel(serialNo, location,
               status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(th);
        }
      }
      return result;
    }
  }

  public ArrayList<SolarPanel> readBySN(double search) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time FROM solarex.solar_panel WHERE serial_number = ?");
      statement.setDouble(1, search);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<SolarPanel> result = new ArrayList<>();
      while (resultSet.next())
      {
        double serialNo = resultSet.getDouble(1);
        int location = resultSet.getInt(2);
        String status = resultSet.getString(3);
        int angle = resultSet.getInt(4);
        int model_no = resultSet.getInt(5);
        int factory_id = resultSet.getInt(6);
        String type = resultSet.getString(7);
        if (type.equals("Photovoltaic"))
        {
          PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo, location,
              status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(pv);
        }
        else if (type.equals("Thermal"))
        {
          ThermalPanel th = new ThermalPanel(serialNo, location,
              status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(th);
        }
      }
      return result;
    }
  }

  public SolarPanel removePVPanel(int location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM solarex.solar_panel WHERE location = ?");
      {
        statement.setInt(1,  location);
        statement.executeUpdate();
      }
    }
    return null;
  }

  public SolarPanel removeTHPanel(int location) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM solarex.solar_panel WHERE location = ?");
      {
        statement.setInt(1,  location);
        statement.executeUpdate();
      }
    }
    return null;
  }

}
