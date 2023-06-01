package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.SolarPanel;

import java.sql.SQLException;
import java.util.ArrayList;

public class CleaningController{

  @FXML
  private Button applyButton;

  @FXML
  private MenuItem cleaningItem;


  @FXML
  private TextField dateField;

  @FXML
  private TextField dateLogField;

  @FXML
  private Button editLogButton;

  @FXML
  private MenuItem managePanelsItem;

  @FXML
  private MenuItem manufacturerItem;

  @FXML
  private Label messageField;

  @FXML
  private Label messageLogField;

  @FXML
  private TableColumn<SolarPanel, Double> idColumn;

  @FXML
  private MenuItem modelsItem;

  @FXML
  private MenuItem openOverview;

  @FXML
  private MenuItem openSetTargets;

  @FXML
  private MenuItem repairsItem;

  @FXML
  private Button returnButton;

  @FXML
  private Button saveAddButton;

  @FXML
  private Menu setTargets;


  @FXML
  private MenuItem weatherItem;

  @FXML TableView<SolarPanel> cleaningTable;

  @FXML TableView<SolarPanel> cleaningLogTable;
    @FXML
    private TableColumn<SolarPanel, String> lastCleanedColumn;

    @FXML
    private TableColumn<SolarPanel, String> cleaningLogColumn;

    @FXML
    private TableColumn<SolarPanel, String> locationColumn;

    @FXML
    private TableColumn<SolarPanel, String> locationLogColumn;

    @FXML
    private TableColumn<SolarPanel, Double> snColumn;

    @FXML
    private TableColumn<SolarPanel, Double> snLogCOlumn;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField endDateField;

    @FXML
    private TableColumn<SolarPanel, String> typeColumn;

    @FXML
    private TableColumn<SolarPanel, String> typeLogColumn;



  private ViewHandler viewHandler;
  private Model model;
  private Region root;
  private ArrayList<SolarPanel> cleanedPanels;


  public CleaningController()
  {}

  public void init(ViewHandler viewHandler, Region root, Model model) throws
      SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    fillCleaningLogTable();
    fillCleaningTable();
  }

  public Region getRoot(){
    return root;
  }

  public void fillCleaningLogTable()throws SQLException
  {
    cleaningLogColumn.setCellValueFactory(new PropertyValueFactory<>("cleaningTime"));
    snLogCOlumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    locationLogColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    typeLogColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    cleaningLogTable.getItems().clear();
    for (int i = 0; i < model.getCleaningLog().size(); i++){
      cleaningLogTable.getItems().add(model.getCleaningLog().get(i));
    }
  }

  public void fillCleaningTable()throws SQLException
  {
    lastCleanedColumn.setCellValueFactory(new PropertyValueFactory<>("cleaningTime"));
    snColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    idColumn.setCellValueFactory(new PropertyValueFactory<>(""));
    cleaningTable.getItems().clear();
    for (int i = 0; i < model.getLastCleaning().size(); i++){
      cleaningTable.getItems().add(model.getLastCleaning().get(i));
    }
  }

  public void onApplyButtonClicked() throws SQLException
  {
    cleaningLogTable.getItems().clear();
    cleanedPanels = model.getPanelsByTime(startDateField.getText(), endDateField.getText());
    cleaningLogTable.getItems().addAll(cleanedPanels);
    if(cleaningLogTable.getItems().isEmpty())
    {
      messageLogField.setText("No information to retrieve for the selected period. Please try again.");
    }
    else
      messageLogField.setText("");
      startDateField.clear();
      endDateField.clear();
  }

  public void onSaveAddButtonClicked() throws SQLException
  {
      model.addCleaning(cleaningTable.getSelectionModel().getSelectedItem().getSerial_number(), dateField.getText());
      dateField.clear();
      fillCleaningTable();
  }


  public void onReturnButton() throws SQLException
  {
    fillCleaningLogTable();
  }

  /*public void onEditLogButton() throws SQLException
  {
   if(cleaningLogTable.getSelectionModel().getSelectedItem() == null){
      messageLogField.setText("Please select a date in the solar panel to edit.");
    }
    model.editLogCleaning(cleaningLogTable.getSelectionModel().getSelectedItem()., dateLogField.getText());
    dateLogField.clear();
    fillCleaningLogTable();
    fillCleaningTable();

  }

   */


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

  public void loadWeather()
  {
    viewHandler.openView("Weather");
  }

  public void loadRepairs()
  {
    viewHandler.openView("Repairs");
  }
  public void loadModels()
  {
    viewHandler.openView("Models");
  }

}


