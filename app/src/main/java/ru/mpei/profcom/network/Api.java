package ru.mpei.profcom.network;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.mpei.profcom.entry.model.UserData;
import ru.mpei.profcom.main.model.entities.EventDto;
import ru.mpei.profcom.main.model.entities.GratitudeDto;
import ru.mpei.profcom.main.model.entities.InfoDto;
import ru.mpei.profcom.main.model.entities.LearnDto;
import ru.mpei.profcom.main.model.entities.NewsDto;
import ru.mpei.profcom.main.model.entities.OrgDto;
import ru.mpei.profcom.main.model.entities.TaskDto;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Completable register(
        @Field("email") String email,
        @Field("password") String password,
        @Field("learn_group") String group,
        @Field("prof_card_number") String cardNumber
    );

    @GET("entry.php")
    Single<UserData> entry(
        @Query("email") String email,
        @Query("password") String password
    );

    @FormUrlEncoded
    @POST("set_user_type.php")
    Single<Boolean> setUserType(
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

    @GET("get_events.php")
    Single<List<EventDto>> getEvents(
        @Query("id") int id
    );

    @FormUrlEncoded
    @POST("add_active_request.php")
    Completable addActiveRequest(
        @Field("id") int id,
        @Field("description") String description
    );

    @GET("get_learnings.php")
    Single<List<LearnDto>> getLearnings();

    @GET("get_tasks.php")
    Single<List<TaskDto>> getTasks(
        @Query("id") int id
    );

    @FormUrlEncoded
    @POST("complete_task.php")
    Completable completeTask(
        @Field("id") int id
    );

    Completable addEventToGoing(int userId, int id);

    Single<List<GratitudeDto>> getGratitudes(int userId);

    Single<List<String>> getAvailablePkTime();

    Completable sendPkRequest(int userId, String time, String description, String vkLink);
}
