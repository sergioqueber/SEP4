package model;


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
  private String password;
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
  public Employee(int id, String password, Role role){
    this.id = id;
    this.password = password;
    this.role = role;
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

  public void setId(int id)
  {
    this.id = id;
  }

  public void setCPR(double CPR)
  {
    this.CPR = CPR;
  }

  public void setfName(String fName)
  {
    this.fName = fName;
  }

  public void setlName(String lName)
  {
    this.lName = lName;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setPhoneNo(double phoneNo)
  {
    this.phoneNo = phoneNo;
  }

  public void setEmploymentDate(String employmentDate)
  {
    this.employmentDate = employmentDate;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setRole(Role role)
  {
    this.role = role;
  }

  public void setWorkPlace(Factory workPlace)
  {
    this.workPlace = workPlace;
  }

  public double getCPR()
  {
    return CPR;
  }

  public String getfName()
  {
    return fName;
  }

  public String getlName()
  {
    return lName;
  }

  public String getPassword()
  {
    return password;
  }

  public Factory getWorkPlace()
  {
    return workPlace;
  }
}
