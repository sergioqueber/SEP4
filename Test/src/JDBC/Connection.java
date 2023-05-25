package JDBC;

import java.sql.DriverManager;


public class Connection
{
  private java.sql.Connection connection;

  public Connection()
  {
  }

  public void getConnection()
  {
    String host = "localhost";
    int portNo = 4532;
    String userName = "postgres";
    String password = "postgres";
    String url = "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

    try {
      this.connection = (java.sql.Connection) DriverManager.getConnection(url, userName, password);
      System.out.println("Connection established to: " + url);
    } catch (Exception var8) {
      var8.printStackTrace();
    }
  }




}
