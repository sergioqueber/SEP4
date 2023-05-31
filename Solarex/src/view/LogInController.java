package view;

import Connection.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

import java.sql.SQLException;
public class LogInController
{
  private Region root;
  private ViewHandler viewHandler;
  private Model model;


  @FXML
  private Button continueButton;

  @FXML
  private Label idLabel;

  @FXML
  private TextField idTextField;

  @FXML
  private Label passwordLabel;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private Label welcomeLabel;
  @FXML
  private Button newUserButton;

  public LogInController(){}

  public void init(ViewHandler viewHandler, Region root, Model model)
      throws SQLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.model = model;
  }


  private void incorrectLogIn(){
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Incorrect LogIn");
    alert.setContentText("Username or password do not match");
    alert.show();
  }
  public void logIn() throws SQLException
  {
    int id = Integer.parseInt(idTextField.getText());
    String password = passwordTextField.getText();
    if(model.logInCheck(id,password)){
      if(model.getEmployee(id).getRole().getId() == 1){
        viewHandler.openView("OverviewAn");
        model.setLastOverview("OverviewAn");
      }else if(model.getEmployee(id).getRole().getId() == 2){
        viewHandler.openView("OverviewTe");
        model.setLastOverview("OverviewTe");
      }
    }
    else {
      incorrectLogIn();
    }
  }

  public Region getRoot(){
    return root;
  }

  public void loadNewUser(){
    viewHandler.openView("New User");
  }
}
