package model;

public class Manufacturer
{
  private String name;
  private String email;
  private double phoneNumber;

  public Manufacturer()
  {

  }

  public Manufacturer(String name, String email, double phoneNumber)
  {
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.name = name;
  }

  public Manufacturer(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setPhoneNumber(double phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public double getPhoneNumber()
  {
    return phoneNumber;
  }

  public String toString()
  {
    return "" + name;
  }

}
