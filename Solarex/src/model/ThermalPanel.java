package Solarex.src.model;

import Solarex.src.model.SolarPanel;

public class ThermalPanel extends SolarPanel
{
  private double initialTemp;
  private double finalTemp;
  private double ambientTemp;

  public ThermalPanel(double serialNo, int location, String installationTime, String cleaningTime,
      boolean status, double ambientTemp, int solarFlux, int angle, int modelNo,
      String timestamp)
  {
    super(serialNo, location, installationTime, cleaningTime, status,
        ambientTemp, solarFlux, angle, modelNo, timestamp);
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