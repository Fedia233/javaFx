package Controllers;

import DAO.UserDAO;
import Entity.UsersEntity;
import Security.Validation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ControllerRegistration {

    private Validation validation = new Validation();
    private UserDAO userDAO = new UserDAO();
    private Stage primaryStage;
    private UsersEntity user = new UsersEntity();
    private List<UsersEntity> allUsersList;

    @FXML
    private TextField name, user_name, date, password, repeat_password, country, phone, email;

    @FXML
    private Label passLabel, emailLabel, userNameLabel, dateLabel;

    @FXML
    private Button btn_regestration;

    public ControllerRegistration() {}

    @FXML
    private void initialize(){
        btn_regestration.setDisable(true);
    }

    private Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void pressBack() {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
             Pane root = loader.load();

             ControllerLogin controllerLogin = loader.getController();
             controllerLogin.setPrimaryStage(getPrimaryStage());

             Scene scene = new Scene(root);
             Stage primaryStage = getPrimaryStage();
             primaryStage.setScene(scene);
             primaryStage.show();
         } catch(IOException e){
             e.printStackTrace();
         }
    }

    @FXML
    private void pressRegestration() {
        userDAO.add(user);
        pressBack();
    }

    @FXML
    private void nameTF() {
        name.focusedProperty().addListener((observable, oldValue, newValue) ->
            {
                if (!newValue) {
                    if (validation.isNameValid(name.getText().toString())) {
                        name.setBlendMode(BlendMode.MULTIPLY);
                        name.setStyle("-fx-fill: black");
                        user.setName(name.getText().toString());
                    } else {
                        name.setBlendMode(BlendMode.GREEN);
                        name.setStyle("-fx-fill: red");
                    }
                }
            });
    }

    @FXML
    private void userNameTF() {
        user_name.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                userNameLabel.setText("");
                if (validation.isUserNameValid(user_name.getText().toString())) {
                    if (dublicateUserName(user_name.getText().toString())) {
                        user_name.setBlendMode(BlendMode.GREEN);
                        user_name.setStyle("-fx-fill: red");
                    } else {
                        user_name.setBlendMode(BlendMode.MULTIPLY);
                        user_name.setStyle("-fx-fill: black");
                        user.setUsername(user_name.getText().toString());
                    }
                } else {
                    user_name.setBlendMode(BlendMode.GREEN);
                    user_name.setStyle("-fx-fill: red");
                }
            }
        });
    }

    @FXML
    private void passwordTF() {
        password.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                if (validation.isPasswordValid(password.getText().toString())) {
                    password.setBlendMode(BlendMode.MULTIPLY);
                    password.setStyle("-fx-fill: black");
                } else {
                    password.setBlendMode(BlendMode.GREEN);
                    password.setStyle("-fx-fill: red");
                }
            }
        });
    }

    @FXML
    private void repPasswordTF() {
        repeat_password.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                passLabel.isVisible();
                if (password.getText().toString().equals(repeat_password.getText().toString())) {
                    password.setBlendMode(BlendMode.MULTIPLY);
                    password.setStyle("-fx-fill: black");

                    repeat_password.setBlendMode(BlendMode.MULTIPLY);
                    repeat_password.setStyle("-fx-fill: black");

                    if (password.getText().length() > 5) {
                        passLabel.setText("passwords match");
                        passLabel.setStyle("-fx-background-color: white");
                        user.setPassword(password.getText().toString());
                    } else {
                        passLabel.setText("");
                        password.setBlendMode(BlendMode.GREEN);
                        password.setStyle("-fx-fill: red");
                        repeat_password.setBlendMode(BlendMode.GREEN);
                        repeat_password.setStyle("-fx-fill: red");
                    }
                } else {
                    password.setBlendMode(BlendMode.GREEN);
                    password.setStyle("-fx-fill: red");

                    repeat_password.setBlendMode(BlendMode.GREEN);
                    repeat_password.setStyle("-fx-fill: red");
                    passLabel.setStyle("-fx-background-color: red");
                    passLabel.setText("You entered two different passwords. Please try again.");
                }
            }
        });
    }

    @FXML
    private void emailTF() {
        email.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                emailLabel.setText("");
                if (validation.isEmailValid(email.getText().toString())) {
                    if (dublicateEmail(email.getText().toString())) {
                        email.setBlendMode(BlendMode.GREEN);
                        email.setStyle("-fx-fill: red");
                    } else {
                        email.setBlendMode(BlendMode.MULTIPLY);
                        email.setStyle("-fx-fill: black");
                        user.setEmail(email.getText().toString());
                    }
                } else {
                    email.setBlendMode(BlendMode.GREEN);
                    email.setStyle("-fx-fill: red");
                }
            }
        });
    }

    @FXML
    private void dateTF() {
       date.focusedProperty().addListener((observable, oldValue, newValue) ->
       {
           if (!newValue) {
               if (date.getText().length() == 0) {
                   dateLabel.setText("");
                   dateLabel.setStyle("-fx-background-color: white");
                   date.setBlendMode(BlendMode.GREEN);
                   date.setStyle("-fx-fill: red");
               } else {
                   if (validation.isDateValid(date.getText().toString())) {
                       dateLabel.setText("");
                       dateLabel.setStyle("-fx-background-color: white");
                       date.setBlendMode(BlendMode.MULTIPLY);
                       date.setStyle("-fx-fill: black");
                       user.setDate(Integer.parseInt(date.getText().toString()));
                   } else {
                       dateLabel.setText("Error, enter the number !");
                       dateLabel.setStyle("-fx-background-color: red");
                       date.setBlendMode(BlendMode.GREEN);
                       date.setStyle("-fx-fill: red");
                   }
               }
           }
       });
    }

    @FXML
    private void countryTF() {
        country.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                if (validation.isCountryValid(country.getText().toString())) {
                    country.setBlendMode(BlendMode.MULTIPLY);
                    country.setStyle("-fx-fill: black");
                    user.setCountry(country.getText().toString());
                } else {
                    country.setBlendMode(BlendMode.GREEN);
                    country.setStyle("-fx-fill: red");
                }
            }
        });
    }

    @FXML
    private void phoneTF() {
        phone.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue) {
                if (validation.isPhoneValid(phone.getText().toString())) {
                    phone.setBlendMode(BlendMode.MULTIPLY);
                    phone.setStyle("-fx-fill: black");
                    user.setPhone(phone.getText().toString());
                        if (user.getName() == null
                                && user.getUsername() == null
                                && user.getPassword() == null
                                && user.getEmail()    == null
                                && user.getCountry()  == null) {
                        } else {
                            btn_regestration.setDisable(false);
                        }
                } else {
                    date.setBlendMode(BlendMode.GREEN);
                    date.setStyle("-fx-fill: red");
                }
            }
        });
    }

    private boolean dublicateEmail(String email) {
        allUsersList = userDAO.list();
        boolean value = false;

        if (allUsersList.size() > 0) {
            for (UsersEntity cn : allUsersList) {
                if (email.equals(cn.getEmail())) {
                    value = true;
                    emailLabel.setText("Such email already in use");
                } else {
                    value = false;
                }
            }
        } else {
            value = false;
        }
        return value;
    }

    private boolean dublicateUserName(String userName) {
        allUsersList = userDAO.list();
        boolean value = false;

        if (allUsersList.size() > 0) {
            for (UsersEntity cn : allUsersList) {
                if (userName.equals(cn.getUsername())) {
                    value = true;
                    userNameLabel.setText("Such user name already in use");
                } else {
                    value = false;
                }
            }
        }
        return value;
    }
}
