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

  public PhotovoltaicPanel createpv(double serialNo, int location,String installationDate, boolean status, int angle, Model model, Factory factory, String type) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.solar_panel(serial_number,location,status,\"angle(°)\",model_no,factory_id, installation_Date) VALUES(?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setDouble(1,serialNo);
      statement.setInt(2,location);
      statement.setBoolean(3,status);
      statement.setInt(4,angle);
      statement.setDouble(5,model.getModelNo());
      statement.setInt(6,factory.getId());
      statement.setString(7,type);
      statement.setString(8,installationDate);
      statement.executeUpdate();
      return new PhotovoltaicPanel(serialNo,location,installationDate,status,angle,model,factory,type);

    }
  }
  public ArrayList<PhotovoltaicPanel> readPv()throws SQLException
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
        boolean status = resultSet.getBoolean(3);
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
        boolean status = resultSet.getBoolean(3);
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
        boolean status = resultSet.getBoolean(3);
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
}
