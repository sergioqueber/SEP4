package Solarex.src.model;

import Solarex.src.model.SolarPanel;

public class ThermalPanel extends SolarPanel
{
  private int initial_temp;
  private int final_temp;
  private int ambient_temp;

  public ThermalPanel(int serial_number, String location, String installation_time, String cleaning_time,
      boolean status, int ambient_temp, int solar_flux, int angle, int model_no,
      String timestamp)
  {
    super(serial_number, location, installation_time, cleaning_time, status,
        ambient_temp, solar_flux, angle, model_no, timestamp);
    this.initial_temp = initial_temp;
    this.final_temp = final_temp;
    this.ambient_temp = ambient_temp;
  }

  public int getInitial_temp()
  {
    return initial_temp;
  }

  public int getFinal_temp()
  {
    return final_temp;
  }

  public int getAmbient_temp()
  {
    return ambient_temp;
  }

}