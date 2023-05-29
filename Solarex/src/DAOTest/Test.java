package DAOTest;

import java.sql.SQLException;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    solarPanelDAO.readPv();
    solarPanelDAO.readTh();

  }
}
