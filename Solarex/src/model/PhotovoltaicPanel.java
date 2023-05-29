package model;

public class PhotovoltaicPanel extends SolarPanel
{
  private double intensity;
  private double voltage;
  private double power;

  public PhotovoltaicPanel(double serialNo, int location, String installationTime,
      boolean status, int angle, Model model, Factory factory,String type)
  {
    super(serialNo, location, installationTime, status, angle, model,factory,type);
    this.power = intensity*voltage;
  }
  public PhotovoltaicPanel(SolarPanel pv){
    super(pv.getSerial_number(),pv.getLocation(),pv.getInstallationTime(),pv.isStatus(),pv.getAngle(),pv.getModel(),pv.getFactory(),pv.getType());
  }
  public PhotovoltaicPanel(double intensity, double voltage, String timestamp){
    super(timestamp);
    this.intensity = intensity;
    this.voltage = voltage;
  }
  public void setIntensity(double intensity)
  {
    this.intensity = intensity;
  }

  public void setVoltage(double voltage)
  {
    this.voltage = voltage;
  }

  public void setPower()
  {
    this.power = power;
  }

  public double getIntensity()
  {
    return intensity;
  }

  public double getVoltage()
  {
    return voltage;
  }

  public double getPower()
  {
    return intensity*voltage;
  }

  public String toString(){
    return "" + intensity;
  }

}
