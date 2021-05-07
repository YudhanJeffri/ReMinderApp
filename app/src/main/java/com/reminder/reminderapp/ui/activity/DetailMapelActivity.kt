package com.reminder.reminderapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reminder.reminderapp.adapter.DetailListAdapter
import com.reminder.reminderapp.adapter.MapelListAdapter
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelModel
import com.reminder.reminderapp.data.model.mapel.MapelModel
import com.reminder.reminderapp.data.viewmodel.ViewModelDetail
import com.reminder.reminderapp.data.viewmodel.ViewModelMapel
import com.reminder.reminderapp.databinding.ActivityDetailMapelBinding

class DetailMapelActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailMapelBinding
    private lateinit var adapater: DetailListAdapter
    val EXTRA_MAPEL: String? = "extra_mapel"
    lateinit var mapel: MapelModel
    lateinit var viewmodelDetailMapelModel: ViewModelDetail

    lateinit var mapelData:DetailMapelModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMapelBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        showLoading(true)
        viewmodelDetailMapelModel = ViewModelProvider(this).get(ViewModelDetail::class.java)
        adapater = DetailListAdapter(this)

        getDataIntent()
        viewmodelDetailMapelModel.getObserverDetail().observe(this,{items ->
            if (items!=null){
                adapater.setData(items.data)
                showLoading(false)
            } else {
                Toast.makeText(this, "Terjadi kesalahan silahkan refresh", Toast.LENGTH_LONG).show()
                showLoading(false)
            }
        })
        setRV()
        swipeRefreshLoad()
        binding.arrowBack.setOnClickListener{ v1 ->
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }}
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    private fun swipeRefreshLoad() {
        binding.refresh.setOnRefreshListener {
            mapel = intent.getParcelableExtra(EXTRA_MAPEL)!!
            viewmodelDetailMapelModel.getDetail(mapel.id)
            showLoading(true)
            binding.refresh.isRefreshing = true
            setRV()
            binding.refresh.isRefreshing = false
            showLoading(false)
        }
    }
    fun getDataIntent(){
        if(intent.hasExtra(EXTRA_MAPEL)){
            val mapel : MapelModel = intent.getParcelableExtra(EXTRA_MAPEL)!!
            binding.namaGuru.text = mapel.namaGuru
            binding.namaMapel.text = mapel.namaMapel
            viewmodelDetailMapelModel.getDetail(mapel.id)

            Toast.makeText(this, "mapel id : "+mapel.id , Toast.LENGTH_SHORT).show()
        } else {
            Log.d("TAGDetailActivity", "getDataIntent: null")}
    }
    fun setRV(){
        adapater = DetailListAdapter(this)
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