package LogIn;

import javax.mail.*;
import java.util.Properties;

public class ModelLogIn {
    public int checkForErrors(String username, String password) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            Message[] result = inbox.getMessages();

            return 0;
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 2;
        }
    }

}
