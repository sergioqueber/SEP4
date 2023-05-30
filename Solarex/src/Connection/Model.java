package Connection;

import DAOTest.*;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model
{
  public ArrayList<model.Model> getModels() throws SQLException
  {
    ModelDAO models = ModelDAO.getInstance();
    return models.readModels();
  }

  public PhotovoltaicPanel addPhotovoltaicPanel(double serialNo, int location, String status, int angle, double modelNo, int factoryId, String type) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createPv(serialNo,location, status,angle,modelNo,factoryId,type);
  }
  public ThermalPanel addThermalPanel(double serialNo, int location, String status, int angle, double modelNo, int factoryId, String type) throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createTh(serialNo,location, status,angle,modelNo,factoryId,type);
  }



  public ArrayList<SolarPanel> getAllPanels() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readAllPanels();
  }

  public ArrayList<PhotovoltaicPanel> getAllPVPanels() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readPv();
  }

  public ArrayList<ThermalPanel> getAllTHPanels() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readTh();
  }

  public SolarPanel deletePVPanel(SolarPanel solarPanel) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.removePVPanel(solarPanel.getLocation());
  }

  public SolarPanel deleteTHPanel(SolarPanel solarPanel) throws SQLException
{
  SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
  return solarPanelDAO.removeTHPanel(solarPanel.getLocation());
}

  public ArrayList<SolarPanel> getPanelsBySn(int filter) throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readFilter("sn",filter);
  }

  public ArrayList<SolarPanel> getPanelsBySerialNo(double sn) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readBySN(sn);
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

  public Manufacturer addManufacturer(String name, String email, double phoneNumber)
      throws SQLException
  {
    ManufacturerDAO manufacturerDAO = ManufacturerDAO.getInstance();
    return manufacturerDAO.createManufacturer(name, email, phoneNumber);
  }

  public ArrayList<Manufacturer> getManufacturers() throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.readManufacturers();
  }

  public ArrayList<Manufacturer> filterByName(String filter) throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.readByName(filter);
  }

  public ArrayList<Manufacturer> filterByEmail(String filter) throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.readByEmail(filter);
  }

  public Manufacturer deleteManufacturer(Manufacturer manufacturer)
      throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.removeManufacturer(manufacturer.getName());
  }

  public void updateManufacturer(String select, Manufacturer manufacturer) throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    manufacturers.editManufacturer(select, manufacturer);
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

    public void setAlertIntensity(double intensity) throws SQLException
    {
      AlertsDAO alertsDAO = AlertsDAO.getInstance();
      alertsDAO.createNotificationIntensity(intensity);

    }

    public void setAlertVoltage(double voltage)throws SQLException{
      AlertsDAO alertsDAO = AlertsDAO.getInstance();
      alertsDAO.createNotificationVoltage(voltage);
    }

    public ArrayList<Alerts> getAlerts() throws SQLException
    {
      AlertsDAO alertsDAO = AlertsDAO.getInstance();
      return alertsDAO.readAlerts();
    }

}
