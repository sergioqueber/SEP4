package Solarex.src.model;

import java.time.*;

public class SolarPanel
{
  private double serialNo;
  private int location;
  private String installationTime;
  private String cleaningTime;
  private boolean status;
  private double ambientTemp;
  private double solarFlux;
  private int angle;
  private Model model;
  private String timestamp;
  private Factory factory;

  public SolarPanel(double serialNo, int location, String installationTime, String cleaningTime, boolean status, double ambientTemp, int solarFlux, int angle, Model model, String timestamp, Factory factory)
  {
    this.serialNo = serialNo;
    this.location = location;
    this.installationTime = installationTime;
    this.cleaningTime = cleaningTime;
    this.status = status;
    this.ambientTemp = ambientTemp;
    this.solarFlux = solarFlux;
    this.angle = angle;
    this.model = model;
    this.timestamp = timestamp;
    this.factory = factory;
  }

  public double getSerial_number()
  {
    return serialNo;
  }

  public void setSerial_number(double serialNo)
  {
    this.serialNo = serialNo;
  }

  public String getInstallationTime()
  {
    return installationTime;
  }

  public boolean isStatus()
  {
    return status;
  }

  public void setStatus(boolean status)
  {
    this.status = status;
  }

  public void setInstallationTime(String installationTime)
  {
    this.installationTime = installationTime;
  }

  public int getLocation()
  {
    return location;
  }

  public void setLocation(int location)
  {
    this.location = location;
  }

  public String getCleaningTime()
  {
    return cleaningTime;
  }

  public void setCleaningTime(String cleaningTime)
  {
    this.cleaningTime = cleaningTime;
  }

  public double getAmbient_temp()
  {
    return ambientTemp;
  }

  public void setAmbient_temp(int ambientTemp)
  {
    this.ambientTemp = ambientTemp;
  }

  public double getSolarFlux()
  {
    return solarFlux;
  }

  public void setSolarFlux(int solarFlux)
  {
    this.solarFlux = solarFlux;
  }

  public int getAngle()
  {
    return angle;
  }

  public void setAngle(int angle)
  {
    this.angle = angle;
  }

  public Model getModel()
  {
    return model;
  }

  public void setModelNo(Model model)
  {
    this.model = model;
  }

  public String getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(String timestamp)
  {
    this.timestamp = timestamp;
  }

  public double getEnergy()
  {
    return model.getArea();
  }

}
