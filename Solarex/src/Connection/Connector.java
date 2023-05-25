package Connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector
{
  //This is the class that will handle all interaction with the database.
  /*private Connection connection;

  private Connector() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public void connect()
  {

  String host = "localhost";
  int portNo = 5432;
  String user = "postgresql";
  String password = "postgresql";
  String dBurl = "jdbc:postgresql://" + "" + host + ":" + portNo + "/" + user;

  try {
    connection = DriverManager.getConnection(dBurl, user, password);
    System.out.println("Connected to" + dBurl);
  }
  catch (SQLException e)
  {
    System.out.println("Failed to connect.");
  }


  }*/
}
