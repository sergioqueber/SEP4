package model;

public class Address
{
  private int id;
  private String street;
  private int number;
  private int postcode;
  private City city;

  public String getStreet()
  {
    return street;
  }

  public int getNumber()
  {
    return number;
  }

  public int getPostcode()
  {
    return postcode;
  }

  public Address(int id, String street, int number, int postcode, City city)
  {
    this.id = id;
    this.number = number;
    this.street = street;
    this.postcode = postcode;
    this.city = city;
  }

  public City getCity()
  {
    return city;
  }

  public void setCity(City city)
  {
    this.city = city;
  }

  public void setPostcode(int postcode)
  {
    this.postcode = postcode;
  }

  public void setNumber(int number)
  {
    this.number = number;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String toString()
  {
    return "Address: " + getStreet() + " " + getNumber() + ", " + getCity() + " " + getPostcode() + ", address id: " + getId();
  }
}
