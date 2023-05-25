package DAOTest;

import model.Role;

import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    RoleDAO dao = RoleDAO.getInstance();
    dao.create(2,"Hola");
  }
}
