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
  @FXML private Button addNewButton;

  @FXML private TextField angleText;

  @FXML private TextField cleaningTimeText;

  @FXML private Button editButton;

  @FXML private ChoiceBox<Factory> factoryChoiceBox;

  @FXML private TextField installationTimeText;

  @FXML private TextField locationText;

  @FXML private ChoiceBox<model.Model> modelChoiceBox;

  @FXML private CheckBox pvCheckBox;

  @FXML private ChoiceBox<String> statusChoiceBox;
  @FXML private Label pvLabel;

  @FXML private Label pvLabel1;

  @FXML private Label pvLabel11;

  @FXML private Label pvLabel111;

  @FXML private Label pvLabel1121;

  @FXML private Label pvLabel11211;

  @FXML private Label pvLabel112111;

  @FXML private Label pvLabel11212;

  @FXML private Label pvLabel11213;

  @FXML private Label pvLabel12;

  @FXML private Label pvLabel13;

  @FXML private Label pvLabel2;

  @FXML private TableColumn<Integer, Integer> pvLocationC;

  @FXML private TableColumn<Double, Double> pvModelC;

  @FXML private TableView<PhotovoltaicPanel> pvPanelTable;

  @FXML private TableColumn<PhotovoltaicPanel, Double> pvSNC;

  @FXML private Button pvSearch;

  @FXML private TableColumn<PhotovoltaicPanel, Boolean> pvStatusC;

  @FXML private TextField pvText;

  @FXML private TableColumn<PhotovoltaicPanel, String> pvTypeC;

  @FXML private Button removeButton;

  @FXML private Button saveButton;

  @FXML private TextField snText;

  @FXML private TextField statusText;

  @FXML private CheckBox thCheckBox;

  @FXML private Label tpLabel;

  @FXML private TableColumn<ThermalPanel, Integer> tpLocationC;

  @FXML private TableColumn<ThermalPanel, Double> tpModelC;

  @FXML private TableView<ThermalPanel> thPanelTable;

  @FXML private TableColumn<Double, Double> tpSNC;

  @FXML private Button tpSearch;

  @FXML private TableColumn<ThermalPanel, Boolean> tpStatusC;

  @FXML private TextField tpText;

  @FXML private TableColumn<ThermalPanel, String> tpTypeC;

  private ViewHandler viewHandler;
  private Model model;
  private Region root;

  public ManagePanelsController()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    modelChoiceBox.getItems().addAll(model.getModels());
    factoryChoiceBox.getItems().addAll(model.getFactories());
    statusChoiceBox.getItems().add(0, "Active");
    statusChoiceBox.getItems().add(1, "Maintenance");
    statusChoiceBox.getItems().add(2, "Removed");
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
    for (int i = 0; i < model.getAllPVPanels().size(); i++)
    {
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
    for (int i = 0; i < model.getAllTHPanels().size(); i++)
    {
      thPanelTable.getItems().add(model.getAllTHPanels().get(i));
    }
  }


  public void onAddNewPanelButton() throws SQLException
  {
    Double serialNo = Double.parseDouble(snText.getText());
    int location = Integer.parseInt(locationText.getText());
    String status = statusChoiceBox.getValue();
    int angle = Integer.parseInt(angleText.getText());
    if (pvCheckBox.isSelected())
    {
      {
        String type = "Photovoltaic";
        double modelNo = model.getModels()
            .get(modelChoiceBox.getSelectionModel().getSelectedIndex()).getModelNo();
        int factoryId = model.getFactories()
            .get(factoryChoiceBox.getSelectionModel().getSelectedIndex()).getId();
        model.addPhotovoltaicPanel(serialNo, location, status, angle, modelNo,
            factoryId, type);
        fillPVPanels();
      }
    }
    else if (thCheckBox.isSelected())
    {
      String type = "Thermal";
      double modelNo = model.getModels().get(modelChoiceBox.getSelectionModel().getSelectedIndex())
          .getModelNo();
      int factoryId = model.getFactories().get(factoryChoiceBox.getSelectionModel().getSelectedIndex())
          .getId();
      model.addThermalPanel(serialNo, location, status, angle, modelNo,
          factoryId, type);
      fillTHPanels();
    }
    snText.clear();
    locationText.clear();
    angleText.clear();
    modelChoiceBox.getSelectionModel().clearSelection();
    factoryChoiceBox.getSelectionModel().clearSelection();
    statusChoiceBox.getSelectionModel().clearSelection();
  }

  public void onRemoveButton() throws SQLException
  {
    if (pvPanelTable.getSelectionModel().isSelected(pvPanelTable.getSelectionModel().getSelectedIndex()))
    {
      model.getAllPVPanels().remove(pvPanelTable.getSelectionModel().getSelectedIndex());
      model.deletePVPanel(model.getAllPVPanels().remove(pvPanelTable.getSelectionModel().getSelectedIndex()));
    }
    else if (thPanelTable.getSelectionModel().isSelected(thPanelTable.getSelectionModel().getSelectedIndex()))
    {
      model.getAllTHPanels().remove(thPanelTable.getSelectionModel().getSelectedIndex());
      model.deleteTHPanel(model.getAllTHPanels().remove(thPanelTable.getSelectionModel().getSelectedIndex()));
    }
    pvPanelTable.getItems().clear();
    thPanelTable.getItems().clear();
    fillPVPanels();
    fillTHPanels();

  }


  public void onPVSearchButton() throws SQLException
  {
    pvSNC.setCellValueFactory(new PropertyValueFactory<>("serial_number"));
    pvLocationC.setCellValueFactory(new PropertyValueFactory<>("location"));
    pvStatusC.setCellValueFactory(new PropertyValueFactory<>("status"));
    pvTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
    pvModelC.setCellValueFactory(new PropertyValueFactory<>("model"));
    pvPanelTable.getItems().clear();
    if (pvText.getText() != null)
    {
      double sn = Double.parseDouble(pvText.getText());
      for (int i = 0; i < model.getPanelsBySerialNo(sn).size(); i++)
      {
        PhotovoltaicPanel photovoltaicPanel = new PhotovoltaicPanel(model.getPanelsBySerialNo(sn).get(i));
        pvPanelTable.getItems().add(photovoltaicPanel);
      }
    }
  }

  public void loadManufacturersView()
  {
    viewHandler.openView("Manufacturers");
  }

}

