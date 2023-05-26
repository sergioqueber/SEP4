package model;

public class ThermalPanel extends SolarPanel
{
  private double initialTemp;
  private double finalTemp;
  private double ambientTemp;

  public ThermalPanel(double serialNo, int location, String installationTime,
      boolean status, int angle, Model model,Factory factory,String type )
  {
    super(serialNo,location,installationTime, status,angle,model,factory,type);
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

}