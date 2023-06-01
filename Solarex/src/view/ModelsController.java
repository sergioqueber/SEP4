package view;
import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Manufacturer;

import java.sql.Connection;
import java.sql.SQLException;


public class ModelsController
{
  @FXML
  private TableColumn<?, ?> widthColumn;

  @FXML
  private Button addButton;

  @FXML
  private TableColumn<?, ?> areaColumn;

  @FXML
  private TableColumn<?, ?> lengthColumn;

  @FXML
  private TextField lengthTextField;

  @FXML
  private ChoiceBox<Manufacturer> manufacturerChoiceBox;

  @FXML
  private TableColumn<?, ?> manufacturerColumn;

  @FXML
  private TableColumn<?, ?> modelNoColumn;

  @FXML
  private TextField modelNoTextField;

  @FXML
  private TableView<model.Model> modelsTable;
  @FXML
  private Button removeButton;

  @FXML
  private TextField widthTextField;
  @FXML
  private TextField efficiencyTextField;
  @FXML
  private MenuBar menu;

  @FXML
  private TableColumn<?, ?> notificationTable;

  @FXML
  private Label notificationsLabel;
  @FXML
  private Menu overview;
  @FXML
  private MenuItem openOverview;
  @FXML
  private Menu setTargets;
  @FXML
  private MenuItem openSetTargets;
  @FXML
  private Menu cleaning;
  @FXML
  private MenuItem openCleaning;
  @FXML
  private Menu repairs;
  @FXML
  private MenuItem openRepairs;
  @FXML
  private Menu managePanels;
  @FXML
  private MenuItem openManagePanels;
  @FXML
  private Menu weather;
  @FXML
  private MenuItem openWeather;
  @FXML
  private Menu manufacturers;
  @FXML
  private MenuItem openManufacturers;
  @FXML
  private Menu models;
  @FXML
  private MenuItem openModels;


  private ViewHandler viewHandler;
  private Model model;
  private Region root;

  public ModelsController(){}
  public void init(ViewHandler viewHandler, Region root, Model model) throws
      SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    manufacturerChoiceBox.getItems().addAll(model.getManufacturers());
    fillModelsTable();
  }

  public Region getRoot()
  {
    return root;
  }

  public void fillModelsTable() throws SQLException
  {
    modelNoColumn.setCellValueFactory(new PropertyValueFactory<>("modelNo"));
    widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
    lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
    areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    modelsTable.getItems().clear();
    for(int i = 0; i <model.getModels().size(); i++){
      modelsTable.getItems().add(model.getModels().get(i));
    }
  }

  public void addModel() throws SQLException
  {
    double modelNo = Double.parseDouble(modelNoTextField.getText());
    double length = Double.parseDouble(lengthTextField.getText());
    double width = Double.parseDouble(widthTextField.getText());
    Manufacturer manufacturer = manufacturerChoiceBox.getValue();
    double efficiency =  Double.parseDouble(efficiencyTextField.getText());
    model.addModel(modelNo,length,width,manufacturer,efficiency);
    fillModelsTable();


  }
  public void onRemoveButton() throws SQLException
  {
    if(modelsTable.getSelectionModel().getSelectedItem() == null)
    {
      //messageText.setText("Please select a manufacturer from the table to continue.");
    }
    else
    {
      model.removeModel(modelsTable.getSelectionModel().getSelectedItem().getModelNo());
      fillModelsTable();
    }
  }

  public void loadOverview(){
    viewHandler.openView(model.getLastOverview());
  }
  public void loadSetTargets(){
    viewHandler.openView("Set Targets");
  }
  public void loadManufacturers(){
    viewHandler.openView("Manufacturers");
  }
  public void loadManagePanels(){
    viewHandler.openView("Manage Panels");
  }
  public void loadCleaning()
  {
    viewHandler.openView("Cleaning");
  }

  public void loadWeather()
  {
    viewHandler.openView("Weather");
  }

  public void loadRepairs()
  {
    viewHandler.openView("Repairs");
  }
}
