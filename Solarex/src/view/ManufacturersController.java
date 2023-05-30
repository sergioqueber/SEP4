package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Manufacturer;

import java.sql.SQLException;

public class ManufacturersController
{

  @FXML
  private ChoiceBox<String> choiceBox;

  @FXML
  private Button editButton;

  @FXML
  private Label messageField;

  @FXML
  private TableView<Manufacturer> manufacturerTable;

  @FXML
  private TableColumn<Manufacturer, String> emailColumn;

  @FXML
  private TextField emailText;

  @FXML
  private TableColumn<Manufacturer, String> manufacturerColumn;

  @FXML
  private Label manufacturerLabel;

  @FXML
  private TextField nameText;

  @FXML
  private TableColumn<Manufacturer, Double> phoneNoColumn;

  @FXML
  private TextField phoneNoText;

  @FXML
  private Button removeButton;

  @FXML
  private Button saveButton;

  @FXML
  private ScrollBar scrollBar;

  @FXML
  private Button searchButton;

  @FXML
  private TextField searchField;

  private ViewHandler viewHandler;
  private Model model;
  private Region root;


  public ManufacturersController()
  {}

  public void init(ViewHandler viewHandler, Region root, Model model) throws
      SQLException
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    fillManufacturers();
    choiceBox.getItems().add(0, "Name");
    choiceBox.getItems().add(1, "Email");

  }

  public Region getRoot(){
    return root;
  }

  public void fillManufacturers () throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    manufacturerTable.getItems().clear();
    for (int i = 0; i < model.getManufacturers().size(); i++)
    {
      System.out.println(model.getManufacturers().get(i));
      manufacturerTable.getItems().add(model.getManufacturers().get(i));
    }
  }

  private String manufacturerName;

  public void setManufacturerName(String manufacturerName)
  {
    this.manufacturerName = manufacturerName;
  }

  public String getManufacturerName()
  {
    return manufacturerName;
  }

  public void onSaveButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
    manufacturerTable.getItems().clear();
    if(phoneNoText.getText().isEmpty() || nameText.getText().isEmpty() || emailText.getText().isEmpty())
    {
      messageField.setText("Fill out all the fields in order to proceed.");
    }
    Manufacturer manufacturer = new Manufacturer();
    if(getEdit())
       {
         String newName = nameText.getText();
        String newEmail = emailText.getText();
        String newPhoneNo = phoneNoText.getText();
        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName(newName);
        newManufacturer.setEmail(newEmail);
        newManufacturer.setPhoneNumber(Double.parseDouble(newPhoneNo));
        model.updateManufacturer(getManufacturerName(), newManufacturer);
        manufacturerTable.getItems().remove(manufacturer);
        manufacturerTable.getItems().add(newManufacturer);
        fillManufacturers();
       }
    else
    {
      String name = nameText.getText();
      String email = emailText.getText();
      String phoneNo = phoneNoText.getText();
      manufacturer.setName(name);
      manufacturer.setEmail(email);
      manufacturer.setPhoneNumber(Double.parseDouble(phoneNo));
      fillManufacturers();
      manufacturerTable.getItems().add(manufacturer);
      model.addManufacturer(name, email, Double.parseDouble(phoneNo));
    }
      nameText.clear();
      emailText.clear();
      phoneNoText.clear();
      messageField.setText("");
    }



  public void onSearchButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    manufacturerTable.getItems().clear();
    if (choiceBox.getValue().equals("Name"))
    {
      if (searchField.getText() != null)
      {
        String name = searchField.getText();
        for (int i = 0; i < model.filterByName(name).size(); i++)
        {
          manufacturerTable.getItems().add(model.filterByName(name).get(i));
        }
      }
    }
    else if (choiceBox.getValue().equals("Email"))
    {
      if (searchField.getText() != null)
      {
        String email = searchField.getText();
        for (int i = 0; i < model.filterByEmail(email).size(); i++)
        {
            manufacturerTable.getItems().add(model.filterByEmail(email).get(i));
        }
      }
    }
  }



  public void onRemoveButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    if (manufacturerTable.getSelectionModel().isSelected(manufacturerTable.getSelectionModel().getSelectedIndex()))
    {
      model.getManufacturers().remove(manufacturerTable.getSelectionModel().getSelectedIndex());
      model.deleteManufacturer(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()));
    }
    manufacturerTable.getItems().clear();
    fillManufacturers();
  }

  public void onEditButtonClicked() throws SQLException
  {
    setEdit(true);
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    if(manufacturerTable.getSelectionModel().isSelected(manufacturerTable.getSelectionModel().getSelectedIndex()))
    {
      messageField.setText("");
      nameText.setText(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()).getName());
      setManufacturerName(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()).getName());
      emailText.setText(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()).getEmail());
      phoneNoText.setText(String.valueOf(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()).getPhoneNumber()));
    }
    else
    {
      messageField.setText("Select a manufacturer from the table in order to proceed.");
    }
  }

  private boolean edit = false;

  public void setEdit(boolean edit)
  {
    this.edit = edit;
  }
  public boolean getEdit()
  {
    return edit;
  }


}