package com.reminder.reminderapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.reminder.reminderapp.R
import com.reminder.reminderapp.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("NonConstantResourceId")
class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        tanggalNow()
        lms()
        binding.listmapel.setOnClickListener { v1: View -> Navigation.findNavController(v1).navigate(
            R.id.action_nav_home_to_listMapelFragment
        ) }
        return binding.root
    }
    fun lms(){
        val uri: Uri = Uri.parse("http://lms.telkomschools.sch.id/") // missing 'http://' will cause crashed
        val intent = Intent(Intent.ACTION_VIEW, uri)
        binding.lms.setOnClickListener{ startActivity(intent)}
    }
    fun tanggalNow(){
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
        binding.tanggalSekarang.text = formattedDate
    }
}