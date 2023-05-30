package model;

public class SolarPanel
{
  private double serialNo;
  private int location;
  private String installationTime;
  private String cleaningTime;
  private String status;
  private double ambientTemp;
  private double solarFlux;
  private int angle;
  private Model model;
  private String timestamp;
  private Factory factory;
  private String type;

  public SolarPanel(double serialNo, int location, String status, int angle, Model model, Factory factory, String type)
  {
    this.serialNo = serialNo;
    this.location = location;
    this.installationTime = installationTime;
    this.cleaningTime = null;
    this.status = status;
    this.ambientTemp = ambientTemp;
    this.solarFlux = solarFlux;
    this.angle = angle;
    this.model = model;
    this.timestamp = null;
    this.factory = factory;
    this.type = type;
  }


  public SolarPanel(String timestamp){
    this.timestamp = timestamp;
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


  public void setStatus(String status)
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

  public void setAmbient_temp(double ambientTemp)
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

  public double getEnergy()
  {
    return model.getArea();
  }
  public String getType(){
    return type;
  }

  public Factory getFactory()
  {
    return factory;
  }

  public String getTimestamp()
  {
    return timestamp;
  }

  public String getStatus()
  {
    return status;
  }
}
