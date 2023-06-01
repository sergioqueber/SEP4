package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.PhotovoltaicPanel;
import model.Repairs;
import model.SolarPanel;
import model.ThermalPanel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SinglePanelThermalController implements Initializable
{
  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML private Button applyButton;
  @FXML private Button graphButton;

  @FXML private TableView<SolarPanel> cleaningTable;
  @FXML
  private TableColumn<SolarPanel,String> cleaningDate;

  @FXML private TextField endTextField;

  @FXML private TableView<ThermalPanel> thVariablesTable;
  @FXML
  private TableColumn<PhotovoltaicPanel, Double> ambientTempColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, Double> finalTempColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, String> timeStampColumn;

  @FXML
  private TableColumn<PhotovoltaicPanel, ?> initialTempColumn;
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



  public SinglePanelThermalController()
  {
  }

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
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
    initialTempColumn.setCellValueFactory(new PropertyValueFactory<>("initialTemp"));
    finalTempColumn.setCellValueFactory(new PropertyValueFactory<>("finalTemp"));
    ambientTempColumn.setCellValueFactory(new PropertyValueFactory<>("ambientTemp"));
    solarFluxColumn.setCellValueFactory(new PropertyValueFactory<>("solarFlux"));
    timeStampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
    thVariablesTable.getItems().clear();
    for (int i = 0; i < model.getThLogValues(startDate,endDate,
        model.getSelectedSn()).size(); i++){
      thVariablesTable.getItems().add(model.getThLogValues(startDate,endDate,model.getSelectedSn()).get(i));
    }
  }

  public void fillCleaningTable()throws SQLException{
    cleaningDate.setCellValueFactory(new PropertyValueFactory<>("cleaningTime"));
    cleaningTable.getItems().clear();
    for (int i = 0; i < model.getCleaningLogBySn(model.getSelectedSn()).size(); i++){
      cleaningTable.getItems().add(model.getCleaningLogBySn(model.getSelectedSn()).get(i));
    }
  }


  public void getRepairs()throws SQLException{
   dateColumn.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
   employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
   repairsTable.getItems().clear();
   for (int i = 0; i < model.getRepairsBySn(model.getSelectedSn()).size(); i++){
     repairsTable.getItems().add(model.getRepairsBySn(model.getSelectedSn()).get(i));
   }
  }
/*
  public void selectTimePeriod() throws SQLException
  {
    ArrayList<ThermalPanel> lectures = model.getThLogValues(startTextField.getText(), endTextField.getText(),
        model.getSelectedSn());
    XYChart.Series series = new XYChart.Series();
    System.out.println(lectures.get(0).getTimestamp());
    ;
    for (int i = 0; i < lectures.size(); i++)
    {
      series.getData().add(new XYChart.Data<>(lectures.get(i).getTimestamp(),
          lectures.get(i).getPower()));
    }

    graph.getData().addAll(series);
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
