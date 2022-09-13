package com.android.sivano.common.uitil

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build


object UIConnection {
    fun checkInternetConnection(context: Context, newtWorkAvailable: (Boolean) -> Unit) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {

            val networkCallback: NetworkCallback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    // network available
                    newtWorkAvailable(true)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    newtWorkAvailable(false)
                }


                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    newtWorkAvailable(false)
                }

                override fun onLost(network: Network) {
                    // network unavailable
                    newtWorkAvailable(false)
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(networkCallback);
            } else {
                val request = NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
                connectivityManager.registerNetworkCallback(request, networkCallback)
            }
        }
    }
}