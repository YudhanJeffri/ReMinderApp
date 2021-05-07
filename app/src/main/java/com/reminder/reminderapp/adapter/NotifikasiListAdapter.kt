package com.reminder.reminderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reminder.reminderapp.R
import com.reminder.reminderapp.data.model.detailMapel.DetailMapelModel

class NotifikasiListAdapter(val context: Context) : RecyclerView.Adapter<NotifikasiListAdapter.ViewHolderDetail>() {
    private val mData = ArrayList<DetailMapelModel>()
    fun setData(items: List<DetailMapelModel>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    class ViewHolderDetail(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tanggal_laporan_riwayat: TextView
        lateinit var jam_laporan_riwayat: TextView
        lateinit var judul_laporan_riwayat: TextView
        lateinit var tanggal_deadline: TextView
        lateinit var jam_deadline: TextView
        lateinit var deskripsi: TextView
        fun bind(item: DetailMapelModel) {
            with(itemView) {
                tanggal_laporan_riwayat = findViewById(R.id.tanggal_laporan_riwayat)
                jam_laporan_riwayat = findViewById(R.id.jam_laporan_riwayat)
                judul_laporan_riwayat = findViewById(R.id.judul_laporan_riwayat)
                deskripsi = findViewById(R.id.deskripsi_riwayat)
                tanggal_deadline = findViewById(R.id.tanggal_deadline)
                jam_deadline = findViewById(R.id.jam_deadline)

                tanggal_laporan_riwayat.text = item.created_at
                jam_laporan_riwayat.text = item.jam_created
                judul_laporan_riwayat.text = item.judul_tugas
                tanggal_deadline.text = item.deadline
                jam_deadline.text = item.deadline_jam
                deskripsi.text = item.deskripsi_tugas
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDetail {
        val mView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_detail_mapel,
            parent,
            false
        )
        return ViewHolderDetail(mView)
    }

    override fun getItemCount() : Int = mData.size

    override fun onBindViewHolder(holder: ViewHolderDetail, position: Int) {
        holder.bind(mData[position])
    }
}