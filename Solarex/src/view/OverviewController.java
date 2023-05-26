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
import model.SolarPanel;

import java.sql.SQLException;
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

  public OverviewController(){};

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
    solarPanelTable.getItems().clear();
    fillTable();
    generationLabel.setText(String.valueOf(model.getGeneration()));
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

  public void resetFields(){
    textFieldSeach.setText("");
  }

}
