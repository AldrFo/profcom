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
import ru.mpei.profcom.main.model.entities.EventDto;
import ru.mpei.profcom.main.model.entities.InfoDto;
import ru.mpei.profcom.main.model.entities.NewsDto;
import ru.mpei.profcom.main.model.entities.OrgDto;

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

    @GET("get_info.php")
    Single<List<InfoDto>> getInfo();

    @GET("get_orgs.php")
    Single<List<OrgDto>> getOrgs();

    @GET("get_user_data.php")
    Single<UserData> getUserData(
        @Query("id") int id
    );

    @FormUrlEncoded
    @POST("add_event.php")
    Single<Response<ResponseBody>> addEvent(
        @Field("id") int id,
        @Field("name") String name,
        @Field("description") String description,
        @Field("link") String link
    );

    @GET("get_events.php")
    Single<List<EventDto>> getEvents(
            @Query("id") int id
    );

}
