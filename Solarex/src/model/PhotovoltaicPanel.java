package model;

public class PhotovoltaicPanel extends SolarPanel
{
  private int current;
  private int voltage;
  private int power;

  public PhotovoltaicPanel(double serialNo, int location, String installationTime,
      boolean status, int angle, Model model, Factory factory,String type)
  {
    super(serialNo, location, installationTime, status, angle, model,factory,type);
    this.current = current;
    this.voltage = voltage;
    this.power = power;
  }

  public void setCurrent()
  {
    this.current = current;
  }

  public void setVoltage()
  {
    this.voltage = voltage;
  }

  public void setPower()
  {
    this.power = power;
  }

  public int getCurrent()
  {
    return current;
  }

  public int getVoltage()
  {
    return voltage;
  }

  public int getPower()
  {
    return power;
  }



}
