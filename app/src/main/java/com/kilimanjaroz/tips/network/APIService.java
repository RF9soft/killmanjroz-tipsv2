package com.kilimanjaroz.tips.network;


import com.kilimanjaroz.tips.model.NewsNotiResponse;
import com.kilimanjaroz.tips.model.ads.AdsResponse;
import com.kilimanjaroz.tips.model.datewise.DatewiseResponse;
import com.kilimanjaroz.tips.model.history.FreeTipsHistoryResponse;
import com.kilimanjaroz.tips.model.history.VipHistoryResponse;
import com.kilimanjaroz.tips.model.livescore.ApiResponse;
import com.kilimanjaroz.tips.response.ListResponse;
import com.kilimanjaroz.tips.response.category.CategoryListResponse;
import com.kilimanjaroz.tips.response.details.DetailsReponse;
import com.kilimanjaroz.tips.response.free.FreeCatResponse;
import com.kilimanjaroz.tips.response.free.freetips.FreeTipsResponse;
import com.kilimanjaroz.tips.response.oldtips.OldTipsResponse;
import com.kilimanjaroz.tips.response.todaytips.TodayTipsResponse;
import com.kilimanjaroz.tips.response.vip.VIPCategoryResponse;
import com.kilimanjaroz.tips.response.vip.viptips.VIPTipsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("api/ads")
    Call<AdsResponse> getAds();

    @GET("api/notifications")
    Call<NewsNotiResponse> getNotificatio();

    @GET("api/tips")
    Call<ListResponse> getTips(
            );

    @GET("api/category-details/{id}")
    Call<DetailsReponse> getTipsDetails(
            @Path("id")int id
            );

    @GET("api/categories")
    Call<CategoryListResponse> getGameCatResponse();

    @GET("api/today-tips")
    Call<TodayTipsResponse> todayResponse();

    @GET("api/old-tips")
    Call<OldTipsResponse> OldResponse();

    @POST("api/date-wise-tips")
    @FormUrlEncoded
    Call<DatewiseResponse> DateResponse(
            @Field("date") String date
    );

    @GET("api/get-category/Free")
    Call<FreeCatResponse> getGameFreeResponse();

    @GET("api/get-category/VIP")
    Call<VIPCategoryResponse> getGameVipResponse();

    @GET("api/vip-tips")
    Call<VIPTipsResponse> getVipTipsResponse();

    @GET("api/free-tips")
    Call<FreeTipsResponse> getFreeTipsResponse();


    @GET("api/free-tips-histories")
    Call<FreeTipsHistoryResponse> getFreeTipsHistoryResponse();

    @GET("api/vip-tips-histories")
    Call<VipHistoryResponse> getVIPTipsHistoryResponse();

    //livescore rapid api
    @GET("football/live")
    Call<ApiResponse> getLiveMatches(
            @Header("X-RapidAPI-Key") String apiKey,
            @Header("X-RapidAPI-Host") String apiHost
    );

}
