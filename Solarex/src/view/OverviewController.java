package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Alerts;
import model.SolarPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewController implements Initializable
{

  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML
  private Button applyButton;

  @FXML
  private Menu setTargets;
  @FXML
  private MenuItem setTargetsItem;
  @FXML
  private Menu manufacturers;
  @FXML
  private MenuItem manufacturerItem;

  @FXML
  private Label consumptionLabel;

  @FXML
  private Label endLabel;

  @FXML
  private Label generationLabel;

  @FXML
  private TableColumn<?, ?> locationColumn;

  @FXML
  private Menu managePanels;

  @FXML
  private MenuBar menu;

  @FXML
  private TableColumn<?, ?> notificationTable;

  @FXML
  private Label notificationsLabel;

  @FXML
  private Menu overview;

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
    savingsValueLabel.setText(String.valueOf(model.getSavings(2.8)));

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
      System.out.println(model.getAllPanels().get(i));
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
      System.out.println(model.getAlerts().get(i));
    }
  }

  public void resetFields(){
    textFieldSeach.setText("");
  }

  public void loadSetTargets(){
    viewHandler.openView("Set Targets");
    System.out.println("Hola");
  }

  public void loadManufacturers(){
    viewHandler.openView("Manufacturers");
  }
}
