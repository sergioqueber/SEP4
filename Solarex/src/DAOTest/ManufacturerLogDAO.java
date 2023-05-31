package DAOTest;

import model.Manufacturer;

import java.sql.*;
import java.util.ArrayList;

public class ManufacturerLogDAO
{
  private static ManufacturerLogDAO instance;

  public static synchronized ManufacturerLogDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ManufacturerLogDAO();
    }
    return instance;
  }

  private ManufacturerLogDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<Manufacturer> readManufacturersLog() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT name, email, phone_number FROM solarex.manufacturer_log");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Manufacturer> result = new ArrayList<>();
      while (resultSet.next())
      {
        String name = resultSet.getString(1);
        String email = resultSet.getString(2);
        double phoneNumber = resultSet.getDouble(3);
        Manufacturer manufacturer = new Manufacturer(name, email, phoneNumber);
        result.add(manufacturer);
      }
      return result;
    }
  }
}
