package com.reminder.reminderapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.reminder.reminderapp.adapter.MapelListAdapter
import com.reminder.reminderapp.data.viewmodel.ViewModelMapel
import com.reminder.reminderapp.databinding.FragmentListMapelBinding


class ListMapelFragment : Fragment() {
    lateinit var binding: FragmentListMapelBinding
    lateinit var viewModelMapel: ViewModelMapel
    lateinit var mapelListAdapter : MapelListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListMapelBinding.inflate(inflater, container, false)
        showLoading(true)
        viewModelMapel = ViewModelProvider(this).get(ViewModelMapel::class.java)
        viewModelMapel.getMapel()
        mapelListAdapter = MapelListAdapter(requireContext())
        setRv()
        viewModelMapel.getObserveMapel().observe(viewLifecycleOwner, { items ->
            if (items != null) {
                mapelListAdapter.setData(items.data)
                showLoading(false)
            } else {
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                showLoading(false)
            }
        })
        swipeRefreshLoad()
        binding.arrowBack.setOnClickListener{v1 -> Navigation.findNavController(v1).popBackStack()}
        return binding.root
    }
    private fun swipeRefreshLoad() {
       binding.refresh.setOnRefreshListener {
           viewModelMapel.getMapel()
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
        mapelListAdapter.notifyDataSetChanged()
        binding.rvMapel.layoutManager = LinearLayoutManager(context)
        binding.rvMapel.adapter = mapelListAdapter

    }
}

