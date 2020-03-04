package com.mobquid.iitdo.retrofit;

import com.mobquid.iitdo.models.AwardsResponse;
import com.mobquid.iitdo.models.BoardMembersResponse;
import com.mobquid.iitdo.models.EventResponse;
import com.mobquid.iitdo.models.FdiResponse;
import com.mobquid.iitdo.models.GalleryResponse;
import com.mobquid.iitdo.models.IOResponse;
import com.mobquid.iitdo.models.ImagesResponse;
import com.mobquid.iitdo.models.KeySectionsResponse;
import com.mobquid.iitdo.models.NewsResponse;
import com.mobquid.iitdo.models.SliderImagesResponse;
import com.mobquid.iitdo.models.StaticDataResponse;
import com.mobquid.iitdo.models.SuccessResponse;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/get_board_members.php")
    void getBoardMembers(
            @Field("sector") String sector,
            Callback<BoardMembersResponse> callback);

    @FormUrlEncoded
    @POST("/get_events.php")
    void getEvents(
            @Field("event_type") String event_type,
            Callback<EventResponse> callback);

    @GET("/get_gallery.php")
    void getGallery(
            Callback<GalleryResponse> callback);

    @FormUrlEncoded
    @POST("/get_images.php")
    void getImages(
            @Field("gallery_name") String gallery_name,
            Callback<ImagesResponse> callback);

    @GET("/get_key_sections.php")
    void getKeySections(
            Callback<KeySectionsResponse> callback);

    @GET("/get_slider_images.php")
    void getSliderImages(
            Callback<SliderImagesResponse> callback);

    @GET("/get_key_people.php")
    void getKeyPeople(
            Callback<BoardMembersResponse> callback);

    @GET("/get_fdi.php")
    void getFDI(
            Callback<FdiResponse> callback);

    @FormUrlEncoded
    @POST("/get_io.php")
    void getIO(
            @Field("type") String type,
            Callback<IOResponse> callback);

    @GET("/get_awards.php")
    void getAwards(
            Callback<AwardsResponse> callback);

    @GET("/get_news.php")
    void getNews(
            Callback<NewsResponse> callback);

    @FormUrlEncoded
    @POST("/get_static_data.php")
    void getStaticData(
            @Field("name") String name,
            Callback<StaticDataResponse> callback);

    @FormUrlEncoded
    @POST("/save_contact.php")
    void saveContact(
            @Field("name") String name,
            @Field("email") String email,
            @Field("contact") String contact,
            @Field("message") String message,
            @Field("source") String source,
            Callback<SuccessResponse> callback);

    @FormUrlEncoded
    @POST("/save_newsletter.php")
    void saveNewsletter(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("business") String business,
            @Field("source") String source,
            Callback<SuccessResponse> callback);

    @FormUrlEncoded
    @POST("/save_new_member.php")
    void saveNewMember(
            @Field("name") String name,
            @Field("designation") String designation,
            @Field("address") String address,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("business") String business,
            @Field("country") String country,
            @Field("category") String category,
            Callback<SuccessResponse> callback);

}
