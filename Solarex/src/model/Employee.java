package Solarex.src.model;


public class Employee
{
  private int id;
  private double CPR;
  private String fName;
  private String lName;
  private String type;
  private String email;
  private double phoneNo;
  private String employmentDate;
  private Role role;
  private Factory workPlace;

  public Employee(int id, double CPR, String fName, String lNAme, String type, String email, double phoneNo, String employmentDate, Role role, Factory workPlace)
  {
    this.id = id;
    this.CPR = CPR;
    this.fName = fName;
    this.lName = lNAme;
    this.type = type;
    this.email = email;
    this.phoneNo = phoneNo;
    this.employmentDate = employmentDate;

  }

  public int getId()
  {
    return id;
  }

  public String getEmail()
  {
    return email;
  }

  public String getFName()
  {
    return fName;
  }

  public String getLName()
  {
    return lName;
  }

  public String getType()
  {
    return type;
  }

  public double getPhoneNo()
  {
    return phoneNo;
  }

  public String getEmploymentDate()
  {
    return employmentDate;
  }

  public Role getRole()
  {
    return role;
  }


}
