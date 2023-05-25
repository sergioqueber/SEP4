package model;

import java.util.ArrayList;

public class Alerts
{
  private ArrayList<Notification> alerts;

  public Alerts()
  {
    this.alerts = new ArrayList<Notification>();
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



}
