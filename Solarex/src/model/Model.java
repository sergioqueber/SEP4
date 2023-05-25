package model;

public class Model
{
  private int modelNo;
  private double length;
  private double width;
  private double area;
  private Manufacturer manufacturer ;
  private double efficiency;

  public Model(int modelNo, double length, double width, Manufacturer manufacturer, double efficiency)
  {
    this.modelNo = modelNo;
    this.length = length;
    this.width = width;
    this.area = getArea();
    //this.manufacturer = manufacturer;
    this.efficiency = efficiency;
  }

  public void setModelNo(int modelNo)
  {
    this.modelNo = modelNo;
  }

  public void setLength(double length)
  {
    this.length = length;
  }

  public void setWidth(double width)
  {
    this.width = width;
  }

  public void setEfficiency(double efficiency)
  {
    this.efficiency = efficiency;
  }

  public void setManufacturer(Manufacturer manufacturer)
  {
    this.manufacturer = manufacturer;
  }

  public int getModelNo()
  {
    return modelNo;
  }

  public double getLength()
  {
    return length;
  }

  public double getWidth()
  {
    return width;
  }

  public double getArea()
  {
    return area = length * width;
  }

  public Manufacturer getManufacturer()
  {
    return manufacturer;
  }

  public double getEfficiency()
  {
    return efficiency;
  }


}
