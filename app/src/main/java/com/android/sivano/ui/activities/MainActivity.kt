package com.android.sivano.ui.activities

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.sivano.R
import com.android.sivano.common.uitil.C.token
import com.android.sivano.common.uitil.UIConnection.checkInternetConnection
import com.android.sivano.ui.viewmodel.AuthViewModel
import com.google.android.gms.tasks.*
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MyAPPLICATION"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel: AuthViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkInternetConnection(this@MainActivity) {
            Log.i(TAG, "onCreate: newtWorkAvailable $it")
        }

    }
}