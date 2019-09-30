package Menu;

import LogIn.dbCreator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;

public class MenuController {



    public void MenuController(dbCreator dbc)
    {
        this.dbc=dbc;
    }
    String user;
    String pass;
    private dbCreator dbc;
    String test;


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
    private ToggleGroup typeGroup;



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
        }catch(IOException e){System.out.println("No MassAdd.fxml file found!");}
    }
    @FXML
    public void addPepAction()
    {

        test="hey";
        System.out.println(getDbc()+"HERE 3"+user+pass);
        MenuModel mM=new MenuModel();

        String fNames=fNamesTextA.getText();
        String sNames=sNamesTextA.getText();
        String emails=emailsTextA.getText();

        ArrayList<String> fNamesL=mM.analyzeString(fNames,"name");
        ArrayList<String>  sNamesL=mM.analyzeString(sNames,"name");
        ArrayList<String>  emailsL=mM.analyzeString(emails,"email");
//        System.out.println(clientRB.getText()+" " + clientRB.isSelected());// prints the radio button selected for client
//        System.out.println(bbRB.isSelected());// prints rb for bb



        String checkerMessage=mM.checkDataProvidedFDB(fNamesL,sNamesL,emailsL,getTheSelectedType());

//////////////////error handling for type selected!!!!!!!!!


        //pop up window
        Stage dialog = new Stage();

        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(sNamesTextA.getScene().getWindow());
        //selecting the window by its hierarchy and blocking untill the "dialog" stage is closed

        VBox dialogVbox = new VBox(20);
        Button btn=new Button();

        dialogVbox.getChildren().add(new Label(checkerMessage));
        dialogVbox.getChildren().add(btn);
        btn.setText("OK");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                if(checkerMessage.equals("Data accepted!")){
                    for(int i=0;i<fNamesL.size();i++)
                    {
                        dbc.insert(fNamesL.get(i),sNamesL.get(i),emailsL.get(i),getTheSelectedType());
                    }

                    ((Stage) sNamesTextA.getScene().getWindow()).close();
                }
            }
        });
        Scene dialogScene = new Scene(dialogVbox, 400, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public void setDBC(dbCreator dbc)
    {
        this.dbc=dbc;
        System.out.println(this.dbc+"this");
    }
    public dbCreator getDbc() {
        return dbc;
    }
    public void addDataInDB()
    {

    }
    public String getTheSelectedType()
    {
        String selectedRB;
        RadioButton rb = (RadioButton)typeGroup.getSelectedToggle();
        if(rb!=null) {
            selectedRB = rb.getText();
        }
        else{
            selectedRB="N/AS";
        }
        return selectedRB;
    }
    public void setCreds(String user, String pass)
    {
        this.user=user;
        this.pass=pass;
    }
}
