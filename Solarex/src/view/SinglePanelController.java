package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import Connection.*;
import model.PhotovoltaicPanel;
import model.Repairs;
import model.SolarPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SinglePanelController implements Initializable
{
  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML private Button applyButton;
  @FXML private Button graphButton;

  @FXML private TableView<SolarPanel> cleaningTable;
  @FXML
  private TableColumn<SolarPanel,String> cleaningDate;

  @FXML private Label curentGenerationLabelValue;

  @FXML private TextField endTextField;

  @FXML private TableView<PhotovoltaicPanel> pvVariablesTable;
  @FXML
  private TableColumn<PhotovoltaicPanel, Double> intensityColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, Double> powerColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, String> timeStampColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, ?> voltageColumn;
  @FXML
  private TableColumn<PhotovoltaicPanel, ?> solarFluxColumn;

  @FXML private TableView<Repairs> repairsTable;

  @FXML private TextField startTextField;
  @FXML private LineChart<?, ?> graph;
  @FXML private NumberAxis powerOutPutAxis;

  @FXML private CategoryAxis timeAxis;
  @FXML
  private TableColumn<?,?> dateColumn;
  @FXML
  private TableColumn<?,?> employeeIdColumn;
  @FXML
  private Label serialNumberValueLabel;
  @FXML
  private Label locationValueLabel;
  @FXML
  private Label installationTimeValueLabel;
  @FXML
  private Label statusValueLabel;
  @FXML
  private Label angleNumberValueLabel;
  @FXML
  private Label typeNumberValueLabel;
  @FXML
  private MenuBar menu;

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

  public SinglePanelController()
  {
  }

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
    getCurrentGeneration();
    getRepairs();
    fillPanelData();
    fillCleaningTable();
    if(model.getLastOverview().equals("OverviewAn")){
      manufacturers.setDisable(true);
      models.setDisable(true);
      managePanels.setDisable(true);
    }

  }

  public void initialize(URL url, ResourceBundle rb)
  {
  }

  public void fillPanelData() throws SQLException
  {
    serialNumberValueLabel.setText(String.valueOf(model.getPanelsBySn(model.getSelectedSn()).get(0).getSerial_number()));
    locationValueLabel.setText(String.valueOf(model.getPanelsBySn(model.getSelectedSn()).get(0).getLocation()));
    installationTimeValueLabel.setText(model.getPanelsBySn(model.getSelectedSn()).get(0).getInstallationTime());
    statusValueLabel.setText(model.getPanelsBySn(model.getSelectedSn()).get(0).getStatus());
    angleNumberValueLabel.setText(String.valueOf(model.getPanelsBySn(model.getSelectedSn()).get(0).getAngle()));
    typeNumberValueLabel.setText(model.getPanelsBySn(model.getSelectedSn()).get(0).getType());
  }

  public Region getRoot()
  {
    return root;
  }

  public void fillValuesTable() throws SQLException
  {
    String startDate = startTextField.getText();
    String endDate = endTextField.getText();
    intensityColumn.setCellValueFactory(new PropertyValueFactory<>("intensity"));
    voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
    powerColumn.setCellValueFactory(new PropertyValueFactory<>("power"));
    solarFluxColumn.setCellValueFactory(new PropertyValueFactory<>("solarFlux"));
    timeStampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
    pvVariablesTable.getItems().clear();
    for (int i = 0; i < model.getPvLogValues(startDate,endDate,model.getSelectedSn()).size(); i++){
      pvVariablesTable.getItems().add(model.getPvLogValues(startDate,endDate, model.getSelectedSn()).get(i));
    }
  }

  public void fillCleaningTable()throws SQLException{
    cleaningDate.setCellValueFactory(new PropertyValueFactory<>("cleaningTime"));
    cleaningTable.getItems().clear();for (int i = 0; i < model.getCleaningLogBySn(model.getSelectedSn()).size(); i++){
      cleaningTable.getItems().add(model.getCleaningLogBySn(model.getSelectedSn()).get(i));
    }
  }

  public void getCurrentGeneration() throws SQLException
  {
    curentGenerationLabelValue.setText(String.valueOf(model.getIndividualGeneration(model.getSelectedSn())));
  }

  public void getRepairs()throws SQLException{
   dateColumn.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
   employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
   repairsTable.getItems().clear();
   for (int i = 0; i < model.getRepairsBySn(model.getSelectedSn()).size(); i++){
     repairsTable.getItems().add(model.getRepairsBySn(model.getSelectedSn()).get(i));
   }
  }

  public void selectTimePeriod() throws SQLException
  {
    ArrayList<PhotovoltaicPanel> lectures=  model.getPvLogValues(startTextField.getText(), endTextField.getText(),model.getSelectedSn());
    XYChart.Series series = new XYChart.Series();
    System.out.println(lectures.get(0).getTimestamp());
    System.out.println(lectures.get(0).getPower());
    for (int i = 0; i < lectures.size(); i++){
      series.getData().add(new XYChart.Data<>(lectures.get(i).getTimestamp(), lectures.get(i).getPower()));
    }

    graph.getData().addAll(series);
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

  public void loadRepairs()
  {
    viewHandler.openView("Repairs");
  }
  public void loadModels(){
    viewHandler.openView("Models");
  }

}
