package Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;


import java.io.IOException;

public class MenuController {

    String user;
    String pass;

    @FXML
    private TextArea sNamesTextA;
    @FXML
    private TextArea fNamesTextA;
    @FXML
    private TextArea emailsTextA;
    @FXML
    private RadioButton clientRB;
    @FXML
    private RadioButton bbRB;
    @FXML
    public void addPepOAction()
    {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu/MassAdd.fxml"));
            Stage addPep = new Stage();
            addPep.setTitle("Adding");
            addPep.setScene(new Scene(root));
            addPep.show();
            System.out.println();
        }catch(IOException e){System.out.println("No MassAdd.fxml file found!");}
    }
    @FXML
    public void addPepAction()
    {
        MenuModel mM=new MenuModel();
        System.out.println("yes2");
        System.out.println(clientRB.getText()+" " + clientRB.isSelected());
        System.out.println(bbRB.isSelected());
        String fNames=fNamesTextA.getText();
        String sNames=sNamesTextA.getText();
        String emails=emailsTextA.getText();

        System.out.println(mM.analyzeString(fNames,"name")+" with snames:"+mM.analyzeString(sNames,"name")+" and their emails:"+mM.analyzeString(emails,"email"));
    }
    public void setCreds(String user, String pass)
    {
        this.user=user;
        this.pass=pass;
    }
}
