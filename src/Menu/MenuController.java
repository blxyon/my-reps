package Menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;


import java.io.IOException;
import java.util.ArrayList;

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
        System.out.println(getTheSelectedType());

        //pop up window
        Stage dialog = new Stage();

        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(sNamesTextA.getScene().getWindow());
        //selecting the window by its hierarchy and blocking untill the "dialog" stage is closed

        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Label("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public void addDataInDB()
    {

    }


    /*
    checkDataProvidedFDB will check the data passed through the TextAreas if its in the right format(for emails)
    and also if they are the same number of items in each TextArea
     */
    public boolean checkDataProvidedFDB(ArrayList<String> names,ArrayList<String> sNames,ArrayList<String> emails)
    {
        boolean dataFitsDB=true;

        if(names.size()==sNames.size() && sNames.size()==emails.size())
        {
            //here we will validate all the emails and if they are all ok then just let it append the data to DB..else dispay one of the emails isn t ok
        }
        else
        {
            //if it enters this that means the data introduced isn't the same size in every TextArea
            dataFitsDB=false;
            //then check what isn't the same size... just some if statements comparing the stuff
        }
        return dataFitsDB;
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
