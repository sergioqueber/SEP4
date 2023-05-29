package view;

public class ManufacturersController
{
/*
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
    choiceBox.getItems().add(2, "Phone number");

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


  public void onSaveButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
    manufacturerTable.getItems().clear();
    if(phoneNoText.getText().isBlank() || nameText.getText().isBlank() || emailText.getText().isBlank())
    {
      messageField.setText("Fill out all the fields in order to proceed.");
    }
    String name = nameText.getText();
    String email = emailText.getText();
    String phoneNo = phoneNoText.getText();
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setName(name);
    manufacturer.setEmail(email);
    manufacturer.setPhoneNumber(Double.parseDouble(phoneNo));
    fillManufacturers();
    manufacturerTable.getItems().add(manufacturer);
    model.addManufacturer(name, email, Double.parseDouble(phoneNo));
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
    if(choiceBox.getValue() == "Name")
    {
      String name = searchField.getText();
      manufacturerTable.getItems().clear();
      for (int i = 0; i < model.getManufacturers().size(); i++)
      {
        model.filterByName(name);
        manufacturerTable.getItems().add(model.getManufacturers().get(i));
      }
    }
    else if (choiceBox.getValue() == "Email")
    {
      String name = searchField.getText();
      manufacturerTable.getItems().clear();
      for (int i = 0; i < model.getManufacturers().size(); i++)
      {
        model.filterByName(name);
        manufacturerTable.getItems().add(model.getManufacturers().get(i));
      }
    }

  }

  public void onRemoveButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    manufacturerTable.getItems().clear();
    if (manufacturerTable.getSelectionModel().isSelected(manufacturerTable.getSelectionModel().getSelectedIndex()))
    {
      model.getManufacturers().remove(manufacturerTable.getSelectionModel().getSelectedIndex());
      model.deleteManufacturer(model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex()));
    }
  }

  public void onEditButtonClicked() throws SQLException
  {
    manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    manufacturerTable.getItems().clear();
    if(manufacturerTable.getSelectionModel().isSelected(manufacturerTable.getSelectionModel().getSelectedIndex()))
    {
      String name = nameText.getText();
      String email = emailText.getText();
      String phoneNo = phoneNoText.getText();
      Manufacturer manufacturer = new Manufacturer();
      manufacturer.setName(name);
      manufacturer.setEmail(email);
      manufacturer.setPhoneNumber(Double.parseDouble(phoneNo));
      model.editManufacturer(manufacturer);
      model.getManufacturers().get(manufacturerTable.getSelectionModel().getSelectedIndex());
    }
  }


 */
}