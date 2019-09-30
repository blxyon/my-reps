package LogIn;

import Menu.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    String user;
    @FXML
    private TextField EmailEntry;
    @FXML
    private PasswordField PassEntry;
    @FXML
    private Button LogIn;

    public void processLogIn()
    {
        //System.out.println(EmailEntry.getText()+" "+PassEntry.getText());
        ModelLogIn modelLogIn=new ModelLogIn();
        boolean succesfulLogIn=false;
        user=EmailEntry.getText();
        String pass=PassEntry.getText();
        //System.out.println(emailIn+" "+passIn);
        if(!user.equals("") && !pass.equals("")) {
            int val=modelLogIn.checkForErrors(user,pass);
            if (val == 0) {

                ///close the log in and enter the new window

                Stage stage = (Stage) LogIn.getScene().getWindow();
                stage.close();

                Parent root;
                try {
                    dbCreator dbc=new dbCreator("emailing");

                    int response=dbc.createNewDatabase();

                    if(response==1) {
                        dbc.connect();
                    }
                    if(response!=2){
                        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Menu/Menu.fxml"));
                        root = loader.load();
                        //FXMLLoader.load(getClass().getClassLoader().getResource("Menu/Menu.fxml"));
                        MenuController mC=loader.getController();
                        mC.setCreds(user, pass);
                        mC.setDBC(dbc);

                        Stage menuStage = new Stage();
                        menuStage.setTitle("Menu");
                        menuStage.setScene(new Scene(root));
                        menuStage.show();
                    }
                    else{
                        System.out.println("Pop: error 2");
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();

                }


            }
            else if(val==1)
            {
                System.out.println("name or pass wrong");
            }
            else if(val==2){
                System.out.println("ERR 2");
            }
        }else{
            System.out.println("insert something pls");////////////////////
        }


    }
}
