package Solarex.src.model;

public class PhotovoltaicPanel extends SolarPanel
{
  private int current;
  private int voltage;
  private int power;

  public PhotovoltaicPanel(int serial_number, String location, String installation_time, String cleaning_time,
      boolean status, int ambient_temp, int solar_flux, int angle, int model_no, String timestamp)
  {
    super(serial_number, location, installation_time, cleaning_time, status, ambient_temp, solar_flux, angle, model_no, timestamp);
    this.current = current;
    this.voltage = voltage;
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
