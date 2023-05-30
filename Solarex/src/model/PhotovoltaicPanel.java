package model;

public class PhotovoltaicPanel extends SolarPanel
{
  private double intensity;
  private double voltage;
  private double power;
  private double solarFlux;

  public PhotovoltaicPanel(double serialNo, int location,
      String status, int angle, Model model, Factory factory,String type)
  {
    super(serialNo, location, status, angle, model,factory,type);
    this.power = intensity*voltage;
  }
  public PhotovoltaicPanel(SolarPanel pv){
    super(pv.getSerial_number(),pv.getLocation(),pv.getStatus(),pv.getAngle(),pv.getModel(),pv.getFactory(),pv.getType());
  }
  public PhotovoltaicPanel(double intensity, double voltage, double solarFlux, String timestamp){
    super(timestamp);
    this.intensity = intensity;
    this.voltage = voltage;
    this.solarFlux = solarFlux;
    power = intensity*voltage;
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
