 package com.example.antik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {
     //Defining views
     private EditText editTextUsername;
     private EditText editTextPassword;
     private Context context;
     private AppCompatButton buttonLogin;
     private ProgressDialog pDialog;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         context = MainActivity.this;

         //Initializing views
         pDialog = new ProgressDialog(context);
         editTextUsername = (EditText) findViewById(R.id.editTextUsername);
         editTextPassword = (EditText) findViewById(R.id.editTextPassword);

         buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);

         //Adding click listener
         buttonLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 login();
             }
         });
         //editTextEmail.setText("admin@agusharyanto.net");
         //editTextPassword.setText("abcd1234");
     }

     private void login() {
         //Getting values from edit texts
         final String username = editTextUsername.getText().toString().trim();
         final String password = editTextPassword.getText().toString().trim();
         pDialog.setMessage("Login Process...");
         showDialog();
         //Creating a string request
         StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         //If we are getting success from server
                         if (response.contains(AppVar.LOGIN_SUCCESS)) {
                             hideDialog();
                             gotonavigationbar();

                         } else {
                             hideDialog();
                             //Displaying an error message on toast
                             Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                         }
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         //You can handle error here if you want
                         hideDialog();
                         Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();

                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> params = new HashMap<>();
                 //Adding parameters to request
                 params.put(AppVar.KEY_USERNAME, username);
                 params.put(AppVar.KEY_PASSWORD, password);

                 //returning parameter
                 return params;
             }
         };

         //Adding the string request to the queue
         Volley.newRequestQueue(this).add(stringRequest);

     }

     private void gotonavigationbar() {
         Intent intent = new Intent(context, navigationbar.class);
         startActivity(intent);
         finish();
     }

     private void showDialog() {
         if (!pDialog.isShowing())
             pDialog.show();
     }

     private void hideDialog() {
         if (pDialog.isShowing())
             pDialog.dismiss();
     }
}
