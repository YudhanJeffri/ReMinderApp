package com.reminder.reminderapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reminder.reminderapp.R
import com.reminder.reminderapp.data.model.mapel.MapelModel
import com.reminder.reminderapp.ui.activity.DetailMapelActivity

@SuppressLint("SetTextI18n", "NonConstantResourceId")
class MapelListAdapter(val context:Context) : RecyclerView.Adapter<MapelListAdapter.ViewHolder>(){
    private val mData = ArrayList<MapelModel>()
    val detailMapelActivity = DetailMapelActivity()
    fun setData(items: List<MapelModel>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var namaMapel: TextView
        lateinit var namaGuru: TextView
        fun bind(item: MapelModel) {
            with(itemView) {
                namaMapel = findViewById(R.id.nama_mapel)
                namaGuru = findViewById(R.id.nama_guru)
                namaMapel.text = item.namaMapel
                namaGuru.text = item.namaGuru
            }
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val mView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_mapel,
                parent,
                false
            )
            return ViewHolder(mView)
        }

        override fun getItemCount() : Int = mData.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(mData[position])
            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailMapelActivity::class.java)
                intent.putExtra(detailMapelActivity.EXTRA_MAPEL, mData[holder.adapterPosition])
                context.startActivity(intent)
            }
        }

}