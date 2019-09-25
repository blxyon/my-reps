package Menu;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public class MenuModel {

    public ArrayList<String> analyzeString(String str,String wordType)
    {
        ArrayList<String> strs=new ArrayList<String>();
        boolean wordStarted=false;
        String wordObt="";
        char currentCharL;
        int lengthStr=str.length();
        for(int i=0;i<lengthStr;i++)
        {
            currentCharL=(char) str.charAt(i);
            if(!(currentCharL=='\n' || currentCharL==' ' || currentCharL==','|| currentCharL==';') && !wordStarted)
            {
                //startStr=i;
                wordStarted=true;
                wordObt=wordObt+currentCharL;
                if(i==lengthStr-1){
                    wordStarted=false;
                    System.out.println("we adding:"+wordObt);
                    strs.add(wordObt);
                }
            }
            else if(!(currentCharL=='\n' || currentCharL==' ' || currentCharL==','|| currentCharL==';') && wordStarted)
            {
                wordObt=wordObt+currentCharL;
                if(i==lengthStr-1){
                    wordStarted=false;
                    System.out.println("we adding:"+wordObt);
                    strs.add(wordObt);
                }
            }
            else if(((currentCharL=='\n' || currentCharL==' ' || currentCharL==','|| currentCharL==';') && wordStarted))
            {
                wordStarted=false;
                System.out.println("we adding:"+wordObt+"<-");
                strs.add(wordObt);
                wordObt="";
            }
            else if((currentCharL=='\n' || currentCharL==' ' || currentCharL==','|| currentCharL==';') && !wordStarted) {}
        }
        return strs;
    }
    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    /*
    checkDataProvidedFDB will check the data passed through the TextAreas if its in the right format(for emails)
    and also if they are the same number of items in each TextArea
     */
    public String checkDataProvidedFDB(ArrayList<String> names, ArrayList<String> sNames, ArrayList<String> emails, String typePer)
    {
        String dataFitsDB="Data accepted!";

        if(names.size()==sNames.size() && sNames.size()==emails.size())
        {
            for(int i=0;i<emails.size();i++)
            {
                if(!isValidEmailAddress(emails.get(i))) {
                    dataFitsDB="The email entered: "+ emails.get(i)+" is not the right format!";
                    break;
                }
            }
            //here we will validate all the emails and if they are all ok then just let it append the data to DB..else dispay one of the emails isn t ok
        }
        else
        {
            //if it enters this that means the data introduced isn't the same size in every TextArea

            //then check what isn't the same size... just some if statements comparing the stuff

            if(!(names.size()==sNames.size())) dataFitsDB="the no. of first names is different from the no. of surnames";
            else if(!(names.size()==emails.size())) dataFitsDB=("the no. of emails is different from the no. of first names");
            else if(!(sNames.size()==emails.size())) dataFitsDB=("the no. of emails is different from the no. of surnames");
        }
        if(typePer.equals("N/AS"))dataFitsDB="No selected person type.";
        return dataFitsDB;
    }

}
