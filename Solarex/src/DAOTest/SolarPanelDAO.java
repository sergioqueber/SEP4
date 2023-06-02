package DAOTest;
import java.sql.*;
import model.*;
import java.util.*;
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

  public ThermalPanel createTh(double serialNo, int location,String installationDate, String status, int angle, double modelNo, int  factoryId, String type) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.solar_panel(serial_number,location,status,\"angle(°)\",model_no,factory_id, installation_Date) VALUES(?,?,?,?,?,?,?,?)");
      statement.setDouble(1,serialNo);
      statement.setInt(2,location);
      statement.setString(3,status);
      statement.setInt(4,angle);
      statement.setDouble(5,modelNo);
      statement.setInt(6,factoryId);
      statement.setString(7,type);
      statement.setString(8,installationDate);
      statement.executeUpdate();
      return new ThermalPanel(serialNo,location,installationDate,status,angle,new Model(modelNo),new Factory(factoryId),type);

    }
  }


  public ArrayList<PhotovoltaicPanel> readPvValues()throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time, voltage, intensity FROM solarex.solar_panel JOIN solarex.photovoltaic_panel pp ON solar_panel.serial_number = pp.solar_panel_sn");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<PhotovoltaicPanel> result = new ArrayList<>();
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
        double voltage = resultSet.getDouble(9);
        double intensity = resultSet.getDouble(10);
        PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo, location,
            installation_time, status, angle, new Model(model_no), new Factory(factory_id),
            type);
        pv.setIntensity(intensity);
        pv.setVoltage(voltage);
        result.add(pv);
      }
      return result;
    }
  }

  public ArrayList<ThermalPanel> readThValues()throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id,type,installation_time, \"initial_temp(°C)\",\"final_temp(°C)\",tp.\"ambient_temp(°C)\" FROM solarex.solar_panel JOIN solarex.thermal_panel tp ON solar_panel.serial_number = tp.solar_panel_sn");
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
        String installation_time = resultSet.getString(8);
        double initialTemp = resultSet.getDouble(9);
        double finalTemp = resultSet.getDouble(10);
        double ambientTemp = resultSet.getDouble(11);
        ThermalPanel th = new ThermalPanel(serialNo, location,
            installation_time, status, angle, new Model(model_no), new Factory(factory_id),
            type);
        th.setInitialTemp(initialTemp);
        th.setAmbient_temp(ambientTemp);
        th.setFinalTemp(ambientTemp);
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
        String installation_time = resultSet.getString(8);
        if(type.equals("Photovoltaic"))
        {
          PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo,location,installation_time, status,angle,new Model(model_no),new Factory(factory_id),type);
          result.add(pv);
        }
        else if (type.equals("Thermal")){
          ThermalPanel th = new ThermalPanel(serialNo,location,installation_time,status,angle,new Model(model_no),new Factory(factory_id),type);
          result.add(th);
        }
      }
      return result;
      }
    }

  public ArrayList<SolarPanel> readFilter(String filter, double search ) throws SQLException
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
        String installation_time = resultSet.getString(8);
        if (type.equals("Photovoltaic"))
        {
          PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo, location,
              installation_time, status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(pv);
        }
        else if (type.equals("Thermal"))
        {
          ThermalPanel th = new ThermalPanel(serialNo, location,
              installation_time, status, angle, new Model(model_no), new Factory(factory_id),
              type);
          result.add(th);
        }
      }
      return result;
    }

  }
  public void updatePv(double intensity, double voltage, double solar_flux,
      String timeStamp, Double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.photovoltaic_panel SET intensity = ?,voltage =?, solar_flux = ?, timestamp = ? WHERE solar_panel_sn = ?");
      statement.setDouble(1, intensity);
      statement.setDouble(2, voltage);
      statement.setDouble(3, solar_flux);
      statement.setString(4, timeStamp);
      statement.setDouble(5,sn);
      statement.executeUpdate();
    }
  }

  public void updateTh(double initialTemp, double finalTemp,double ambientTemp, double solar_flux,
      String timeStamp, Double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.thermal_panel SET \"initial_temp(°C)\" = ?, \"final_temp(°C)\"=?, \"ambient_temp(°C)\" = ?, solar_flux = ?,timestamp = ? WHERE solar_panel_sn = ?");
      statement.setDouble(1, initialTemp);
      statement.setDouble(2, finalTemp);
      statement.setDouble(3,ambientTemp);
      statement.setDouble(4, solar_flux);
      statement.setString(5, timeStamp);
      statement.setDouble(6,sn);
      statement.executeUpdate();
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
        PhotovoltaicPanel pv = new PhotovoltaicPanel(serialNo,location,status,angle,new Model(model_no),new Factory(factory_id),type);
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
  public void updatePV(double serialNo, int location, String status, int angle, double modelNo, int factoryId,
      String type) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.solar_panel SET location = ?, status = ?, \"angle(°)\" = ?, model_no = ?, factory_id = ?, type = ? WHERE serial_number = ?");
      statement.setDouble(7, serialNo);
      statement.setInt(1, location);
      statement.setString(2, status);
      statement.setInt(3, angle);
      statement.setDouble(4,modelNo);
      statement.setInt(5, factoryId);
      statement.setString(6, type);
      statement.executeUpdate();
    }
  }

  public void updateTH(double serialNo, int location, String status, int angle, double modelNo, int factoryId,
      String type) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.solar_panel SET location = ?, status = ?, \"angle(°)\" = ?, model_no = ?, factory_id = ?, type = ? WHERE serial_number = ?");
      statement.setDouble(7, serialNo);
      statement.setInt(1, location);
      statement.setString(2, status);
      statement.setInt(3, angle);
      statement.setDouble(4,modelNo);
      statement.setInt(5, factoryId);
      statement.setString(6, type);
      statement.executeUpdate();
    }
  }

}
