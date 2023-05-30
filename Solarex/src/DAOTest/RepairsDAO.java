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
}
