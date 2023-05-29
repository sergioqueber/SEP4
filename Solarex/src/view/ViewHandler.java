package view;

import Connection.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private Region root;
  private Model model;

  private OverviewController overviewController;

  private SetTargetsController setTargetsController;
  private LogInController logInController;

  private ManufacturersController manufacturersController;

  public ViewHandler(Model model){
    this.currentScene =  new Scene(new Region());
    this.model = model;
  }

  public void openView(String window){
    root = null;
    switch(window){
      case "OverviewAn":
        root = loadOverviewView("OverviewAn.fxml");
        break;
      case "Set Targets":
        root = loadSetTargetsController("SetTargets.fxml");
        break;
      case "Manufacturers":
        root = loadManufacturersView("Manufacturers.fxml");
        break;
      case "LogIn":
        root = loadLogIn("login.fxml");
        break;
      case "OverviewTe":
        root = loadOverviewView("OverviewTe.fxml");
        break;
      case "Home Page 2":
        //root =
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

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("LogIn");
  }
  private Region loadOverviewView(String fxmlFile){
    if(overviewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        overviewController = loader.getController();
        overviewController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root =  overviewController.getRoot();
        overviewController.init(this,root,model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }
  private Region loadSetTargetsController(String fxmlFile){
    if(setTargetsController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        setTargetsController = loader.getController();
        setTargetsController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root =  setTargetsController.getRoot();
        setTargetsController.init(this,root,model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

  private Region loadManufacturersView(String fxmlFile){
    if(manufacturersController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        manufacturersController = loader.getController();
        manufacturersController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root =  manufacturersController.getRoot();
        manufacturersController.init(this,root, model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

  private Region loadLogIn(String fxmlFile){
    if(logInController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        logInController = loader.getController();
        logInController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root =  logInController.getRoot();
        logInController.init(this,root, model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

}
