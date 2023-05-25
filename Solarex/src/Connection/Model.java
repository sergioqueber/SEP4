package Connection;

import DAOTest.SolarPanelDAO;
import model.Factory;
import model.PhotovoltaicPanel;

import java.sql.SQLException;

public class Model
{
  public PhotovoltaicPanel addPhotovoltaicPanel(double serialNo, int location, boolean status, int angle, model.Model model, Factory factory) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.create(serialNo,location,status,angle,model,factory);
  }
}
