import java.security.Principal;
import java.util.Date;
import  Connection.Connector;
import model.PhotovoltaicPanel;
import model.SolarPanel;

import java.util.Random;

public class SolarPanelSimulator extends Thread
{
  private Connector conection;

  //private double intensity;
  //private double voltage;
  //private double solarFlux;
  private final int waitTime = 3000;
  Random random = new Random();
  public void run(){
    while (true){
      double intensity = 0.2 + (0.4 - 0.2) * random.nextDouble();
      double voltage = 19.0 + (21.0 - 19.0) * random.nextDouble();
      double solarFlux = 700.0 + (1000.0 - 700.0) * random.nextDouble();
      System.out.println(intensity + " " + voltage + " " + solarFlux);

      try {
        Thread.sleep(waitTime);
      } catch (InterruptedException e) {
        System.out.println("Thread sleeping interrupted");
        e.printStackTrace();
      }
    }
  }
}



