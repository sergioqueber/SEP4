package Connection;

import DAOTest.SolarPanelDAO;
import model.Factory;
import model.PhotovoltaicPanel;
import model.SolarPanel;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model
{
  public PhotovoltaicPanel addPhotovoltaicPanel(double serialNo, int location, String installationDate, boolean status, int angle, model.Model model, Factory factory, String type) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createpv(serialNo,location, installationDate,status,angle,model,factory,type);
  }

  public ArrayList<SolarPanel> getAllPanels() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readAllPanels();
  }

  public ArrayList<SolarPanel> getPanelsBySn(int filter) throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readFilter("sn",filter);
  }

  public ArrayList<SolarPanel> getPanelsByLocation(int filter)throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readFilter("location",filter);
  }

  public double getGeneration ()throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    double generation = 0;
    for(int i = 0; i < solarPanelDAO.readPv().size(); i++){
      generation = generation + solarPanelDAO.readPv().get(i).getPower();
      }
    return generation;
    }
  }
