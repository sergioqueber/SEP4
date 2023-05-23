package model;

public class ThermalPanel extends SolarPanel
{
  private double initialTemp;
  private double finalTemp;
  private double ambientTemp;

  public ThermalPanel(double serialNo, int location, String installationTime, String cleaningTime,
      boolean status, double ambientTemp, int solarFlux, int angle, Model model,
      String timestamp,Factory factory )
  {
    super(serialNo,location,installationTime,cleaningTime,status,ambientTemp,solarFlux,angle,model,timestamp,factory);
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