package DAOTest;

import model.Employee;
import model.Repairs;
import model.SolarPanel;

import java.sql.*;
import java.util.ArrayList;

public class RepairsDAO
{
  private static RepairsDAO instance;

  private RepairsDAO() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RepairsDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RepairsDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz",
        "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public ArrayList<Repairs> readRepairsBySn(Double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT id,employee_id,date_of_repair From solarex.repairs WHERE solar_panel_sn = ?");
      statement.setDouble(1, sn);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Repairs> repairs = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt(1);
        int employeeId = resultSet.getInt(2);
        String dateOfRepair = resultSet.getString(3);
        Repairs repair = new Repairs(id, new Employee(id), dateOfRepair, new SolarPanel(sn));
        repairs.add(repair);
      }
      return repairs;
    }
  }
  public ArrayList<Repairs> readRepairs() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT id,employee_id,date_of_repair,solar_panel_sn From solarex.repairs");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Repairs> repairs = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt(1);
        int employeeId = resultSet.getInt(2);
        String dateOfRepair = resultSet.getString(3);
        double sn = resultSet.getDouble(4);
        Repairs repair = new Repairs(id, new Employee(id), dateOfRepair, new SolarPanel(sn));
        repairs.add(repair);
      }
      return repairs;
    }
  }
  public ArrayList<Repairs> readRepairsByDate(String startDate, String endDate) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT id,employee_id,date_of_repair,solar_panel_sn From solarex.repairs WHERE date_of_repair BETWEEN ? and ?");
      statement.setString(1,startDate);
      statement.setString(2,endDate);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Repairs> repairs = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt(1);
        int employeeId = resultSet.getInt(2);
        String dateOfRepair = resultSet.getString(3);
        double sn = resultSet.getDouble(4);
        Repairs repair = new Repairs(id, new Employee(id), dateOfRepair, new SolarPanel(sn));
        repairs.add(repair);
      }
      return repairs;
    }
  }
  public Repairs createRepair(int employeeId, String dateOfRepair, double sn) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.repairs (employee_id, date_of_repair, solar_panel_sn) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1,employeeId);
      statement.setString(2,dateOfRepair);
      statement.setDouble(3,sn);
      statement.executeUpdate();
      ResultSet keys =  statement.getGeneratedKeys();
      if (keys.next()) {
        return new Repairs(keys.getInt(1),new Employee(employeeId),dateOfRepair,new SolarPanel(sn));
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }
  public void removeRepair(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM solarex.repairs WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}
