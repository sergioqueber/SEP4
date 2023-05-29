package DAOTest;

import model.Manufacturer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class ManufacturerDAO
{
  private static ManufacturerDAO instance;

  public static synchronized ManufacturerDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ManufacturerDAO();
    }
    return instance;
  }

  private ManufacturerDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public Manufacturer createManufacturer(String name, String email, double phoneNumber) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO solarex.manufacturer(name, email, phone_number) VALUES (?, ?, ?);");
      statement.setString(1, name);
      statement.setString(2, email);
      statement.setDouble(3, phoneNumber);
      statement.executeUpdate();
    }
    return null;
  }

  public Manufacturer editManufacturer(Manufacturer manufacturer) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE solarex.manufacturer SET name = ?, email = ?, phone_number = ?");
      statement.setString(1, manufacturer.getName());
      statement.setString(2, manufacturer.getEmail());
      statement.setDouble(3, manufacturer.getPhoneNumber());
      statement.executeUpdate();
    }
    return manufacturer;
  }

  public ArrayList<Manufacturer> readManufacturers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT name, email, phone_number FROM solarex.manufacturer");
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

  public ArrayList<Manufacturer> readByName(String searchString) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM solarex.manufacturer WHERE name LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Manufacturer> result = new ArrayList<>();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        Manufacturer manufacturer = new Manufacturer(name);
        result.add(manufacturer);
      }
      return result;
    }
  }

  public Manufacturer removeManufacturer(String name) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM solarex.manufacturer WHERE name LIKE ?");
      {
        statement.setString(1, "%" + name + "%");
        statement.executeUpdate();
      }
    }
    return null;
  }

}
