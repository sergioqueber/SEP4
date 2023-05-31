package Connection;

import DAOTest.*;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model
{

  private String lastOverview;
  private String adminPassword = "1234";
  private double selectedSn;
  private String selectedType;

  public void setSelectedType(String selectedType)
  {
    this.selectedType = selectedType;
  }

  public String getSelectedType()
  {
    return selectedType;
  }

  public double getSelectedSn()
  {
    return selectedSn;
  }

  public void setSelectedSn(double selectedSn)
  {
    this.selectedSn = selectedSn;
  }

  public void setLastOverview(String lastOverview)
  {
    this.lastOverview = lastOverview;
  }

  public String getLastOverview()
  {
    return lastOverview;
  }

  public String getAdminPassword()
  {
    return adminPassword;
  }

  public ArrayList<model.Model> getModels() throws SQLException
  {
    ModelDAO models = ModelDAO.getInstance();
    return models.readModels();
  }

  public void addModel(double modelNo, double length, double width, Manufacturer manufacturer, double efficiency) throws SQLException
  {
    ModelDAO models = ModelDAO.getInstance();
    models.createModel(modelNo, length, width, manufacturer, efficiency);
  }

  public PhotovoltaicPanel addPhotovoltaicPanel(double serialNo, int location,
      String status, int angle, double modelNo, int factoryId, String type)
      throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createPv(serialNo, location, status, angle, modelNo,
        factoryId, type);
  }

  public ThermalPanel addThermalPanel(double serialNo, int location, String status, int angle, double modelNo, int factoryId, String type)
      throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createTh(serialNo, location, status, angle, modelNo,
        factoryId, type);
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

  public ArrayList<SolarPanel> getPanelsBySn(double filter) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readFilter("sn", filter);
  }

  public ArrayList<SolarPanel> getPanelsBySerialNo(double sn) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readBySN(sn);
  }

  public ArrayList<SolarPanel> getPanelsByLocation(int filter)
      throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readFilter("location", filter);
  }

  public ArrayList<SolarPanel> getPanelsByTime(String startDate, String endDate)
      throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    return cleaningLogDAO.getAllCleaningLogByTime(startDate, endDate);
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

  public void updateManufacturer(String select, Manufacturer manufacturer)
      throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    manufacturers.editManufacturer(select, manufacturer);
  }

  public void editPVpanel(double serialNo, int location, String status, int angle, double modelNo, int factoryId, String type) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    solarPanelDAO.updatePV(serialNo, location, status, angle, modelNo, factoryId, type);
  }

  public void editTHpanel(double serialNo, int location, String status, int angle, double modelNo, int factoryId, String type) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    solarPanelDAO.updateTH(serialNo, location, status, angle, modelNo, factoryId, type);
  }

  public double getHeatingConsumption() throws SQLException
  {
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    System.out.println(factoryDAO.read().get(0).getHeatingConsumption());
    return factoryDAO.read().get(0).getHeatingConsumption();
  }

  public double getElectricityConsumption() throws SQLException
  {
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    return factoryDAO.read().get(0).getElectricityConsumption();
  }

  public int getSavings(double electricityPrice, double sellingPrice)
      throws SQLException
  {
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    double savings = 0;
    if (getGeneration() > (factoryDAO.read().get(0).getElectricityConsumption()))
    {
      savings = (((factoryDAO.read().get(0).getElectricityConsumption())
          * electricityPrice) + (getGeneration() * sellingPrice));
    }
    else if (getGeneration() < (factoryDAO.read().get(0).getElectricityConsumption()))
    {
      savings = (((factoryDAO.read().get(0).getElectricityConsumption())
          * electricityPrice) - (getGeneration() * electricityPrice));
    }
    return (int) savings;
  }

  public double getGeneration() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    double generation = 0;
    for (int i = 0; i < solarPanelDAO.readPv().size(); i++)
    {
      generation = generation + solarPanelDAO.readPv().get(i).getPower();
    }
    return generation;
  }

  public ArrayList<Double> getGenerationByTimePeriod(String startDate, String endDate) throws SQLException
  {
    PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
    System.out.println(pvPanelLogDAO.getDatesInTimePeriod(startDate, endDate));
    return pvPanelLogDAO.readByTimePeriod(startDate, endDate);
  }

  public ArrayList<String> getDaysInTimePeriod(String startDate, String endDate)
      throws SQLException
  {
    PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
    return pvPanelLogDAO.getDatesInTimePeriod(startDate, endDate);
  }

  public void setAlertIntensity(double intensity) throws SQLException
  {
    AlertsDAO alertsDAO = AlertsDAO.getInstance();
    alertsDAO.createNotificationIntensity(intensity);

  }

  public void setAlertVoltage(double voltage) throws SQLException
  {
    AlertsDAO alertsDAO = AlertsDAO.getInstance();
    alertsDAO.createNotificationVoltage(voltage);
  }

  public ArrayList<Alerts> getAlerts() throws SQLException
  {
    AlertsDAO alertsDAO = AlertsDAO.getInstance();
    return alertsDAO.readAlerts();
  }

  public boolean logInCheck(int id, String password) throws SQLException
  {
    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    return employeeDAO.readById(id).getPassword().equals(password);
  }

  public Employee getEmployee(int id) throws SQLException
  {
    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    return employeeDAO.readById(id);
  }

  public void registerEmployee(String CPR, String fName, String lNAme, String email, double phoneNo, String employmentDate,
      String password, Role role, Factory workPlace) throws SQLException
  {
    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    employeeDAO.createEmployee(CPR, fName, lNAme, email, phoneNo, employmentDate,
        password, role, workPlace);
  }

  public ArrayList<PhotovoltaicPanel> getPvLogValues(String startTime, String endTime, Double sn) throws SQLException
  {
    PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
    return pvPanelLogDAO.readValues(startTime, endTime, sn);
  }

  public ArrayList<ThermalPanel> getThLogValues(String startTime, String endTime, Double sn) throws SQLException
  {
    ThPanelLogDAO thPanelLogDAO = ThPanelLogDAO.getInstance();
    return thPanelLogDAO.readValues(startTime, endTime, sn);
  }

  public double getIndividualGeneration(Double sn) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    double generation = 0;
    for (int i = 0; i < solarPanelDAO.readPvValues().size(); i++)
    {
      if (solarPanelDAO.readPvValues().get(i).getSerial_number() == sn)
      {
        generation = solarPanelDAO.readPvValues().get(i).getPower();
      }
    }
    return generation;
  }

  public ArrayList<Repairs> getRepairsBySn(double sn) throws SQLException
  {
    RepairsDAO repairsDAO = RepairsDAO.getInstance();
    return repairsDAO.readRepairsBySn(sn);
  }

  public ArrayList<SolarPanel> getCleaningLogBySn(double sn) throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    return cleaningLogDAO.readCleaningLog(sn);
  }

  public ArrayList<SolarPanel> getCleaningLog() throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    return cleaningLogDAO.readCleaningLog();
  }

  public void addCleaning(double sn, String date) throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    cleaningLogDAO.addCleaningTime(sn, date);
  }

  public void editLogCleaning(double sn, String date) throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    cleaningLogDAO.editCleaningLogTime(sn, date);
  }

  public ArrayList<SolarPanel> getLastCleaning() throws SQLException
  {
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    return cleaningLogDAO.getCleaningTimes();
  }

  public void newPvLecture(double intensity, double voltage, double solar_flux,
      String timeStamp, Double sn) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    solarPanelDAO.updatePv(intensity, voltage, solar_flux, timeStamp, sn);
  }

  public void newThLecture(double initialTemp, double finalTemp,double ambientTemp, double solar_flux,
      String timeStamp, Double sn) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    solarPanelDAO.updateTh(initialTemp, finalTemp, ambientTemp, solar_flux, timeStamp,sn);
  }

  public void removeModel(double modelNo) throws SQLException
  {
    ModelDAO modelDAO = ModelDAO.getInstance();
    modelDAO.deleteModel(modelNo);
  }


}
