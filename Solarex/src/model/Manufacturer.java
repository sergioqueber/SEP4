package model;

public class Manufacturer
{
  private String name;
  private String email;
  private double phoneNumber;

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

  public double getPhoneNumber()
  {
    return phoneNumber;
  }

  public Manufacturer(String name, String email,double phoneNumber){
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.name = name;
  }

}
