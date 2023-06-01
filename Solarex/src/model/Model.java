package model;

public class Model
{
  private double modelNo;
  private double length;
  private double width;
  private double area;
  private Manufacturer manufacturer ;
  private double efficiency;

  public Model(double modelNo, double length, double width, Manufacturer manufacturer, double efficiency)
  {
    this.modelNo = modelNo;
    this.length = length;
    this.width = width;
    this.area = getArea();
    this.manufacturer = manufacturer;
    this.efficiency = efficiency;
  }
  public Model(double modelNo){
    this.modelNo = modelNo;
  }

  public Model()
  {
    this.modelNo = modelNo;
    this.length = length;
    this.width = width;
    this.area = getArea();
    this.manufacturer = manufacturer;
    this.efficiency = efficiency;
  }

  public void setModelNo(double modelNo)
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

  public double getModelNo()
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

  public String toString(){
    return "" + getModelNo();
  };

}
