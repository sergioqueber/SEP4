package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Manufacturer;
import model.SolarPanel;

import java.sql.Date;
import java.sql.SQLException;

public class ManagePanelsController
{
  @FXML
  @FXML
  private Button addNewButton;

  @FXML
  private TextField angleText;

  @FXML
  private TextField cleaningTimeText;

  @FXML
  private Button editButton;

  @FXML
  private ChoiceBox<?> factoryChoiceBpx;

  @FXML
  private TextField installationTimeText;

  @FXML
  private TextField locationText;

  @FXML
  private ChoiceBox<?> modelChoiceBox;

  @FXML
  private CheckBox pvCheckBox;

  @FXML
  private Label pvLabel;

  @FXML
  private Label pvLabel1;

  @FXML
  private Label pvLabel11;

  @FXML
  private Label pvLabel111;

  @FXML
  private Label pvLabel1121;

  @FXML
  private Label pvLabel11211;

  @FXML
  private Label pvLabel112111;

  @FXML
  private Label pvLabel11212;

  @FXML
  private Label pvLabel11213;

  @FXML
  private Label pvLabel12;

  @FXML
  private Label pvLabel13;

  @FXML
  private Label pvLabel2;

  @FXML
  private TableColumn<?, ?> pvLocationC;

  @FXML
  private TableColumn<?, ?> pvManufacturerC;

  @FXML
  private TableView<?> pvPanelTable;

  @FXML
  private TableColumn<?, ?> pvSNC;

  @FXML
  private Button pvSearch;

  @FXML
  private TableColumn<?, ?> pvStatusC;

  @FXML
  private TextField pvText;

  @FXML
  private TableColumn<?, ?> pvTypeC;

  @FXML
  private Button removeButton;

  @FXML
  private Button saveButton;

  @FXML
  private TextField snText;

  @FXML
  private TextField statusText;

  @FXML
  private CheckBox thCheckBox;

  @FXML
  private Label tpLabel;

  @FXML
  private TableColumn<?, ?> tpLocationC;

  @FXML
  private TableColumn<?, ?> tpManufacturerC;

  @FXML
  private TableView<?> tpPanelTable;

  @FXML
  private TableColumn<?, ?> tpSNC;

  @FXML
  private Button tpSearch;

  @FXML
  private TableColumn<?, ?> tpStatusC;

  @FXML
  private TextField tpText;

  @FXML
  private TableColumn<?, ?> tpTypeC;

  private ViewHandler viewHandler;
  private Model model;
  private Region root;


  public ManagePanelsController()
  {}

  public Region getRoot()
  {
  return root;
  }

  public void init(ViewHandler viewHandler, Region root, Model model) throws
      SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

  }

  /*public void onAddNewPanelButton() throws SQLException
  {
    pvSNC.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    pvLocationC.setCellValueFactory(new PropertyValueFactory<>("location"));
    pvStatusC.setCellValueFactory(new PropertyValueFactory<>("status"));
    pvTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
    pvModelC.setCellValueFactory(new PropertyValueFactory<>("model"));
    pvPanelTable.getItems();
    if (pvCheckBox.isSelected())
    {
      Double serialNo = Double.parseDouble(pvText.getText());
      int location = Integer.parseInt(locationText.getText());
      boolean status = Boolean.valueOf(statusText.getText());
      String type = "Photovoltaic panel";
      Date installation_date = installationTimeText.getText()
      model.addPhotovoltaicPanel()

    }
    else if ()
    {

    }

   */




}

