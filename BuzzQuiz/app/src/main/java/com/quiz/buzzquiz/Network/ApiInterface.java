package com.quiz.buzzquiz.Network;

import com.quiz.buzzquiz.Models.Player;
import com.quiz.buzzquiz.Models.Room;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

public interface ApiInterface {
    @GET("/rooms")
    Room.List rooms();

    @POST("/rooms")
    Room postRoom(@Body String name);

    @GET("/rooms")
    Room getRoom(@Query("name")String name);

    @PUT("/rooms")
    void leaveRoom(@Query("name")String name, @Query("credentials")String credentials
                   ,@Query("remove")boolean remove);

    @GET("/players")
    Player.List players();

    @GET("/players")
    Player getPlayer(@Query("name")String name);

    @DELETE("/players")
    void deletePlayer(@Query("credentials")String credentials);

    @POST("/players")
    Player postPlayer(@Body String name);

    @POST("/answer")
    String postAnswer(@Body String credentials,
                      @Body String roomName,
                      @Body int keyId);

    @GET("/answer")
    String getAnswer(@Query("credentials") String credentials,
                      @Query("roomName") String roomName,
                      @Query("keyId") int keyId);
}
