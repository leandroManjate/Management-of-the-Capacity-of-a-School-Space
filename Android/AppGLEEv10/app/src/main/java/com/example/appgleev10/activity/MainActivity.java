package com.example.appgleev10.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appgleev10.R;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.utlis.DateSerializer;
import com.example.appgleev10.utlis.TimeSerializer;
import com.example.appgleev10.viewmodel.AdminViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;
import java.util.prefs.Preferences;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private EditText edtMail, edtPass;
    private Button btnLogin;
    private AdminViewModel adminViewModel;
    private TextInputLayout txtInputUser, txtInputPass;
    TextView textViewJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViewModel();
        this.init();
    }

    private void initViewModel() {
        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
    }
    private void init(){
        edtMail = findViewById(R.id.edtMail);
        edtPass = findViewById(R.id.edtPassword);
        txtInputUser = findViewById(R.id.txtInputMail);
        txtInputPass = findViewById(R.id.txtInputPassword);
        textViewJoin = findViewById(R.id.tvJoin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {

         try {
             if (validation()) {
                 adminViewModel.login(edtMail.getText().toString(), edtPass.getText().toString()).observe(this, adminGenericResponse -> {
                     if (adminGenericResponse.getResp() == 1) {
                         //Toast.makeText(this, adminGenericResponse.getMessage(), Toast.LENGTH_SHORT).show();
                         toastOk(adminGenericResponse.getMessage());
                         Admin admin = adminGenericResponse.getBody();
                         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                         SharedPreferences.Editor editor = sharedPreferences.edit();
                         final Gson gson = new GsonBuilder()
                                 .registerTypeAdapter(Date.class, new DateSerializer())
                                 .registerTypeAdapter(Time.class, new TimeSerializer())
                                 .create();
                         editor.putString("AdminJson", gson.toJson(admin, new TypeToken<Admin>(){
                         }.getType()));
                         editor.apply();
                         edtMail.setText("");
                         edtPass.setText("");
                         startActivity(new Intent(this, StartActivity.class));

                     }else {
                         // Toast.makeText(this, "Login failed! " + adminGenericResponse.getMessage(), Toast.LENGTH_SHORT).show();
                         toastError("Login Incorrect... " + adminGenericResponse.getMessage());
                     }
                 });
             }else {
                 toastError("Please, complete all fields!");
             }

         }catch (Exception exception) {
             toastError("Login ERROR: " + exception.getMessage());
         }


        });

        textViewJoin.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserRegistrationActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        });

        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtInputUser.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txtInputPass.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void toastOk(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_ok, (ViewGroup) findViewById(R.id.ll_custom_toast_ok));
        TextView txtMessage = view.findViewById(R.id.txtMessageToast1);
        txtMessage.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public void toastError(String msg){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.ll_custom_toast_error));
        TextView txtMessage = view.findViewById(R.id.txtMessageToast2);
        txtMessage.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    private boolean validation() {
        boolean toreturn = true;
        String usermail, password;
        usermail = edtMail.getText().toString();
        password = edtPass.getText().toString();

        if (usermail.isEmpty()) {
            txtInputUser.setError("Put your user mail, please!");
            toreturn = false;
        }else {
            txtInputUser.setErrorEnabled(false);
        }

        if (password.isEmpty()) {
            txtInputPass.setError("Put your password, please!");
            toreturn = false;
        }else {
            txtInputPass.setErrorEnabled(false);
        }

        return  toreturn;
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref = preferences.getString("AdminJson", "");
        if(!pref.equals("")){
            toastOk("Active session detected, the login will be omitted!");
            this.startActivity(new Intent(this, StartActivity.class));
            this.overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }*/

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("You clicked the back button")
                .setContentText("Do you want to close the application?")
                .setCancelText("Cancel").setConfirmText("Yes")
                .showCancelButton(true).setCancelClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Operation canceled")
                            .setContentText("You didn't leave the app...")
                            .show();
                }).setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismissWithAnimation();
                    System.exit(0);
                }).show();
    }
}