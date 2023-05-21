package Solarex.src.model;

public class Repairs
{
  private int id;
  private Employee employee;
  private String repairDate;
  private SolarPanel panel;

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

  public SolarPanel panel()
  {
    return panel;
  }

  public Repairs()
  {
    this.id = id;
    this.employee = employee;
    this.repairDate = repairDate;
    this.panel = panel;
  }

}
