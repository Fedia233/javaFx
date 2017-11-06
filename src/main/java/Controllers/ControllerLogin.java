package Controllers;


import DAO.UserDAO;
import Entity.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ControllerLogin {

    @FXML private Label wrongPassLabel;
    @FXML private TextField username_field = new TextField();
    @FXML private PasswordField password_field = new PasswordField();

    private ControllerRegistration controllerRegistration;

    private Stage primaryStage;
    private UserDAO userDAO = new UserDAO();

    /*getter and setter*/
    private Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void pressLogin() {
        UsersEntity user = getCurrentUser(username_field.getText().toString(), password_field.getText().toString());
        if ( user != null) {
            try {
                FXMLLoader loader =  new FXMLLoader(getClass().getResource("/homepage.fxml"));
                Pane root = loader.load();
                ControllerHomePage controllerHomePage = loader.getController();
                controllerHomePage.setPrimaryStage(getPrimaryStage());

                controllerHomePage.setUser(user);
                controllerHomePage.showUser();

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                wrongPassLabel.setText("");
                wrongPassLabel.setStyle("-fx-background-color: white");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            wrongPassLabel.setText("password/email wrong");
            wrongPassLabel.setStyle("-fx-background-color: red");
        }
    }

    @FXML
    private void pressRegistration() {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/registration.fxml"));
            Pane root = loader.load();

            controllerRegistration = loader.getController();
            controllerRegistration.setPrimaryStage(getPrimaryStage());

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private UsersEntity getCurrentUser(String name, String pass) {
        UsersEntity user = null;
        List<UsersEntity> userList;
        userList = userDAO.list();
            if (userList.size() > 0) {
            for (UsersEntity cn : userList) {
                    if (name.equals(cn.getUsername()) && pass.equals(cn.getPassword())) {
                        user = cn;
                    } else {
                        user = null;
                    }
                }
            } else {
                user = null;
            }
            return user;
    }



}
