package model;


public class Employee
{
  private int id;
  private String CPR;
  private String fName;
  private String lName;
  private String email;
  private double phoneNo;
  private String employmentDate;
  private String password;
  private Role role;
  private Factory workPlace;

  public Employee(int id, String CPR, String fName, String lNAme, String email, double phoneNo, String employmentDate, Role role, Factory workPlace)
  {
    this.id = id;
    this.CPR = CPR;
    this.fName = fName;
    this.lName = lNAme;
    this.email = email;
    this.phoneNo = phoneNo;
    this.employmentDate = employmentDate;

  }
  public Employee(int id, String password, Role role){
    this.id = id;
    this.password = password;
    this.role = role;
  }
  public Employee(int id){
    this.id = id;
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

  public void setCPR(String CPR)
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

  public String getCPR()
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

  public String toString(){
    return "" + id;
  }
}
