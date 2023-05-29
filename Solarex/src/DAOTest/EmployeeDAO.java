package DAOTest;

import model.Employee;
import model.Factory;
import model.Role;

import java.sql.*;

public class EmployeeDAO
{

  private static EmployeeDAO instance;

  public static synchronized EmployeeDAO getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new EmployeeDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz", "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public Employee createEmployee(int id, double CPR, String fName, String lNAme, String type, String email, double phoneNo, String employmentDate, Role role, Factory workPlace) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.employee(id, ssn, f_name, l_name, email, phone_number, date_of_employment, password, role_id, factory_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setDouble(2, CPR);
      statement.setString(3, fName);
      statement.setString(4, lNAme);
      statement.setString(5, type);
      statement.setString(6, email);
      statement.setDouble(7, phoneNo);
      statement.setString(8, employmentDate);
      statement.setInt(9, role.getId());
      statement.setInt(10, workPlace.getId());
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Employee(keys.getInt(1), CPR, fName, lNAme, type, email,
            phoneNo, employmentDate, role, workPlace);
      }
      else
      {
        throw new SQLException("No keys generated.");
      }
    }
  }

  public Employee readById(int id) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT password, role_id FROM solarex.employee WHERE id = ?");
      statement.setInt(1,id);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next()){
        String password = resultSet.getString("password");
        int roleId = resultSet.getInt("role_id");
        return new Employee(id,password, new Role(roleId));
      }
      else {
        return null;
      }
    }
  }


}


