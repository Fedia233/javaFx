package Controllers;

import Entity.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class ControllerHomePage {

    private Stage primaryStage;
    private UsersEntity user;

    @FXML
    private Label nameText;

    @FXML
    private void initialize(){
    }

    public ControllerHomePage(){}

    @FXML
    private void pressLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Pane root = loader.load();

            ControllerLogin controllerLogin = loader.getController();
            controllerLogin.setPrimaryStage(getPrimaryStage());
            setUser(null);

            Scene scene = new Scene(root);
            Stage primaryStage = getPrimaryStage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /*getter and setter*/
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public void showUser() {
        nameText.setText(getUser().getName().toString());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
