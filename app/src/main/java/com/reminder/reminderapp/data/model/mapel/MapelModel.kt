package com.reminder.reminderapp.data.model.mapel

import android.os.Parcel
import android.os.Parcelable

data class MapelModel(
    val created_at: String?,
    val id: Int,
    val namaGuru: String?,
    val namaMapel: String?,
    val updated_at: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(created_at)
        parcel.writeInt(id)
        parcel.writeString(namaGuru)
        parcel.writeString(namaMapel)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MapelModel> {
        override fun createFromParcel(parcel: Parcel): MapelModel {
            return MapelModel(parcel)
        }

        override fun newArray(size: Int): Array<MapelModel?> {
            return arrayOfNulls(size)
        }
    }
}