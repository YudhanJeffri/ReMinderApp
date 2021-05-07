package com.reminder.reminderapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.reminder.reminderapp.R
import com.reminder.reminderapp.adapter.MapelListAdapter
import com.reminder.reminderapp.adapter.NotifikasiListAdapter
import com.reminder.reminderapp.data.viewmodel.ViewModelMapel
import com.reminder.reminderapp.data.viewmodel.ViewModelNotifikasi
import com.reminder.reminderapp.databinding.FragmentLaporanBinding
import com.reminder.reminderapp.databinding.FragmentNotifikasiBinding
import java.util.zip.Inflater

class NotifikasiFragment : Fragment() {
    lateinit var binding: FragmentNotifikasiBinding
    lateinit var viewmodel: ViewModelNotifikasi
    lateinit var adapter: NotifikasiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        showLoading(true)
        viewmodel = ViewModelProvider(this).get(ViewModelNotifikasi::class.java)
        viewmodel.getAllDetail()
        adapter = NotifikasiListAdapter(requireContext())
        setRv()
        viewmodel.getObserverDetail().observe(viewLifecycleOwner, { items ->
            if (items != null) {
                adapter.setData(items.data)
                showLoading(false)
            } else {
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                showLoading(false)
            }
        })
        swipeRefreshLoad()
        return binding.root

    }
    private fun swipeRefreshLoad() {
        binding.refresh.setOnRefreshListener {
            viewmodel.getAllDetail()
            showLoading(true)
            binding.refresh.isRefreshing = true
            setRv()
            binding.refresh.isRefreshing = false
            showLoading(false)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setRv() {
        adapter.notifyDataSetChanged()
        binding.rvDetal.layoutManager = LinearLayoutManager(context)
        binding.rvDetal.adapter = adapter

    }

}