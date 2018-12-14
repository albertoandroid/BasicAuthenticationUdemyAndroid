package com.androiddesdecero.basicauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.basicauthentication.api.WebServiceApi;
import com.androiddesdecero.basicauthentication.api.WebServiceBA;
import com.androiddesdecero.basicauthentication.model.ProfesorBA;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MessageDigest md;
    private Button btPublico;
    private Button btUser;
    private Button btAdmin;
    private Button btAdminWithHeader;
    private Button btUserWithHeaderandHash;
    private Button btUserWithHeaderandHashUserPassword;
    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
    }

    private void setUpView(){
        btPublico = findViewById(R.id.btPublico);
        btPublico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudSinAutenticar();
            }
        });

        btAdmin = findViewById(R.id.btAdmin);
        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudAutenticadoAdmin();
            }
        });

        btUser = findViewById(R.id.btUser);
        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudAutenicadoUser();
            }
        });

        btAdminWithHeader = findViewById(R.id.btAdminWithHeader);
        btAdminWithHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudAutenticationAdminWithHeader();
            }
        });
        btUserWithHeaderandHash = findViewById(R.id.btUserWithHeaderandHash);
        btUserWithHeaderandHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitudAutenticadoUserHash();
            }
        });
    }

    private void solicitudAutenticadoUserHash(){

        String passwordHash = encrypPassword("user");
        Log.d("TAG1", passwordHash);
        String userPassord = "user:" + passwordHash;
        String authHeader = "Basic " + Base64.encodeToString((userPassord).getBytes(), Base64.NO_WRAP);

        Call<List<ProfesorBA>> call = WebServiceBA
                .getInstance()
                .createService(WebServiceApi.class)
                .listAllProfessorUser(authHeader);

        call.enqueue(new Callback<List<ProfesorBA>>() {
            @Override
            public void onResponse(Call<List<ProfesorBA>> call, Response<List<ProfesorBA>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Profesor: " + response.body().get(i).getName()
                                + " Salario: " + response.body().get(i).getSalary()
                                + " ID: " + response.body().get(i).getId()
                        );
                    }
                }else if(response.code()==204){
                    Log.d("TAG1", "No hay profesores");
                }else if(response.code()==403){
                    Log.d("TAG1", "Forbidden: No tienes permiso para el recurso");
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado: No existe usuario");
                }
            }

            @Override
            public void onFailure(Call<List<ProfesorBA>> call, Throwable t) {

            }
        });
    }

    private void solicitudAutenticationAdminWithHeader(){
        String authHeader = "Basic " + Base64.encodeToString(("alberto:alberto").getBytes(), Base64.NO_WRAP);

        Call<List<ProfesorBA>> call = WebServiceBA
                .getInstance()
                .createService(WebServiceApi.class)
                .listAllProfessorAdmin(authHeader);

        call.enqueue(new Callback<List<ProfesorBA>>() {
            @Override
            public void onResponse(Call<List<ProfesorBA>> call, Response<List<ProfesorBA>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Profesor: " + response.body().get(i).getName()
                                + " Salario: " + response.body().get(i).getSalary()
                                + " ID: " + response.body().get(i).getId()
                        );
                    }
                }else if(response.code()==204){
                    Log.d("TAG1", "No hay profesores");
                }else if(response.code()==403){
                    Log.d("TAG1", "Forbidden: No tienes permiso para el recurso");
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado: No existe usuario");
                }
            }

            @Override
            public void onFailure(Call<List<ProfesorBA>> call, Throwable t) {

            }
        });
    }

    private void solicitudAutenicadoUser(){
        Call<List<ProfesorBA>> call = WebServiceBA
                .getInstance()
                .createService(WebServiceApi.class)
                .listAllProfessorUser();

        call.enqueue(new Callback<List<ProfesorBA>>() {
            @Override
            public void onResponse(Call<List<ProfesorBA>> call, Response<List<ProfesorBA>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Profesor: " + response.body().get(i).getName()
                                + " Salario: " + response.body().get(i).getSalary()
                                + " ID: " + response.body().get(i).getId()
                        );
                    }
                }else if(response.code()==204){
                    Log.d("TAG1", "No hay profesores");
                }else if(response.code()==403){
                    Log.d("TAG1", "Forbidden: No tienes permiso para el recurso");
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado: No existe usuario");
                }
            }

            @Override
            public void onFailure(Call<List<ProfesorBA>> call, Throwable t) {

            }
        });
    }

    private void solicitudAutenticadoAdmin(){
        Call<List<ProfesorBA>> call = WebServiceBA
                .getInstance()
                .createService(WebServiceApi.class)
                .listAllProfessorAdmin();

        call.enqueue(new Callback<List<ProfesorBA>>() {
            @Override
            public void onResponse(Call<List<ProfesorBA>> call, Response<List<ProfesorBA>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Profesor: " + response.body().get(i).getName()
                                + " Salario: " + response.body().get(i).getSalary()
                                + " ID: " + response.body().get(i).getId()
                        );
                    }
                }else if(response.code()==204){
                    Log.d("TAG1", "No hay profesores");
                }else if(response.code()==403){
                    Log.d("TAG1", "Forbidden: No tienes permiso para el recurso");
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado: No existe usuario");
                }
            }

            @Override
            public void onFailure(Call<List<ProfesorBA>> call, Throwable t) {

            }
        });
    }

    private void solicitudSinAutenticar(){
        Call<List<ProfesorBA>> call = WebServiceBA
                .getInstance()
                .createService(WebServiceApi.class)
                .listAllPorfessorPublic();

        call.enqueue(new Callback<List<ProfesorBA>>() {
            @Override
            public void onResponse(Call<List<ProfesorBA>> call, Response<List<ProfesorBA>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Profesor: " + response.body().get(i).getName()
                                + " Salario: " + response.body().get(i).getSalary()
                                + " ID: " + response.body().get(i).getId()
                        );
                    }
                }else if(response.code()==204){
                    Log.d("TAG1", "No hay profesores");
                }
            }

            @Override
            public void onFailure(Call<List<ProfesorBA>> call, Throwable t) {

            }
        });
    }

    private String encrypPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, messageDigest);
            String hashPassword = bigInteger.toString(16);
            while (hashPassword.length()<32){
                hashPassword = "0" + hashPassword;
            }
            return hashPassword;

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
