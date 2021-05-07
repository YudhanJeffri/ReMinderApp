package com.reminder.reminderapp.client

import com.reminder.reminderapp.data.model.detailMapel.DetailMapelModel
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelResponse
import com.reminder.reminderapp.data.model.mapel.MapelResponse
import retrofit2.Call
import retrofit2.http.*

interface ClientService {
    @GET("api/mapels")
    fun mapels(): Call<MapelResponse>

    @GET("api/mapeldetails/{id_mapels}")
    fun getdetailMapel(@Path("id_mapels") id: Int): Call<DetailMapelResponse>

    @FormUrlEncoded
    @POST("api/laportugas")
    fun laporTugas(
        @Field("id_mapels") id_mapels:Int,
        @Field("judul_tugas") judul_tugas:String,
        @Field("deskripsi_tugas") deskripsi_tugas:String,
        @Field("deadline") deadline:String
    ) : Call<DetailMapelModel>

    @GET("api/Detailmapels")
    fun getAllDetailmapels(): Call<DetailMapelResponse>
}