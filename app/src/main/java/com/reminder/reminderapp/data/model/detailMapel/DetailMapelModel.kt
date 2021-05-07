package com.reminder.reminderapp.data.model.detailMapel

import android.os.Parcel
import android.os.Parcelable

data class DetailMapelModel(
        var created_at: String?,
        var jam_created: String?,
        var deadline: String?,
        var deadline_jam: String?,
        var deskripsi_tugas: String?,
        var id: Int,
        var id_mapels: Int,
        var judul_tugas: String?,
        var updated_at: String?
)