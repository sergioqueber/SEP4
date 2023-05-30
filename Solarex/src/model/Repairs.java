package model;

public class Repairs
{
  private int id;
  private Employee employee;
  private String repairDate;
  private SolarPanel solarPanel;

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

  public SolarPanel solarPanel()
  {
    return solarPanel;
  }

  public Repairs(int id, Employee employee, String date, SolarPanel solarPanel)
  {
    this.id = id;
    this.employee = employee;
    this.repairDate = date;
    this.solarPanel = solarPanel;
  }

}
