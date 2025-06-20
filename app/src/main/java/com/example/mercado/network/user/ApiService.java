package com.example.mercado.network.user;
import com.example.mercado.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class ApiService {
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/")
    Call<ResponseBody> testConnection();



}
