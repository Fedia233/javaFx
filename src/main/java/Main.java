import Controllers.ControllerLogin;
import DAO.UserDAO;
import Entity.UsersEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{


    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/login.fxml"));
        Pane root = loader.load();

        ControllerLogin controllerLogin = loader.getController();
        Scene scene = new Scene(root);
        primaryStage = new Stage();

        controllerLogin.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args) throws Exception {
        launch(args);
    }
}