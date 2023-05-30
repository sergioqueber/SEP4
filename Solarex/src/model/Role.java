package model;

public class Role
{
  private int id;
  private String type;

  public int getId()
  {
    return id;
  }

  public String getType()
  {
    return type;
  }

  public Role(int id, String type)
  {
    this.id = id;
    this.type = type;
  }
  public Role(int id){
    this.id = id;
  }
}
