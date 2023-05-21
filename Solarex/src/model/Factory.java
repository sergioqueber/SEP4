package Solarex.src.model;

public class Factory
{
  private int id;
  private double heatingConsumption;
  private double electricityConsumption;

  public int getId()
  {
    return id;
  }

  public double getHeatingConsumption()
  {
    return heatingConsumption;
  }

  public double getElectricityConsumption()
  {
    return electricityConsumption;
  }

  public Factory(int id, double heatingConsumption, double electricityConsumption)
  {
    this.id = id;
    this.heatingConsumption = heatingConsumption;
    this.electricityConsumption = electricityConsumption;
  }


}
