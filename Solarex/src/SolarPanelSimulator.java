import Connection.Connector;
import Connection.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SolarPanelSimulator extends Thread
{
  private Connector conection;

  //private double intensity;
  //private double voltage;
  //private double solarFlux;
  private final int waitTime = 3000;
  private Model model;
  Random random = new Random();
  private LocalDate currentDate;

  public void run(){
    while (true){
      double intensity = 0.2 + (0.4 - 0.2) * random.nextDouble();
      double voltage = 19.0 + (21.0 - 19.0) * random.nextDouble();
      double solarFlux = 700.0 + (1000.0 - 700.0) * random.nextDouble();
      double initialTemp = 10.0 + (13.0 - 10.0)* random.nextDouble();
      double finalTemp = 15.0 + (21.0 - 15.0) * random.nextDouble();
      double ambientTemp = 10.0 + (15.0 - 10.0)* random.nextDouble();
      String timestamp = currentDate.format(DateTimeFormatter.ISO_DATE);
      try
      {
        model.newPvLecture(intensity,voltage,solarFlux,timestamp,111111.0);
      }
      catch (SQLException e)
      {
        throw new RuntimeException(e);
      }
      currentDate = currentDate.plusDays(1);
      try {
        Thread.sleep(waitTime);
      } catch (InterruptedException e) {
        System.out.println("Thread sleeping interrupted");
        e.printStackTrace();
      }
    }
  }
}



