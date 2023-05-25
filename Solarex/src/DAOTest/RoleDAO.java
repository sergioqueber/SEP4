package DAOTest;
import java.sql.*;
import model.*;

public class RoleDAO
{
  private static RoleDAO instance;

  private RoleDAO() throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RoleDAO getInstance() throws SQLException{
    if (instance == null) {
      instance = new RoleDAO();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException{
    return DriverManager.getConnection("jdbc:postgresql://balarama.db.elephantsql.com:5432/osmxbusz", "osmxbusz", "m5YUAz0vMtIcjX3bmybJc7Kaz2STNoQ-");
  }

  public Role create(int id,String type) throws SQLException{
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO solarex.roles(id,type) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(2,type);
      statement.setInt(1,id);
      statement.executeUpdate();
      return new Role (id, type);

      }
    }
  }

