package view;
import Connection.Model;
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

import java.sql.SQLException;

public class SetTargetsController
{
  @FXML
  private Menu comparison;

  @FXML
  private CheckBox criticalIntensityCheckBox;

  @FXML
  private CheckBox criticalVoltageCheckbox;

  @FXML
  private Menu managePanels;

  @FXML
  private MenuBar menu;

  @FXML
  private Menu overview;

  @FXML
  private Label pvLabel;

  @FXML
  private TextField pvTargetTextField;

  @FXML
  private Label thLabel;

  @FXML
  private TextField thTargetTextField;
  @FXML
  private Button pvApplyButton;
  @FXML
  private Button thApplyButton;
  @FXML
  private MenuItem overviewItem;

  private Region root;
  private ViewHandler viewHandler;
  private Model model;

  public SetTargetsController(){}

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;

  }

  public void setCriticalValue() throws SQLException
  {
    if(criticalIntensityCheckBox.isSelected()){
      model.setAlertIntensity(Double.parseDouble(pvTargetTextField.getText()));

    }
    else if (criticalVoltageCheckbox.isSelected()){
      model.setAlertVoltage(Double.parseDouble(pvTargetTextField.getText()));
    }
  }

  public Region getRoot(){
    return root;
  }

  public void loadOverview(){
    viewHandler.openView("Overview");
  }

  public void resetField(){
    pvTargetTextField.setText("");
    thTargetTextField.setText("");
  }
}
