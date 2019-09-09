package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class WordsAnalyser {

    public List<String> emailsMatching(String emails)
    {
        List<String> emailsFound=new ArrayList<String>();
        Matcher m = Pattern.compile("()").matcher(emails);
        //Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
        while(m.find()) {
            emailsFound.add(m.group(1));
        }
        return emailsFound;

    }
    public List<String> namesMatching(String names)
    {
        List<String> namesFound=new ArrayList<String>();

        return namesFound;

    }
}
