package view;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import Connection.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import model.Notification;

import java.util.Optional;

public class OverviewController
{

  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  @FXML
  private Button applyButton;

  @FXML
  private Menu compararison;

  @FXML
  private Label consumptionLabel;

  @FXML
  private TextField electricity;

  @FXML
  private Label endLabel;

  @FXML
  private TextField generation;

  @FXML
  private Label generationLabel;

  @FXML
  private TextField heating;

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
  private TableColumn<?, ?> serialNumberColumn;

  @FXML
  private TableView<?> solarPanelTable;

  @FXML
  private Label startLabel;

  @FXML
  private TableColumn<?, ?> statusColumn;

  @FXML
  private TextField textFieldEnd;

  @FXML
  private TextField textFieldStart;

  @FXML
  private Label timePeriodLabel;

  @FXML
  private TableColumn<?, ?> typeColumn;

  @FXML
  private Button updateButton;
  public OverviewController(){};

  public void init(ViewHandler viewHandler, Region root, Model model){
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
    solarPanelTable.getItems().clear();
  }

  public Region getRoot(){
    return root;
  }

  public void fillTable (){

  }
}
