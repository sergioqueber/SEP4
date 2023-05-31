package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Employee;
import model.Repairs;
import model.SolarPanel;

import java.sql.SQLException;
public class RepairsController
{
  @FXML
  private Button applyButton;

  @FXML
  private Menu cleaning;

  @FXML private MenuItem cleaningItem;

  @FXML
  private MenuItem managePanelsItem;

  @FXML
  private MenuItem manufacturerItem;

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

  @FXML
  private TableColumn<?, ?> dateColumn;

  @FXML
  private Button editButton;

  @FXML
  private TableColumn<Employee, Double> employeeIdColumn;

  @FXML
  private TextField endDateTextField;

  @FXML
  private CheckBox locationCheckbox;

  @FXML
  private TableColumn<Repairs, Integer> locationColumn;

  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem openCleaning;


  @FXML
  private Menu overview;

  @FXML
  private TableView<Repairs> repairsTable;

  @FXML
  private Button saveButton;

  @FXML
  private Button seachButton;

  @FXML
  private TextField searchTextField;


  @FXML
  private CheckBox snCheckBox;

  @FXML
  private TableColumn<?, ?> snColumn;

  @FXML
  private TableColumn<?, ?> snColumnRepairs;

  @FXML
  private TableView<SolarPanel> solarPanelsTable;

  @FXML
  private TextField startDateTextField;

  @FXML
  private TableColumn<?, ?> statusColumn;

  @FXML
  private TableColumn<?, ?> typeColumn;
  @FXML
  private Button refreshButton;
  @FXML
  private Button refreshRepairsButton;
  @FXML
  private TextField employeeIdTextField;
  @FXML
  private TextField dateTextField;

  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  public RepairsController(){};
  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
    fillSolarPanelsTable();
    fillRepairsTable();
  }

  public void setRoot(Region root)
  {
    this.root = root;
  }

  public Region getRoot(){
    return root;
  }

  public void fillSolarPanelsTable () throws SQLException
  {

    snColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    solarPanelsTable.getItems().clear();
    for (int i = 0; i < model.getAllPanels().size() ; i++)
    {
      solarPanelsTable.getItems().add(model.getAllPanels().get(i));
    }
  }
  public void search() throws SQLException
  {
    if(snCheckBox.isSelected()){
      int sn = Integer.parseInt(searchTextField.getText());
      snColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
      statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
      locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
      solarPanelsTable.getItems().clear();
      for (int i = 0; i < model.getPanelsBySn(sn).size() ; i++)
      {
        //System.out.println(model.getAllPanels().get(i));
        solarPanelsTable.getItems().add(model.getPanelsBySn(sn).get(i));
      }
    }
    else if (locationCheckbox.isSelected()){
      int location = Integer.parseInt(searchTextField.getText());
      snColumn.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
      statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
      locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
      solarPanelsTable.getItems().clear();
      for (int i = 0; i < model.getPanelsByLocation(location).size() ; i++)
      {
        solarPanelsTable.getItems().add(model.getPanelsByLocation(location).get(i));
      }
    }
    resetFields();
  }
  public void fillRepairsTable() throws SQLException
  {
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
    snColumnRepairs.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
    employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
    repairsTable.getItems().clear();
    for(int i = 0; i < model.getRepairs().size(); i++){
      repairsTable.getItems().add(model.getRepairs().get(i));
    }
  }

  public void filterRepairsTable() throws SQLException{
    String startDate = startDateTextField.getText();
    String endDate = endDateTextField.getText();
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("repairDate"));
    snColumnRepairs.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
    employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
    repairsTable.getItems().clear();
    for(int i = 0; i < model.getRepairsByDate(startDate,endDate).size(); i++){
      repairsTable.getItems().add(model.getRepairsByDate(startDate,endDate).get(i));
    }
    resetFields();
  }
  public void addNewRepair() throws SQLException{
    int employeeId = Integer.parseInt(employeeIdTextField.getText());
    String date = dateTextField.getText();
    double sn = solarPanelsTable.getSelectionModel().getSelectedItem().getSerial_number();
    model.registerNewRepair(employeeId,date,sn);
    fillRepairsTable();
    resetFields();
  }
  public void removeRepair() throws SQLException{
    int id = model.getRepairs().get(repairsTable.getSelectionModel().getSelectedIndex()).getId();
    model.deleteRepairById(id);
    fillRepairsTable();
  }
  public void resetFields(){
    searchTextField.setText("");
    locationCheckbox.setSelected(false);
    snCheckBox.setSelected(false);
  }

  public void loadOverview(){
    viewHandler.openView(model.getLastOverview());
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

  public void loadModels()
  {
    viewHandler.openView("Models");
  }

  public void loadWeather()
  {
    viewHandler.openView("Weather");
  }



}
