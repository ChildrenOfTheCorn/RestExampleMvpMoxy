package com.example.restexample.data;

import com.example.restexample.data.RestModels.AuthResponse;
import com.example.restexample.data.RestModels.RestResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by grishberg on 04.11.16.
 */

public interface ApiService {
    @POST(RestConst.Methods.AUTHORIZATION)
        //Call<RestResponse<AuthResponse>> authorization(@Query(RestConst.Fields.LOGIN) String login,
        //                                               @Query(RestConst.Fields.PASSWORD) String pass);

    Observable<RestResponse<AuthResponse>> authorization(@Query(RestConst.Fields.LOGIN) String login,
                                                         @Query(RestConst.Fields.PASSWORD) String pass);
}
