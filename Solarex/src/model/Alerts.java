package model;

public class Alerts
{
  private int id;
  private int notificationId;
  private double solarPanelSn;

  public Alerts(int id, int notificationId, double solarPanelSn)
  {
    this.id = id;
    this.notificationId = notificationId;
    this.solarPanelSn = solarPanelSn;
  }


  public int getId()
  {
    return id;
  }

  public int getNotificationId()
  {
    return notificationId;
  }

  public double getSolarPanelSn()
  {
    return solarPanelSn;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setNotificationId(int notificationId)
  {
    this.notificationId = notificationId;
  }

  public void setSolarPanelSn(double solarPanelSn)
  {
    this.solarPanelSn = solarPanelSn;
  }



  @Override public String toString()
  {
    return "Alerts{" + "id = " + id + ", notificationId = " + notificationId
        + ", solarPanelSn = " + solarPanelSn;
  }
}
