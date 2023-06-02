package model;

public class Address
{
  private int id;
  private String street;
  private int number;
  private int postcode;
  private City city;

  public Address(int id, String street, int number, int postcode, City city)
  {
    this.id = id;
    this.number = number;
    this.street = street;
    this.postcode = postcode;
    this.city = city;
  }

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

  public void setId(int id)
  {
    this.id = id;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public void setNumber(int number)
  {
    this.number = number;
  }

  public void setPostcode(int postcode)
  {
    this.postcode = postcode;
  }

  public void setCity(City city)
  {
    this.city = city;
  }

  public int getId()
  {
    return id;
  }

  public City getCity()
  {
    return city;
  }

}
