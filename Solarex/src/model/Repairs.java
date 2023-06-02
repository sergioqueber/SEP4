package model;

public class Repairs
{
  private int id;
  private Employee employee;
  private String repairDate;
  private SolarPanel solarPanel;
  private double serialNumber;
  public Repairs(int id, Employee employee, String date, SolarPanel solarPanel)
  {
    this.id = id;
    this.employee = employee;
    this.repairDate = date;
    this.solarPanel = solarPanel;
    serialNumber = solarPanel.getSerial_number();
  }

  public int getId()
  {
    return id;
  }

  public Employee getEmployee()
  {
    return employee;
  }

  public String getRepairDate()
  {
    return repairDate;
  }

  public SolarPanel getSolarPanel()
  {
    return solarPanel;
  }

  public void setSolarPanel(SolarPanel solarPanel)
  {
    this.solarPanel = solarPanel;
  }

  public double getSerialNumber()
  {
    return serialNumber;
  }

  public void setEmployee(Employee employee)
  {
    this.employee = employee;
  }

  public void setRepairDate(String repairDate)
  {
    this.repairDate = repairDate;
  }

  public void setSerialNumber(double serialNumber)
  {
    this.serialNumber = serialNumber;
  }




}
