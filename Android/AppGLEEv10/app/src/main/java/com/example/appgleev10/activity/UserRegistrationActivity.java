package com.example.appgleev10.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgleev10.R;
import com.example.appgleev10.entity.service.Admin;
import com.example.appgleev10.entity.service.DocStored;
import com.example.appgleev10.entity.service.Visitant;
import com.example.appgleev10.viewmodel.AdminViewModel;
import com.example.appgleev10.viewmodel.DocStoredViewModel;
import com.example.appgleev10.viewmodel.VisitantViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.time.LocalDateTime;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserRegistrationActivity extends AppCompatActivity {

    private File file;
    private VisitantViewModel visitantViewModel;
    private AdminViewModel adminViewModel;
    private DocStoredViewModel docStoredViewModel;

    private Button btnUpdateImage, btnSaveData;
    private CircleImageView userImage;
    private AutoCompleteTextView dropdownCity;
    private EditText edtNameUser, edtEmail, edtPhone, edtSchool, edtUserName, edtPassword;
    private TextInputLayout txtInputNameUser, txtInputEmail, txtInputPhone, txtInputSchool, txtInputCity,
            txtInputUserName, txtInputPassword;

    private final static int LOCATION_REQUEST_CODE = 23;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        this.init();
        this.initViewModel();
        this.spinners();
    }

    private void spinners() {
        //LIST OF CITIES
        String[] city = getResources().getStringArray(R.array.city);
        ArrayAdapter arrayCity = new ArrayAdapter(this, R.layout.dropdownitem, city);
        dropdownCity.setAdapter(arrayCity);

        /*dropdownCity.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, city[position], Toast.LENGTH_SHORT).show();
        });*/
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.visitantViewModel = vmp.get(VisitantViewModel.class);
        this.adminViewModel = vmp.get(AdminViewModel.class);
        this.docStoredViewModel = vmp.get(DocStoredViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thanks for granting permissions to " +
                            "read the warehouse, these permissions are necessary to be able to " +
                            "choose your profile picture", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "We cannot carry out the registration if you do not grant us the permissions to read the storage.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        btnSaveData = findViewById(R.id.btnSaveData);
        btnUpdateImage = findViewById(R.id.btnUploadImage);
        userImage = findViewById(R.id.imageUser);
        edtNameUser = findViewById(R.id.edtNameUser);
        edtEmail = findViewById(R.id.edtEmailU);
        edtPhone = findViewById(R.id.edtPhoneU);
        edtSchool = findViewById(R.id.edtSchoolU);
        edtUserName = findViewById(R.id.edtUserMail);
        edtPassword = findViewById(R.id.edtPasswordU);

        //AutoCompleteTextView
        dropdownCity = findViewById(R.id.dropdownCity);

        //TextInputLayout
        txtInputNameUser = findViewById(R.id.txtInputNameU);
        txtInputEmail =    findViewById(R.id.txtInputEmailU);
        txtInputPhone =    findViewById(R.id.txtInputPhoneU);
        txtInputCity =     findViewById(R.id.txtInputCity);
        txtInputSchool =   findViewById(R.id.txtInputSchoolU);
        txtInputUserName = findViewById(R.id.txtInputEmailUser);
        txtInputPassword = findViewById(R.id.txtInputPasswordUser);

        btnUpdateImage.setOnClickListener(v -> {
            this.loadImage();
        });
        btnSaveData.setOnClickListener(v -> {
            this.saveData();
        });

        ///ONCHANGE LISTENEER
        edtNameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputNameUser.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputPhone.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dropdownCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputCity.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtSchool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtInputSchool.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveData() {
        Visitant visitant;
        if (validation()) {
            visitant = new Visitant();
            try {
                visitant.setName(edtNameUser.getText().toString());
                visitant.setEmail(edtEmail.getText().toString());
                visitant.setPhone(edtPhone.getText().toString());
                visitant.setCity(dropdownCity.getText().toString());
                visitant.setSchool(edtSchool.getText().toString());
                visitant.setId(0);

                LocalDateTime ldt = LocalDateTime.now(); //To generate the name of the archive on the basis of closing, hour, year
                RequestBody rb = RequestBody.create(file, MediaType.parse("multipart/form-data")), somedata; //We are sending a file (image) from the form
                String filename = "" + ldt.getDayOfMonth() + (ldt.getMonthValue() + 1) +
                        ldt.getYear() + ldt.getHour()
                        + ldt.getMinute() + ldt.getSecond(); //Assign a name to the archive (image)
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), rb);
                somedata = RequestBody.create("profilePh" + filename, MediaType.parse("text/plain")); //We are sending a name to the archive.
                this.docStoredViewModel.save(part, somedata).observe(this, response -> {

                    if (response.getResp() == 1) {
                        visitant.setPicture(new DocStored());
                        visitant.getPicture().setId(response.getBody().getId());//We assign the photo to the visitant
                        this.visitantViewModel.saveVisitant(visitant).observe(this, vResponse -> {

                            if (vResponse.getResp() == 1) {
                                //If you want can show this message.
                                //Toast.makeText(this, response.getMessage() + ", Now we will proceed to register your credentials.", Toast.LENGTH_SHORT).show();

                                int idV = vResponse.getBody().getId();//Get the visitant id.

                                Admin admin = new Admin();
                                admin.setEmail(edtUserName.getText().toString());
                                admin.setPassword(edtPassword.getText().toString());
                                admin.setIsvalid(true);
                                admin.setVisitant(new Visitant(idV));
                                this.adminViewModel.save(admin).observe(this, uResponse -> {
                                    //Toast.makeText(this, uResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    if (uResponse.getResp() == 1) {
                                        //Toast.makeText(this, "Your data and credentials were created correctly", Toast.LENGTH_SHORT).show();
                                        successMessage("Great! " + "Your information has been saved successfully in the system.");
                                        //this.finish();

                                    } else {
                                        incorrectToast("It has not been possible to keep the data, new attempt. ");
                                    }
                                });
                            } else {
                                incorrectToast("It has not been possible to keep the data, new attempt");
                            }
                        });
                    } else {
                        incorrectToast("It has not been possible to keep the data, new attempt");
                    }
                });
            } catch (Exception e) {
                warningMessage("An error has been produced : " + e.getMessage());
            }
        } else {
            errorMessage("Please complete all fields on the form");
        }
    }

    private void loadImage() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/");
        startActivityForResult(Intent.createChooser(i, "Choose the application"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            final String realPath = getRealPathFromURI(uri);
            this.file = new File(realPath);
            this.userImage.setImageURI(uri);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String result;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private boolean validation() {
        boolean toreturn = true;
        String name, email, phone, dropCity, school, userName, password, schoolU;
        name = edtNameUser.getText().toString();
        email = edtEmail.getText().toString();
        phone = edtPhone.getText().toString();
        dropCity = dropdownCity.getText().toString();
        school = edtSchool.getText().toString();
        userName = edtUserName.getText().toString();
        password = edtPassword.getText().toString();

        if (this.file == null) {
            errorMessage("Select a perfil photo");
            toreturn = false;
        }
        if (name.isEmpty()) {
            txtInputNameUser.setError("Put your name...");
            toreturn = false;
        } else {
            txtInputNameUser.setErrorEnabled(false);
        }
        if (email.isEmpty()) {
            txtInputEmail.setError("Put your email...");
            toreturn = false;
        } else {
            txtInputEmail.setErrorEnabled(false);
        }
        if (phone.isEmpty()) {
            txtInputPhone.setError("Put you phone number...");
            toreturn = false;
        } else {
            txtInputPhone.setErrorEnabled(false);
        }
        if (dropCity.isEmpty()) {
            txtInputCity.setError("Put your city...");
            toreturn = false;
        } else {
            txtInputCity.setErrorEnabled(false);
        }
        if (school.isEmpty()) {
            txtInputSchool.setError("Put your school...");
            toreturn = false;
        } else {
            txtInputSchool.setErrorEnabled(false);
        }
        if (userName.isEmpty()) {
            txtInputUserName.setError("Put your user name...");
            toreturn = false;
        } else {
            txtInputUserName.setErrorEnabled(false);
        }
        if (password.isEmpty()) {
            txtInputPassword.setError("Put your password...");
            toreturn = false;
        } else {
            txtInputPassword.setErrorEnabled(false);
        }

        return toreturn;
    }

    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Well Done!")
                .setContentText(message).show();
    }

    public void errorMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText(message).show();
    }

    public void warningMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.WARNING_TYPE).setTitleText("Notificación del Sistema")
                .setContentText(message).setConfirmText("Ok").show();
    }

    public void incorrectToast(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_toast_error, (ViewGroup) findViewById(R.id.ll_custom_toast_error));
        TextView txtMensaje = view.findViewById(R.id.txtMessageToast2);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

}