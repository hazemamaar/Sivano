package com.android.sivano.domin.mapper

import com.android.sivano.domin.model.User
import com.android.sivano.model.UserResponse

fun UserResponse.toUser():User= User(email, id, image, name, phone, token, points, credit)