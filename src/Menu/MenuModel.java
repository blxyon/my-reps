package Menu;

import java.util.ArrayList;

public class MenuModel {

    public ArrayList<String> analyzeString(String str, String wordType)
    {
        ArrayList <String> strs=new ArrayList<String>();

        String tempWord="";
        int noChars=str.length();
        int wordBeg=0;
        boolean readStarted=false;
        for(int i=0;i<noChars;i++)
        {
            Character cInLoop=new Character(str.charAt(i));
            if(isAcc(cInLoop, wordType) && readStarted==false && (int)cInLoop!=32){
                readStarted=true;
                wordBeg=i;
                tempWord=tempWord+cInLoop;

            }
            else if(isAcc(cInLoop, wordType) && readStarted==true)
            {
                tempWord=tempWord+cInLoop;
                if(i==noChars-1){
                    strs.add(tempWord);
                    tempWord="";}
            }
            else if(readStarted==false){}//do nothing
            else{
                readStarted=false;
                if(!tempWord.equals(""))strs.add(tempWord);
                tempWord="";
            }
        }

        return strs;
    }
    public boolean isAcc(char c, String wordType)
    {
        //think about better method for email verification!
        if(wordType.equals("email")){
            if((int)c==43 || (int)c==45 || (int)c==46 || ( (int)c<=57 && (int)c>=48) || ( (int)c<=90 && (int)c>=64)
            || ( (int)c<=122 && (int)c>=97) || (int)c==95)
                return true;
            else return false;}
        else if(wordType.equals("name")){
            if( ((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=172) || (int)c==47 || (int)c==39 || (int)c==46
                    || (int)c==45 || (int)c==58) return true;
            else return false;
        }
        else return false;
    }


}
