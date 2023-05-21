package Solarex.src.model;

public class Address
{
  private String street;
  private int number;
  private int postcode;

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

  public Address()
  {
    this.number = number;
    this.street = street;
    this.postcode = postcode;
  }

}
