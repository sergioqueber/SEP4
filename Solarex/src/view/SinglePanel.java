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

public class SinglePanel implements Initializable
{
  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML private Button applyButton;

  @FXML private TableView<?> cleaningTable;

  @FXML private Label cuurentGenerationLabelValue;

  @FXML private TextField endTextField;

  @FXML private TableView<?> pvVariablesTable;

  @FXML private TableView<?> repairsTable;

  @FXML private TextField startTextField;
  @FXML private LineChart<?, ?> graph;
  @FXML private NumberAxis powerOutPutAxis;

  @FXML private CategoryAxis timeAxis;

  public SinglePanel()
  {
  }

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
  }

  public void initialize(URL url, ResourceBundle rb)
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public void fillValuesTable(){

  }
}
