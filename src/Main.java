import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage secondaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LogIn/LogIn.fxml"));
        secondaryStage.setTitle("Log In");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
