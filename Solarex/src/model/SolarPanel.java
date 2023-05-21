package Solarex.src.model;

public class SolarPanel
{
  private int serial_number;
  private String location;
  private String installation_time;
  private String cleaning_time;
  private boolean status;
  private int ambient_temp;
  private int solar_flux;
  private int angle;
  private int model_no;
  private String timestamp;

  public SolarPanel(int serial_number, String location, String installation_time, String cleaning_time, boolean status, int ambient_temp, int solar_flux, int angle, int model_no, String timestamp)
  {
    this.serial_number = serial_number;
    this.location = location;
    this.installation_time = installation_time;
    this.cleaning_time = cleaning_time;
    this.status = status;
    this.ambient_temp = ambient_temp;
    this.solar_flux = solar_flux;
    this.angle = angle;
    this.model_no = model_no;
    this.timestamp = timestamp;

  }

  public int getSerial_number()
  {
    return serial_number;
  }

  public void setSerial_number(int serial_number)
  {
    this.serial_number = serial_number;
  }

  public String getInstallation_time()
  {
    return installation_time;
  }

  public boolean isStatus()
  {
    return status;
  }

  public void setStatus(boolean status)
  {
    this.status = status;
  }

  public void setInstallation_time(String installation_time)
  {
    this.installation_time = installation_time;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getCleaning_time()
  {
    return cleaning_time;
  }

  public void setCleaning_time(String cleaning_time)
  {
    this.cleaning_time = cleaning_time;
  }

  public int getAmbient_temp()
  {
    return ambient_temp;
  }

  public void setAmbient_temp(int ambient_temp)
  {
    this.ambient_temp = ambient_temp;
  }

  public int getSolar_flux()
  {
    return solar_flux;
  }

  public void setSolar_flux(int solar_flux)
  {
    this.solar_flux = solar_flux;
  }

  public int getAngle()
  {
    return angle;
  }

  public void setAngle(int angle)
  {
    this.angle = angle;
  }

  public int getModel_no()
  {
    return model_no;
  }

  public void setModel_no(int model_no)
  {
    this.model_no = model_no;
  }

  public String getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(String timestamp)
  {
    this.timestamp = timestamp;
  }

}
