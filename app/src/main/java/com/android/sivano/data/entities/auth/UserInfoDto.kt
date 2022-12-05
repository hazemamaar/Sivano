package com.android.sivano.data.entities.auth

//import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    val image: String,
    val name: String,
    val phone: String,


)