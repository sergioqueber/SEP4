package model;

public class Factory
{
  private int id;
  private double heatingConsumption;
  private double electricityConsumption;

  public void setId(int id)
  {
    this.id = id;
  }

  public void setElectricityConsumption(double electricityConsumption)
  {
    this.electricityConsumption = electricityConsumption;
  }

  public void setHeatingConsumption(double heatingConsumption)
  {
    this.heatingConsumption = heatingConsumption;
  }

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

  public Factory(int id){
    this.id = id;
  }


}

