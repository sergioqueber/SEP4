package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class WeatherController {

  @FXML
  private MenuItem manufacturersItem;

  @FXML
  private Label message;

  @FXML
  private Label messageField;

  @FXML
  private Label title;

  @FXML
  private MenuItem managePanelsItem;

  @FXML
  private MenuItem manufacturerItem;

  @FXML
  private MenuItem modelsItem;

  @FXML private Menu setTargets;

  @FXML private MenuItem cleaningItem;

  @FXML
  private MenuItem openOverview;

  @FXML
  private MenuItem openSetTargets;

  @FXML
  private MenuItem repairsItem;

  @FXML
  private MenuItem weatherItem;



  private ViewHandler viewHandler;
  private Model model;
  private Region root;

  public WeatherController()
  {}

  public void init(ViewHandler viewHandler, Region root, Model model) throws
      SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

  }

  public void setRoot(Region root)
  {
    this.root = root;
  }

  public Region getRoot()
  {
    return root;
  }

  public void loadOverview(){
    viewHandler.openView(model.getLastOverview());
  }

  public void loadModels()
  {
    viewHandler.openView("Models");
  }

  public void loadSetTargets(){
    viewHandler.openView("Set Targets");
  }

  public void loadCleaning()
  {
    viewHandler.openView("Cleaning");
  }

  public void loadManufacturers(){
    viewHandler.openView("Manufacturers");
  }

  public void loadManagePanels(){
    viewHandler.openView("Manage Panels");
  }

  public void loadRepairs()
  {
    viewHandler.openView("Repairs");
  }



}
