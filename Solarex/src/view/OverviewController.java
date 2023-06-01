package view;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import Connection.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import model.Alerts;
import model.Notification;
import model.SolarPanel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OverviewController implements Initializable
{

  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML
  private Button applyButton;


  @FXML
  private Label consumptionLabel;

  @FXML
  private Label endLabel;

  @FXML
  private Label generationLabel;

  @FXML
  private TableColumn<?, ?> locationColumn;

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


  @FXML
  private TextField savings;

  @FXML
  private Label savingsLabel;

  @FXML
  private TableColumn<SolarPanel, Double> serialNumberColumn;

  @FXML
  private TableView<SolarPanel> solarPanelTable;

  @FXML
  private Label startLabel;

  @FXML
  private TableColumn<SolarPanel, Boolean> statusColumn;

  @FXML
  private TextField textFieldEnd;

  @FXML
  private TextField textFieldStart;

  @FXML
  private Label timePeriodLabel;

  @FXML
  private TableColumn<SolarPanel, String> typeColumn;

  @FXML
  private Button updateButton;
  @FXML
  private CheckBox SNCheckBox;
  @FXML
  private CheckBox locationCheckBox;
  @FXML
  private TextField textFieldSeach;
  @FXML
  private Button searchButton;
  @FXML
  private Label generationValueLabel;
  @FXML
  private Label electricityValueConsumption;
  @FXML
  private Label heatingValueConsumption;
  @FXML
  private Label electricityConsumption;
  @FXML
  private Label heatingConsumption;
  @FXML
  private Label savingsValueLabel;
  @FXML
  private LineChart<?, ?> graph;
  @FXML
  private NumberAxis powerOutputAxis;
  @FXML
  private CategoryAxis timeAxis;
  @FXML
  TableView<Alerts> alertsTableView;
  @FXML
  TableColumn<Alerts,Integer> idColumn;
  @FXML
  TableColumn<Alerts,String> descriptionColumn;
  @FXML
  Button refreshButton;
  @FXML
  private Button selectButton;



  public OverviewController() throws SQLException
  {};
  ArrayList<Double> generationByTimePeriod;
  ArrayList<String> days;
  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
    solarPanelTable.getItems().clear();
    fillTable();
    //refreshNotification();
    generationValueLabel.setText(String.valueOf(model.getGeneration()));
    electricityValueConsumption.setText(String.valueOf(model.getElectricityConsumption()));
    heatingValueConsumption.setText(String.valueOf(model.getHeatingConsumption()));
    savingsValueLabel.setText(String.valueOf(model.getSavings(2.8,1.8)));
    if(model.getLastOverview().equals("OverviewAn")){
      manufacturers.setDisable(true);
      models.setDisable(true);
      managePanels.setDisable(true);
    }

  }


  public void selectTimePeriod() throws SQLException
  {
    generationByTimePeriod = model.getGenerationByTimePeriod(textFieldStart.getText(), textFieldEnd.getText());
    days = model.getDaysInTimePeriod(textFieldStart.getText(),textFieldEnd.getText());
    XYChart.Series series = new XYChart.Series();

    for (int i = 0; i < days.size(); i++){
      series.getData().add(new XYChart.Data<>(days.get(i), generationByTimePeriod.get(i)));
    }

    graph.getData().addAll(series);
  }
  public void initialize(URL url, ResourceBundle rb) {


  }



  public Region getRoot(){
    return root;
  }

  public void search() throws SQLException
  {
    if(SNCheckBox.isSelected()){
      int sn = Integer.parseInt(textFieldSeach.getText());
      serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
      statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
      locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
      solarPanelTable.getItems().clear();
      for (int i = 0; i < model.getPanelsBySn(sn).size() ; i++)
      {
        //System.out.println(model.getAllPanels().get(i));
        solarPanelTable.getItems().add(model.getPanelsBySn(sn).get(i));
      }
    }
    else if (locationCheckBox.isSelected()){
      int location = Integer.parseInt(textFieldSeach.getText());
      serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
      statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
      locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
      solarPanelTable.getItems().clear();
      for (int i = 0; i < model.getPanelsByLocation(location).size() ; i++)
      {
        //System.out.println(model.getAllPanels().get(i));
        solarPanelTable.getItems().add(model.getPanelsByLocation(location).get(i));
      }
    }
    resetFields();
  }
  public void fillTable () throws SQLException
  {

    serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    solarPanelTable.getItems().clear();
    for (int i = 0; i < model.getAllPanels().size() ; i++)
    {
      //System.out.println(model.getAllPanels().get(i));
      solarPanelTable.getItems().add(model.getAllPanels().get(i));
    }
  }

  public void refreshNotification() throws SQLException
  {
    idColumn.setCellValueFactory(new PropertyValueFactory<>("notificationId"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("solarPanelSn"));
    alertsTableView.getItems().clear();
    for (int i = 0; i < model.getAlerts().size(); i++)
    {
      alertsTableView.getItems().add(model.getAlerts().get(i));
      //System.out.println(model.getAlerts().get(i));
    }
  }

  public void resetFields(){
    textFieldSeach.setText("");
    locationCheckBox.setSelected(false);
    SNCheckBox.setSelected(false);
  }

  public void loadSetTargets(){
    viewHandler.openView("Set Targets");
    //System.out.println("Hola");
  }

  public void loadManufacturers(){
    viewHandler.openView("Manufacturers");
  }
  public void loadManagePanels(){
    viewHandler.openView("Manage Panels");
  }
  public void loadSinglePanelView(){
    model.setSelectedSn (solarPanelTable.getSelectionModel().getSelectedItem().getSerial_number());
    model.setSelectedType(solarPanelTable.getSelectionModel().getSelectedItem()
        .getType());
    if(model.getSelectedType().equals("Photovoltaic")){
      viewHandler.openView("Single Panel Pv");
    }
    else if (model.getSelectedType().equals("Thermal"))
    {
      viewHandler.openView("Single Panel Th");
    }

  }
  public void loadModels(){
    viewHandler.openView("Models");
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
