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

  @FXML private TableView<?> cleaningTable;

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
  private ListView<Double> panelData;


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
  }

  public void initialize(URL url, ResourceBundle rb)
  {
  }

  public void fillPanelData() throws SQLException
  {
   panelData.getItems().add(model.getPanelsBySn(111111.0).get(0).getSerial_number());
   //panelData.getItems().add(model.getPanelsBySn(111111.0).get(0).getModel());
  // panelData.getItems().add(model.getPanelsBySn(111111.0).get(0).getAngle());
  // panelData.getItems().add(model.getPanelsBySn(111111.0).get(0).getLocation());



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
    timeStampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
    pvVariablesTable.getItems().clear();
    for (int i = 0; i < model.getPvLogValues(startDate,endDate).size(); i++){
      pvVariablesTable.getItems().add(model.getPvLogValues(startDate,endDate).get(i));
    }
  }

  public void getCurrentGeneration() throws SQLException
  {
    curentGenerationLabelValue.setText(String.valueOf(model.getIndividualGeneration(111111.0)));
  }

  public void getRepairs()throws SQLException{
   dateColumn.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
   employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
   repairsTable.getItems().clear();
   for (int i = 0; i < model.getRepairsBySn(111111.0).size(); i++){
     repairsTable.getItems().add(model.getRepairsBySn(111111.0).get(i));
   }
  }

  public void selectTimePeriod() throws SQLException
  {
    ArrayList<PhotovoltaicPanel> lectures=  model.getPvLogValues(startTextField.getText(), endTextField.getText());
    XYChart.Series series = new XYChart.Series();
    System.out.println(lectures.get(0).getTimestamp());
    System.out.println(lectures.get(0).getPower());
    for (int i = 0; i < lectures.size(); i++){
      series.getData().add(new XYChart.Data<>(lectures.get(i).getTimestamp(), lectures.get(i).getPower()));
    }

    graph.getData().addAll(series);
  }
}
