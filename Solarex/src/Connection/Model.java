package Connection;

import DAOTest.FactoryDAO;
import DAOTest.PvPanelLogDAO;
import DAOTest.SolarPanelDAO;
import DAOTest.FactoryDAO;
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

  public ArrayList<Factory> getFactories() throws SQLException
  {
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    return factoryDAO.read();
  }

  public double getHeatingConsumption()throws SQLException{
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    System.out.println(factoryDAO.read().get(0).getHeatingConsumption());
    return factoryDAO.read().get(0).getHeatingConsumption();
  }

  public double getElectricityConsumption()throws SQLException{
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    return factoryDAO.read().get(0).getElectricityConsumption();
  }

  public double getSavings(double electricityPrice)throws SQLException{
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    return ((factoryDAO.read().get(0).getElectricityConsumption()) *(electricityPrice - getGeneration() * electricityPrice));
  }

  public double getGeneration ()throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    double generation = 0;
    for(int i = 0; i < solarPanelDAO.readPv().size(); i++){
      generation = generation + solarPanelDAO.readPv().get(i).getPower();
      }
    return generation;
    }
    public ArrayList<Double> getGenerationByTimePeriod(String startDate, String endDate) throws SQLException{
      PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
      System.out.println(pvPanelLogDAO.getDatesInTimePeriod(startDate,endDate));
      return pvPanelLogDAO.readByTimePeriod(startDate,endDate);
    }

    public ArrayList<String> getDaysInTimePeriod(String startDate, String endDate)
        throws SQLException
    {
      PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
      return pvPanelLogDAO.getDatesInTimePeriod(startDate,endDate);
    }
  }