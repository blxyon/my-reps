package Menu;

import java.util.ArrayList;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

public class MenuModel {

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

}
