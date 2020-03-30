package logic;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
      

//Source: https://www.javatpoint.com/example-of-sending-email-using-java-mail-api
public class Mail   {  
     public static void Send(String topic,String recipient){  
          String to = recipient;//change accordingly  
          String from = "legitmedicalcentre@gmail.com";
          String host = "localhost";//or IP address  
      
         //Get the session object  
          final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
          // Get a Properties object
             Properties props = System.getProperties();
             props.setProperty("mail.smtp.host", "smtp.gmail.com");
             props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
             props.setProperty("mail.smtp.socketFactory.fallback", "false");
             props.setProperty("mail.smtp.port", "465");
             props.setProperty("mail.smtp.socketFactory.port", "465");
             props.put("mail.smtp.auth", "true");
             props.put("mail.debug", "true");
             props.put("mail.store.protocol", "pop3");
             props.put("mail.transport.protocol", "smtp");
             try{ 
             Session session = Session.getDefaultInstance(props, 
                     new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                           return new PasswordAuthentication(from, "SuperLegit_987");
                        }});
      
         //compose the message  to send
           
             MimeMessage message = new MimeMessage(session);  
             message.setFrom(new InternetAddress(from));  
             message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
             message.setSubject("APPOINTMENT NOTIFICATION");  
             message.setText(topic);  
      
             // Send message  
             Transport.send(message);  
             System.out.println("message sent successfully....");  
      
          }catch (MessagingException mex) {mex.printStackTrace();}  
       }  
    }  