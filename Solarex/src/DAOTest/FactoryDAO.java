package DAOTest;
import java.sql.*;
import model.*;
import java.util.*;
public class FactoryDAO
{
  private static FactoryDAO instance;

  private FactoryDAO() throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized FactoryDAO getInstance() throws SQLException{
    if (instance == null) {
      instance = new FactoryDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException{
    return DriverManager.getConnection("jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz", "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<Factory> read() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT id, heating_consumption, electricity_consumption FROM solarex.factory");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Factory> result = new ArrayList<>();

      while (resultSet.next())
      {
        int id = resultSet.getInt(1);
        double heatingConsumption = resultSet.getDouble(2);
        double electricityConsumption = resultSet.getDouble(3);
        Factory factory = new Factory(id, heatingConsumption,
            electricityConsumption);
        result.add(factory);
        System.out.println(factory);
      }
      return result;
    }
  }

}
