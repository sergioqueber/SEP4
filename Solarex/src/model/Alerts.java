package model;

import java.util.ArrayList;

public class Alerts
{
  private int id;
  private int notificationId;
  private double solarPanelSn;
  private ArrayList<Notification> alerts;

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


  public void addNotification(Notification notification)
  {
    this.alerts.add(notification);
  }

  public ArrayList<Notification> getAlertList()
  {
    return alerts;
  }

  public void removeNotification(Notification notification)
  {
    this.alerts.remove(notification);
  }

  public Notification getNotification (int index)
  {
    return alerts.get(index);
  }

  @Override public String toString()
  {
    return "Alerts{" + "id = " + id + ", notificationId = " + notificationId
        + ", solarPanelSn = " + solarPanelSn + ", alerts = " + alerts + '}';
  }
}
