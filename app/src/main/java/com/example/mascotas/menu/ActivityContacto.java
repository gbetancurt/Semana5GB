package com.example.mascotas.menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mascotas.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ActivityContacto extends AppCompatActivity {
    EditText etnombre,etemail,etmensaje;
    Button btenviarcomentario;
    private static final String EMAIL = "dummyMail ";
    private static final String PASSWORD = "dummyPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        setOnClick();

        etnombre = findViewById(R.id.textImNom);
        etmensaje = findViewById(R.id.textImMensaje);
        etemail = findViewById(R.id.textImEmail);
        btenviarcomentario = findViewById(R.id.buttonenviarcomentario);

        btenviarcomentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    private void sendEmail(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        final Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(EMAIL));
            message.setSubject("Contacto de: " + etnombre.getText().toString().trim());
            message.setText("Nombre: " + etnombre.getText().toString().trim() + "\nEmail: " + etemail.getText().toString().trim() + "\nMensaje: " + etmensaje.getText().toString().trim());

            new JavaMailAPI().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

    private void setOnClick(){
        final ImageButton botonatras = (ImageButton) findViewById(R.id.botonatras);
        botonatras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class JavaMailAPI extends AsyncTask<Message,String,String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ActivityContacto.this, "Please wait", "Sending mail...", true,false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Success")){
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContacto.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("Success"));
                builder.setMessage("Mail send");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }else{
                Toast.makeText(ActivityContacto.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "Error";
        }
    }
}

