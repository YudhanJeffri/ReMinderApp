package com.reminder.reminderapp.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reminder.reminderapp.client.ClientService
import com.reminder.reminderapp.client.RetrofitClient
import com.reminder.reminderapp.data.model.mapel.MapelResponse
import com.reminder.reminderapp.helper.RxUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Subscription

class ViewModelMapel : ViewModel() {
    var service: ClientService = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
    var mapelList: MutableLiveData<MapelResponse> = MutableLiveData<MapelResponse>()
    var subscription: Subscription? = null
    val rx = RxUtil()

    fun getMapel() {
        val call = service.mapels()
        call.enqueue(object : Callback<MapelResponse> {
            override fun onResponse(call: Call<MapelResponse>, response: Response<MapelResponse>) {
                mapelList.postValue(response.body())
            }
            override fun onFailure(call: Call<MapelResponse>, t: Throwable) {
                mapelList.postValue(null)
            }
        })
    }
    fun getObserveMapel(): MutableLiveData<MapelResponse>{
        return mapelList
    }
}