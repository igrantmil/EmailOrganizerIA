package com.example.igran_000.emailorganizer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import static javax.mail.Folder.HOLDS_MESSAGES;

public class Sort extends Service {
    private String pass;
    private String logon;
    private String sender;
    private String rfold;
    private String host;
    private String mailStoreType;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        host = "imap-mail.outlook.com";// change accordingly
        mailStoreType = "imap";
        pass = intent.getStringExtra("pass");
        logon = intent.getStringExtra("email");
        sender = intent.getStringExtra("sender");
        rfold = intent.getStringExtra("rfold");

        Thread t = new Thread() {


            public void run() {


                move(host, mailStoreType, logon, pass, sender, rfold);


            }
        };
        t.start();
        return flags;
    }

    public void move(String host, String storeType, String user,
                     String password, String sender, String rfold) {

        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");
            properties.put("mail.imap.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            //create the IMAP store object and connect with the imap server
            Store store = emailSession.getStore(storeType);
            store.connect(host, user, password);

            Folder dest = store.getFolder(rfold);
            dest.open(Folder.READ_WRITE);
            if (dest == null) {
                return;
            }

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);
            SearchTerm sSender = new FromTerm(new InternetAddress(sender));
            Message[] messages = emailFolder.search(sSender);


            if (dest.exists()) {
                dest.appendMessages(messages);
            } else {
                dest.create(HOLDS_MESSAGES);
                dest.open(Folder.READ_WRITE);
                dest.appendMessages(messages);
            }
            dest.close(true);
            emailFolder.setFlags(messages, new Flags(Flags.Flag.DELETED), true);
            emailFolder.close(true);


            //close the store and folder objects


            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*



 public void move(String host, String storeType, String user,
                 String password, String sender, String rfold) {


             try {

             //create properties field
             Properties properties = new Properties();

             properties.put("mail.imap.host", host);
             properties.put("mail.imap.port", "993");
             properties.put("mail.imap.ssl.enable", "true");
             properties.put("mail.imap.starttls.enable", "true");

             Session emailSession = Session.getDefaultInstance(properties);

             //create the IMap store object and connect with the imap server
             Store store = emailSession.getStore("imap");

             store.connect(host, user, password);
             Folder emailFolder = store.getFolder("INBOX");
             System.out.println(store.getFolder("finance").exists());

             emailFolder.open(Folder.READ_WRITE);
             SearchTerm sSender = new FromTerm(new InternetAddress(sender));
             Message[] messages = emailFolder.search(sSender);


             //close the store and folder objects
             emailFolder.close(false);

             store.close();

         } catch (NoSuchProviderException e) {
             e.printStackTrace();
         } catch (MessagingException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
     } */
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");

    }


}








