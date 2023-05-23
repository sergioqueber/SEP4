package model;

public class Test
{
  public static void main(String[] args)
  {
    Manufacturer manufacturer = new Manufacturer("name","email",55213877);

    Model model = new Model(354,5,5, manufacturer,10);

    System.out.println(model);
  }
}
