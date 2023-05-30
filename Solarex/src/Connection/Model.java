package Connection;

import DAOTest.*;
import DAOTest.FactoryDAO;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model
{
  private String lastOverview;
  private String adminPassword = "1234";
  private Double selectedSn;
  private String selectedType;

  public void setSelectedType(String selectedType)
  {
    this.selectedType = selectedType;
  }

  public String getSelectedType()
  {
    return selectedType;
  }

  public Double getSelectedSn()
  {
    return selectedSn;
  }

  public void setSelectedSn(Double selectedSn)
  {
    this.selectedSn = selectedSn;
  }

  public void setLastOverview(String lastOverview){
    this.lastOverview = lastOverview;
  }

  public String getLastOverview(){
    return lastOverview;
  }

  public String getAdminPassword()
  {
    return adminPassword;
  }

  public PhotovoltaicPanel addPhotovoltaicPanel(double serialNo, int location, String installationDate, String status, int angle, double modelNo, int factoryId, String type) throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createPv(serialNo,location, installationDate,status,angle,modelNo,factoryId,type);
  }
  public ThermalPanel addThermalPanel(double serialNo, int location, String installationDate, String status, int angle, double modelNo, int factoryId, String type) throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.createTh(serialNo,location, installationDate,status,angle,modelNo,factoryId,type);
  }



  public ArrayList<SolarPanel> getAllPanels() throws SQLException
  {
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    return solarPanelDAO.readAllPanels();
  }

  public ArrayList<SolarPanel> getPanelsBySn(double filter) throws SQLException{
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

  public Manufacturer deleteManufacturer(Manufacturer manufacturer)
      throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.removeManufacturer(manufacturer.getName());
  }

  public Manufacturer editManufacturer(Manufacturer manufacturer) throws SQLException
  {
    ManufacturerDAO manufacturers = ManufacturerDAO.getInstance();
    return manufacturers.editManufacturer(manufacturer);
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

  public int getSavings(double electricityPrice, double sellingPrice)throws SQLException{
    FactoryDAO factoryDAO = FactoryDAO.getInstance();
    double savings= 0;
    if(getGeneration() > (factoryDAO.read().get(0).getElectricityConsumption()))
    {
      savings = (((factoryDAO.read().get(0).getElectricityConsumption()) *electricityPrice) + (getGeneration() *sellingPrice));
    }
    else if (getGeneration() < (factoryDAO.read().get(0).getElectricityConsumption())){
      savings =  (((factoryDAO.read().get(0).getElectricityConsumption()) *electricityPrice) - (getGeneration() * electricityPrice));
    }
    return (int) savings;
  }

  public double getGeneration ()throws SQLException{
    SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
    double generation = 0;
    for(int i = 0; i < solarPanelDAO.readPvValues().size(); i++){
      generation = generation + solarPanelDAO.readPvValues().get(i).getPower();
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

    public void registerEmployee(String CPR, String fName, String lNAme, String email, double phoneNo, String employmentDate,String password, Role role, Factory workPlace)
        throws SQLException
    {
    EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
    employeeDAO.createEmployee(CPR, fName,lNAme,email,phoneNo,employmentDate,password,role,workPlace);
    }

    public ArrayList<PhotovoltaicPanel> getPvLogValues(String startTime, String endTime, Double sn) throws SQLException
    {
      PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
      return pvPanelLogDAO.readValues(startTime,endTime, sn);
    }
    public ArrayList<ThermalPanel> getThLogValues(String startTime, String endTime, Double sn) throws SQLException{
    ThPanelLogDAO thPanelLogDAO = ThPanelLogDAO.getInstance();
    return thPanelLogDAO.readValues(startTime,endTime,sn);
    }

    public double getIndividualGeneration (Double sn) throws SQLException
    {
      SolarPanelDAO solarPanelDAO = SolarPanelDAO.getInstance();
      double generation = 0;
      for(int i = 0;  i < solarPanelDAO.readPvValues().size(); i++){
        if(solarPanelDAO.readPvValues().get(i).getSerial_number() == sn){
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

    public ArrayList<SolarPanel> getCleaningLogBySn(double sn)throws SQLException{
    CleaningLogDAO cleaningLogDAO = CleaningLogDAO.getInstance();
    return cleaningLogDAO.readCleaningLog(sn);
    }

    public void newPvLecture(double intensity, double voltage, double solar_flux,
        String timeStamp, Double sn) throws SQLException
    {
    PvPanelLogDAO pvPanelLogDAO = PvPanelLogDAO.getInstance();
    pvPanelLogDAO.update(intensity,voltage,solar_flux,timeStamp,sn);
    }
  }
