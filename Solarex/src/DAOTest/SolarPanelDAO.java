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

  public PhotovoltaicPanel create(double serialNo, int location, boolean status, int angle, Model model, Factory factory) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.solar_panel(serial_number,location,status,\"angle(°)\",model_no,factory_id) VALUES(?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setDouble(1,serialNo);
      statement.setInt(2,location);
      statement.setBoolean(3,status);
      statement.setInt(4,angle);
      statement.setDouble(5,model.getModelNo());
      statement.setInt(6,factory.getId());
      statement.executeUpdate();
      return new PhotovoltaicPanel(serialNo,location,status,angle,model,factory);

    }
  }

  public ArrayList<SolarPanel> readAllPanels(){
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT serial_number,location,status,\"angle(°)\",model_no,factory_id")
    }
  }
}
