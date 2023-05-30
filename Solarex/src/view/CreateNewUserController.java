package view;

import Connection.Model;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import Connection.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateNewUserController
{

  @FXML private PasswordField adminPasswordTextField;

  @FXML private Button backButton;

  @FXML private TextField cprTextField;

  @FXML private Button createUserButton;

  @FXML private TextField emailTextField;

  @FXML private Label emalLabel;

  @FXML private TextField employmentDateTextField;

  @FXML private Label emplymentDateLabel;

  @FXML private Label fNameLabel;

  @FXML private TextField fNameTextField;

  @FXML private TextField factoryNameTextField;

  @FXML private Label idLabel;

  @FXML private TextField idTextField;

  @FXML private Label lNameLabel;

  @FXML private TextField lNameTextFiled;

  @FXML private Label passwordLabel;

  @FXML private PasswordField passwordTextField;

  @FXML private Label phoneNumberLabel;

  @FXML private TextField phoneNumberTextField;

  @FXML private Label roleIdLabel;

  @FXML private TextField roleIdTextField;

  private Region root;
  private ViewHandler viewHandler;
  private Model model;


  public CreateNewUserController()
  {
  }

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
  }

  public void createNewUser() throws SQLException
  {
    if(model.getAdminPassword().equals(adminPasswordTextField.getText()))
    {
      String cpr = cprTextField.getText();
      String fName = fNameTextField.getText();
      String lName = lNameTextFiled.getText();
      String email = emailTextField.getText();
      Double phoneNo = Double.parseDouble(phoneNumberTextField.getText());
      String employmentDate = employmentDateTextField.getText();
      String password = passwordTextField.getText();
      Role role = new Role(Integer.parseInt(roleIdTextField.getText()));
      Factory factory = new Factory(Integer.parseInt(factoryNameTextField.getText()));
      model.registerEmployee(cpr, fName, lName, email, phoneNo, employmentDate, password,
          role, factory);
      viewHandler.openView("LogIn");
    }
  }

  public void setRoot(Region root)
  {
    this.root = root;
  }

  public Region getRoot()
  {
    return root;
  }

  public void back(){
    viewHandler.openView(model.getLastOverview());
  }
}
