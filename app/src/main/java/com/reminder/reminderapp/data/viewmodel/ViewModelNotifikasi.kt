package com.reminder.reminderapp.data.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reminder.reminderapp.client.ClientService
import com.reminder.reminderapp.client.RetrofitClient
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNotifikasi(application: Application) : AndroidViewModel(application){
    var service: ClientService = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
    var mapelDetail: MutableLiveData<DetailMapelResponse> = MutableLiveData<DetailMapelResponse>()
    fun getAllDetail() {
        val call = service.getAllDetailmapels()
        call.enqueue(object : Callback<DetailMapelResponse> {
            override fun onResponse(call: Call<DetailMapelResponse>, response: Response<DetailMapelResponse>) {
                mapelDetail.postValue(response.body())
            }
            override fun onFailure(call: Call<DetailMapelResponse>, t: Throwable) {
                Toast.makeText(getApplication(), "Terjadi kesalahan silahkan ulangi lagi", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun getObserverDetail(): MutableLiveData<DetailMapelResponse> {
        return mapelDetail
    }
}