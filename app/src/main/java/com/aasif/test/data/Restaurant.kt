package com.aasif.test.data

import android.os.Parcel
import android.os.Parcelable

data class Restaurant(
    val address: String,
    val category_id: Int,
    val id: Int,
    val image_url: String,
    val name: String,
    val phone_number: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeInt(category_id)
        parcel.writeInt(id)
        parcel.writeString(image_url)
        parcel.writeString(name)
        parcel.writeString(phone_number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}