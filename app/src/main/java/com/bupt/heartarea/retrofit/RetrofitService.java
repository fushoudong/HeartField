package com.bupt.heartarea.retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RetrofitService {

    @GET
    Call<String> get(@Url String url);

    @GET
    Call<ResponseBody> get2(@Url String url);

    @GET
    Call<String> getWithHeaders(@HeaderMap Map<String, String> headers, @Url String url);

    @GET
    Call<ResponseBody> getWithHeaders2(@HeaderMap Map<String, String> headers, @Url String url);

    @FormUrlEncoded
    @POST
    Call<String> post(
            @Url String url,
            @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Call<String> post(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @FieldMap Map<String, String> params);


    @Multipart
    @POST
    Call<String> postFile(
            @Url String url,
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> params);

    @Multipart
    @POST
    Call<String> postFile(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> params);

}


