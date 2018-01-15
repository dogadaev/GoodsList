package com.quaindinteractive.goodslist.model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.quaindinteractive.goodslist.util.Config.FILE_NAME;

public interface MessagesApi {

    @GET(FILE_NAME)
    Call<XmlList> messages();
}
