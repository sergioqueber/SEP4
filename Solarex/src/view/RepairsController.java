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
public class RepairsController
{
  @FXML
  private Button applyButton;

  @FXML
  private Menu cleaning;

  @FXML
  private TableColumn<?, ?> dateColumn;

  @FXML
  private Button editButton;

  @FXML
  private TableColumn<?, ?> employeeIdColumn;

  @FXML
  private TextField endDateTextField;

  @FXML
  private CheckBox locationCheckbox;

  @FXML
  private TableColumn<?, ?> locationColumn;

  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem openCleaning;

  @FXML
  private MenuItem openOverview;

  @FXML
  private MenuItem openSetTargets;

  @FXML
  private Menu overview;

  @FXML
  private TableView<?> repairsTable;

  @FXML
  private Button saveButton;

  @FXML
  private Button seachButton;

  @FXML
  private TextField searchTextField;

  @FXML
  private Menu setTargets;

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
  public void resetFields(){
    searchTextField.setText("");
    locationCheckbox.setSelected(false);
    snCheckBox.setSelected(false);
  }
}
