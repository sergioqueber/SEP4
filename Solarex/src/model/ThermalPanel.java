package model;

public class ThermalPanel extends SolarPanel
{
  private double initialTemp;
  private double finalTemp;
  private double ambientTemp;

  public ThermalPanel(double serialNo, int location,
      String status, int angle, Model model,Factory factory,String type )
  {
    super(serialNo,location, status,angle,model,factory,type);
    this.initialTemp = initialTemp;
    this.finalTemp = finalTemp;
    this.ambientTemp = ambientTemp;
  }

  public ThermalPanel(double initialTemp, double finalTemp, double ambientTemp, double solarFlux, String timestamp)
  {
    super(timestamp);
    this.initialTemp = initialTemp;
    this.finalTemp = finalTemp;
    this.ambientTemp = ambientTemp;
  }


  public double getInitialTemp()
  {
    return initialTemp;
  }

  public double getFinalTemp()
  {
    return finalTemp;
  }

  public double getAmbientTemp()
  {
    return ambientTemp;
  }

  public void setInitialTemp(double initialTemp)
  {
    this.initialTemp = initialTemp;
  }

  public void setFinalTemp(double finalTemp)
  {
    this.finalTemp = finalTemp;
  }

  public void setAmbientTemp(double ambientTemp)
  {
    this.ambientTemp = ambientTemp;
  }
}