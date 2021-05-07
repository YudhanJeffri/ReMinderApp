package com.reminder.reminderapp.ui.fragment

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.reminder.reminderapp.client.ClientService
import com.reminder.reminderapp.client.RetrofitClient
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelModel
import com.reminder.reminderapp.data.model.mapel.MapelModel
import com.reminder.reminderapp.data.model.mapel.MapelResponse
import com.reminder.reminderapp.databinding.FragmentLaporanBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class LaporanFragment : Fragment() {
    lateinit var binding: FragmentLaporanBinding
    var id_mapels:Int = 0
    var service: ClientService = RetrofitClient.getRetrofitInstance().create(ClientService::class.java)
    var tanggalDeadline = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaporanBinding.inflate(inflater, container, false)
        initSpinnerMapel()
        setSpinner()
        binding.btnLapor.setOnClickListener{
            if (binding.editJudul.text.toString() == "" || binding.editDeskripsi.text.toString() == ""){
                Toast.makeText(context, "Harap isi Semua Form", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.tanggalDeadline.text.toString() == "Tanggal"){
                    laporTugas(id_mapels,binding.editJudul.text.toString(),binding.editDeskripsi.text.toString(),"Tidak Ada")
                } else {
                    laporTugas(id_mapels,binding.editJudul.text.toString(),binding.editDeskripsi.text.toString(),binding.tanggalDeadline.text.toString())
                }
            }
        }
        binding.arrowBack.setOnClickListener{v1->Navigation.findNavController(v1).popBackStack()}
        binding.consDeadline.setOnClickListener { setDate() }
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    fun setDate(){
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(),
                OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->binding.tanggalDeadline.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }
    fun setSpinner(){
        binding.spMapel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedName = parent.getItemAtPosition(position).toString()
                id_mapels = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    fun laporTugas(id_mapels: Int, judul_tugas: String, deskripsi_tugas: String, deadline: String){
            val call = service.laporTugas(id_mapels, judul_tugas, deskripsi_tugas, deadline)
            call.enqueue(object : Callback<DetailMapelModel> {
                override fun onResponse(call: Call<DetailMapelModel>, response: Response<DetailMapelModel>) {
                    Toast.makeText(context, "Laporan Berhasil Terkirim", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(binding.view).popBackStack()
                }

                override fun onFailure(call: Call<DetailMapelModel>, t: Throwable) {
                    Toast.makeText(context, "Laporan gagal di kirim", Toast.LENGTH_SHORT).show()
                }
            })
    }
    private fun initSpinnerMapel() {
        service.mapels().enqueue(object : Callback<MapelResponse> {
            override fun onResponse(call: Call<MapelResponse>, response: Response<MapelResponse>) {
                if (response.isSuccessful) {
                    val semuadosenItems: List<MapelModel> = response.body()!!.data
                    val listSpinner: MutableList<String> = ArrayList()
                    for (i in semuadosenItems.indices) {
                        listSpinner.add(semuadosenItems[i].namaMapel.toString())
                    }
                    val adapter = context?.let {
                        ArrayAdapter(it,
                                R.layout.simple_spinner_item, listSpinner)
                    }
                    adapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                    binding.spMapel.adapter = adapter
                } else {
                    Toast.makeText(context, "Gagal mengambil data mapel", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MapelResponse>, t: Throwable) {
                Toast.makeText(context, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show()
            }
        })
    }
}