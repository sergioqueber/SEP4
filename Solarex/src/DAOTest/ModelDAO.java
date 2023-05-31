package DAOTest;

import model.Manufacturer;
import model.Model;
import model.SolarPanel;

import java.sql.*;
import java.util.ArrayList;

public class ModelDAO
{

  private static ModelDAO instance;

  public static synchronized ModelDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ModelDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz", "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public Model createModel(double modelNo, double length, double width, Manufacturer manufacturer, double efficiency) throws SQLException
  {
    Model model = new Model(modelNo, length, width, manufacturer, efficiency);
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.model(model_no, \"area(m^2)\", \"length(m)\", \"width(m)\", \"manufacturer_name\", \"efficiency(%)\") VALUES (?, ?, ?, ?, ?, ?)" );
      statement.setDouble(1, model.getModelNo());
      statement.setDouble(2, model.getArea());
      statement.setDouble(3, model.getLength());
      statement.setDouble(4, model.getWidth());
      statement.setString(5, model.getManufacturer().getName());
      statement.setDouble(6, model.getEfficiency());
      statement.executeUpdate();
      return model;
    }
  }


  public ArrayList<Model> readModels() throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT model_no, \"area(m^2)\", \"length(m)\", \"width(m)\", \"efficiency(%)\", \"manufacturer_name\" FROM solarex.model ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Model> result = new ArrayList<>();
      while (resultSet.next())
      {
        int modelNo = resultSet.getInt(1);
        double length = resultSet.getDouble(3);
        double width = resultSet.getDouble(4);
        double efficiency = resultSet.getDouble(5);
        String manufacturer = resultSet.getString(6);
        Model model = new Model(modelNo, length, width, new Manufacturer(manufacturer), efficiency);
        result.add(model);
      }
      return result;
    }
  }

  public Model readByModelNo(int modelNo) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM solarex.model WHERE \"model_no\" = ?");
      statement.setInt(1, modelNo);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        double length = resultSet.getDouble(3);
        double width = resultSet.getDouble(4);
        double efficiency = resultSet.getDouble(5);
        String manufacturer = resultSet.getString(6);
        return new Model(modelNo, length, width, new Manufacturer(manufacturer), efficiency);
      }
      else
      {
        return null;
      }
    }
  }

  public SolarPanel deleteModel(double modelNo) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM solarex.model WHERE model_no = ?");
      {
        statement.setDouble(1,  modelNo);
        statement.executeUpdate();
      }
    }
    return null;
  }

}
