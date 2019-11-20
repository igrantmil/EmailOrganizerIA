package com.example.igran_000.emailorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Loading", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                Intent intent = new Intent(Rules.this, Sort.class);
                String pass = getIntent().getStringExtra("pass");
                String logon = getIntent().getStringExtra("email");
                String sender = ((EditText) findViewById(R.id.editText)).getText().toString();
                String rfold = ((EditText) findViewById(R.id.editText2)).getText().toString();
                intent.putExtra("email", logon);
                intent.putExtra("pass", pass);
                intent.putExtra("rfold", rfold);
                intent.putExtra("sender", sender);
                Rules.this.startService(intent);


            }
        });
/*
         class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

            private final String mEmail;
            private final String mPassword;
             String rfold=(findViewById(R.id.editText2)).toString();
             String sender=(findViewById(R.id.editText)).toString();
            UserLoginTask(String email, String password) {
                mEmail = email;
                mPassword = password;
            }

            @Override
        protected Boolean doInBackground(Void... params) {
            String host = "imap-mail.outlook.com";// change accordingly
            String mailStoreType = "imap";
            String pass = getIntent().getStringExtra("pass");
            String logon = getIntent().getStringExtra("email");

            move(host, mailStoreType, logon, pass, sender, rfold);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                Intent intent = new Intent(getBaseContext(), Rules.class);
                intent.putExtra("email", mEmail);
                intent.putExtra("pass", mPassword);
                startActivity(intent);
                finish();
            }
        }


    }
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

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("imap");

            store.connect(host, user, password);
            Folder emailFolder = store.getFolder("INBOX");
            System.out.println(store.getFolder("finace45").exists());

            emailFolder.open(Folder.READ_WRITE);
            SearchTerm sSender = new FromTerm(new InternetAddress(sender));
            Message[] messages = emailFolder.search(sSender);


                //create the folder object and open it
                Folder emailFolder = store.getFolder("INBOX");
                emailFolder.open(Folder.READ_ONLY);

                // retrieve the messages from the folder in an array and print it
                Message[] messages = emailFolder.getMessages();
                System.out.println("messages.length---" + messages.length);

                for (int i = 0, n = messages.length; i < n; i++) {
                    Message message = messages[i];
                    System.out.println("---------------------------------");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("Text: " + message.getContent().toString());
                 }



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
        */
    }

}
