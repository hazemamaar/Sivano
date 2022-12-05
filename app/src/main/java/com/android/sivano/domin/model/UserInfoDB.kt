package com.android.sivano.domin.model

import com.google.android.gms.maps.model.LatLng

//import com.google.android.gms.maps.model.LatLng

data class UserInfoDB(
    val email: String = "",
    val token: String = "",
    val latLng: LatLng
)