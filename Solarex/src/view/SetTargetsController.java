package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

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
    viewHandler.openView(model.getLastOverview());
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

  public void loadWeather()
  {
    viewHandler.openView("Weather");
  }

  public void loadModels()
  {
    viewHandler.openView("Models");
  }

  public void resetField()
  {
    pvTargetTextField.setText("");
    thTargetTextField.setText("");
  }
}
