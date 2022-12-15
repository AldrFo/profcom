package ru.mpei.profcom.network;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.mpei.profcom.entry.model.UserData;
import ru.mpei.profcom.main.model.entities.NewsDto;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Single<Response<ResponseBody>> register(
        @Field("email") String email,
        @Field("password") String password,
        @Field("learn_group") String group,
        @Field("prof_card_number") String cardNumber
    );

    @GET("entry.php")
    Single<Response<UserData>> entry(
        @Query("email") String email,
        @Query("password") String password
    );

    @FormUrlEncoded
    @POST("set_user_type.php")
    Single<Response<ResponseBody>> setUserType(
        @Field("email") String email,
        @Field("type") String type,
        @Field("pb_id") int pbId
    );

    @GET("get_news.php")
    Single<List<NewsDto>> getNews();

}
