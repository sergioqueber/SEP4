package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private Region root;


  public void openView(String window){
    root = null;
    switch(window){
      case "Home Page":
        root =
        break;
      case "Manage Factors":
        root =
      case "Manage Countries":
        root =
        break;
      case "Edit Country":
        root =
      case "IMS Calculator":
        root =

        break;
      case "Home Page 2":
        root =
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null){
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

}
