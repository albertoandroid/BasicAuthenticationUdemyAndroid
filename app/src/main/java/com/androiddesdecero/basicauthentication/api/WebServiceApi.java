package com.androiddesdecero.basicauthentication.api;

import com.androiddesdecero.basicauthentication.model.ProfesorBA;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by albertopalomarrobledo on 14/12/18.
 */

public interface WebServiceApi {

    @GET("/todos_profesores_public")
    Call<List<ProfesorBA>> listAllPorfessorPublic();

    @GET("/todos_profesores_admin")
    Call<List<ProfesorBA>> listAllProfessorAdmin();

    @GET("/todos_profesores_user")
    Call<List<ProfesorBA>> listAllProfessorUser();

    @GET("/todos_profesores_admin")
    Call<List<ProfesorBA>> listAllProfessorAdmin(@Header("Authorization")String authHeader);
}
