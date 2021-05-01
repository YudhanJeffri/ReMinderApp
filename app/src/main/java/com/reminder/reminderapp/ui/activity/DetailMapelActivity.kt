package com.reminder.reminderapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reminder.reminderapp.adapter.DetailListAdapter
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelModel
import com.reminder.reminderapp.data.model.mapel.MapelModel
import com.reminder.reminderapp.databinding.ActivityDetailMapelBinding

class DetailMapelActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailMapelBinding
    private lateinit var adapater: DetailListAdapter
    val EXTRA_MAPEL: String? = "extra_mapel"
    lateinit var mapel: MapelModel
    lateinit var mapelData:DetailMapelModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMapelBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        setRV()
        binding.arrowBack.setOnClickListener{ v1 ->
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }}
    }
    fun getDataIntent(){
        if(intent.hasExtra(EXTRA_MAPEL)){
            val mapel : MapelModel = intent.getParcelableExtra(EXTRA_MAPEL)!!
            mapel.id
        } else {
            Log.d("TAGDetailActivity", "getDataIntent: null")}
    }
    fun setRV(){
        adapater = DetailListAdapter(detailData())
        binding.rvDetailMapel.layoutManager = LinearLayoutManager(this)
        binding.rvDetailMapel.setHasFixedSize(true)
        binding.rvDetailMapel.adapter = adapater
    }
    fun detailData(): ArrayList<DetailMapelModel> {
        val detaillist: ArrayList<DetailMapelModel> = ArrayList<DetailMapelModel>()
        mapelData = DetailMapelModel("Rabu, 17 Februari 2021", "12:34 AM", "Rabu, 1 Apr 21", "08:00 WIB", "tugas dikerjakan di word, dikumpulkan melalui drive, dan juga di kumpulkan di lms terimakasih :)", 1, 1, "Tugas membuat laporan pengujian produk", "1233")
        detaillist.add(mapelData)
        return detaillist
    }
}