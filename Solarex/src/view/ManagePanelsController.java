package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Factory;
import model.PhotovoltaicPanel;
import model.ThermalPanel;

import java.sql.SQLException;

public class ManagePanelsController
{
  @FXML
  private Button addNewButton;

  @FXML
  private TextField angleText;

  @FXML
  private TextField cleaningTimeText;

  @FXML
  private Button editButton;

  @FXML
  private ChoiceBox<Factory> factoryChoiceBox;

  @FXML
  private TextField installationTimeText;

  @FXML
  private TextField locationText;

  @FXML
  private ChoiceBox<model.Model> modelChoiceBox;

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
  private TableColumn<Integer, Integer> pvLocationC;

  @FXML
  private TableColumn<Double, Double> pvModelC;

  @FXML
  private TableView<PhotovoltaicPanel> pvPanelTable;

  @FXML
  private TableColumn<Double, Double> pvSNC;

  @FXML
  private Button pvSearch;

  @FXML
  private TableColumn<Boolean, Boolean> pvStatusC;

  @FXML
  private TextField pvText;

  @FXML
  private TableColumn<String, String> pvTypeC;

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
  private TableColumn<Integer, Integer> tpLocationC;

  @FXML
  private TableColumn<Double, Double> tpModelC;

  @FXML
  private TableView<ThermalPanel> thPanelTable;

  @FXML
  private TableColumn<Double, Double> tpSNC;

  @FXML
  private Button tpSearch;

  @FXML
  private TableColumn<Boolean, Boolean> tpStatusC;

  @FXML
  private TextField tpText;

  @FXML
  private TableColumn<String, String> tpTypeC;

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
    modelChoiceBox.getItems().addAll(model.getModels());
    factoryChoiceBox.getItems().addAll(model.getFactories());
    fillPVPanels();
    fillTHPanels();
  }

  public void fillPVPanels() throws SQLException
  {
    pvSNC.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    pvLocationC.setCellValueFactory(new PropertyValueFactory<>("location"));
    pvStatusC.setCellValueFactory(new PropertyValueFactory<>("status"));
    pvTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
    pvModelC.setCellValueFactory(new PropertyValueFactory<>("model"));
    pvPanelTable.getItems().clear();
    for (int i = 0; i < model.getAllPanels().size(); i++)
    {
      System.out.println(model.getAllPanels().get(i));
      pvPanelTable.getItems().add(model.getAllPVPanels().get(i));
    }
  }

  public void fillTHPanels() throws SQLException
  {
    tpSNC.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    tpLocationC.setCellValueFactory(new PropertyValueFactory<>("location"));
    tpStatusC.setCellValueFactory(new PropertyValueFactory<>("status"));
    tpTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
    tpModelC.setCellValueFactory(new PropertyValueFactory<>("model"));
    thPanelTable.getItems().clear();
    for (int i = 0; i < model.getAllPanels().size(); i++)
    {
      System.out.println(model.getAllPanels().get(i));
      thPanelTable.getItems().add(model.getAllTHPanels().get(i));
    }
  }

  public void onAddNewPanelButton() throws SQLException
  {
    pvSNC.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    pvLocationC.setCellValueFactory(new PropertyValueFactory<>("location"));
    pvStatusC.setCellValueFactory(new PropertyValueFactory<>("status"));
    pvTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
    pvModelC.setCellValueFactory(new PropertyValueFactory<>("model"));
    pvPanelTable.getItems();
    if (pvCheckBox.isSelected())
    {
      Double serialNo = Double.parseDouble(snText.getText());
      int location = Integer.parseInt(locationText.getText());
      boolean status = Boolean.valueOf(statusText.getText());
      String type = "Photovoltaic panel";
      String installationDate = installationTimeText.getText();
      int angle = Integer.parseInt(angleText.getText());
      double modelNo = modelChoiceBox.getSelectionModel().getSelectedIndex();
      int factoryId = factoryChoiceBox.getSelectionModel().getSelectedIndex();
      model.addPhotovoltaicPanel(serialNo, location, installationDate, status,
          angle, modelNo, factoryId, type);
      pvPanelTable.getItems().add(model.addPhotovoltaicPanel(serialNo, location, installationDate, status, angle,
          modelNo, factoryId, type));

    }
    else if (thCheckBox.isSelected())
    {
      Double serialNo = Double.parseDouble(snText.getText());
      int location = Integer.parseInt(locationText.getText());
      boolean status = Boolean.valueOf(statusText.getText());
      String type = "Thermal panel";
      String installationDate = installationTimeText.getText();
      int angle = Integer.parseInt(angleText.getText());
      double modelNo = modelChoiceBox.getSelectionModel().getSelectedIndex();
      int factoryId = factoryChoiceBox.getSelectionModel().getSelectedIndex();
      model.addThermalPanel(serialNo, location, installationDate, status, angle,
          modelNo, factoryId, type);
      thPanelTable.getItems().add(model.addThermalPanel(serialNo, location, installationDate, status, angle,
          modelNo, factoryId, type));
    }
    fillPVPanels();
    fillTHPanels();
    snText.clear();
    locationText.clear();
    statusText.clear();
    angleText.clear();
    modelChoiceBox.getSelectionModel();
    factoryChoiceBox.getSelectionModel();
  }






}

