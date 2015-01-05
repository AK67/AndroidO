package com.example.anubhavkaushik.makemyband;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class login extends Activity {

    ProgressDialog progressDialog; // to show when login validation is in process
    Context context;
    AlertDialog.Builder alertDialogBuilder;
    protected  boolean result_flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("login","login activity created");
        context =this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signIn= (Button)findViewById(R.id.email_sign_in_button);
         alertDialogBuilder = new AlertDialog.Builder(context);
        signIn.setOnClickListener(new signInListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class loginact extends AsyncTask<URL, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=ProgressDialog.show(login.this,"","Please Wait",false);

        }
         @Override
        protected Long doInBackground(URL... urls) {
                  Log.i("listener","in do background");
            try {
                URL servlet;
                Log.i("listener","try called");

                servlet = new URL("http://10.0.2.2:8080/mark/LoginCheckerService");
                URLConnection conn;
                conn = servlet.openConnection();
                conn.setDoOutput(true);
                String data ;
                data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode("username", "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode("password1", "UTF-8");
                data += "&" + URLEncoder.encode("passcode", "UTF-8") + "="
                        + URLEncoder.encode("3.141592653589793", "UTF-8");
                OutputStream output = conn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(output);

                writer.write(data);
                writer.flush();
                output.close();

                BufferedReader br = null ;
                try{  InputStream input = conn.getInputStream();

                    InputStreamReader isr = new InputStreamReader(input);

                     Log.i("InputStreamReader::", String.valueOf(isr.ready()));
                    br = new BufferedReader(isr);
                    String str ;
                    str = br.readLine();
                    Log.i("login","printing value read");
                    Log.d("result frim loginjava",str);
                    str.trim();
                    // if user entered right password then set result_flag=true
                    // else keep it false

                    if(str.contains("successfull"))
                    {   result_flag =true;
                        Log.i("balle balle","run ");
                    }


                }
                catch(Exception e)
                { e.printStackTrace();

                }


                br.close();
                      /* */

            } catch (MalformedURLException ex) {
                Log.i("login","malformed url exception");
                ex.printStackTrace();
            } catch (IOException ex) {
                Log.i("login","Io exception");
                ex.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {

        }
        @Override
        protected void onPostExecute(Long result) {

            progressDialog.dismiss();

            // result_flag which tells whether password entered was correct or not
            if(result_flag)
            {    Log.i("result_flag status","true");
                SharedPreferences prefs = getSharedPreferences("statusInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("loggedIn",true).apply();
                Intent intent = new Intent(login.this,interact.class);
                startActivity(intent);
            }
            else
            {
                alertDialogBuilder.setTitle("OOPS!");
                alertDialogBuilder
                        .setMessage("Wrong password!!")
                        .setCancelable(false)
                        .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder.show();

            }
        }
    }

    public class signInListener implements View.OnClickListener {
        public void onClick(View view) {
            URL a = null;
            Log.i("listener","Onclick called");

            new loginact().execute(a);
        }
        }
    }



