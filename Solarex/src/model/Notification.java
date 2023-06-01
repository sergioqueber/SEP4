package model;

public class Notification
{
  private int id;
  private String name;

  public void setName(String name)
  {
    this.name = name;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public Notification(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public String toString()
  {
    return "" + getId();
  }


}
