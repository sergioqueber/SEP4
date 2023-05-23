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

}
