package com.android.sivano.ui.fragment.app

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.core.content.res.ResourcesCompat
import com.android.sivano.R
import com.android.sivano.common.helpers.MyLocation
import com.android.sivano.common.uitil.C
import com.android.sivano.common.uitil.RadiusAnimation
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentHomeBinding
import com.android.sivano.databinding.FragmentMapsBinding
import com.android.sivano.domin.model.UserInfoDB
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapsFragment : Fragment(),OnMapReadyCallback  {
 //   private lateinit var mMap: GoogleMap
    private lateinit var _binding: FragmentMapsBinding
    private val binding get() = _binding

    @Inject
    lateinit var complexPreferences:ComplexPreferences
    lateinit var latLong:LatLng
    lateinit var locationResult: MyLocation.LocationResult
    private val myLocation by lazy {   MyLocation()}
//    private val callback = OnMapReadyCallback { googleMap ->
//
//        /**
//         * Manipulates the map once available.
//         * This callback is triggered when the map is ready to be used.
//         * This is where we can add markers or lines, add listeners or move the camera.
//         * In this case, we just add a marker near Sydney, Australia.
//         * If Google Play services is not installed on the device, the user will be prompted to
//         * install it inside the SupportMapFragment. This method will only be triggered once the
//         * user has installed Google Play services and returned to the app.
//         */
//        /**
//         * Manipulates the map once available.
//         * This callback is triggered when the map is ready to be used.
//         * This is where we can add markers or lines, add listeners or move the camera.
//         * In this case, we just add a marker near Sydney, Australia.
//         * If Google Play services is not installed on the device, the user will be prompted to
//         * install it inside the SupportMapFragment. This method will only be triggered once the
//         * user has installed Google Play services and returned to the app.
//         */
//
//        override fun onMapReady(googleMap: GoogleMap) {
//            val sydney = LatLng(-33.852, 151.211)
//            googleMap.addMarker(
//                MarkerOptions()
//                    .position(sydney)
//                    .title("Marker in Sydney")
//            )
//        }
////        var dataUserInfo : UserInfoDB=UserInfoDB("hazemamaar9@gmail.com","skldghdjkbsdgb",latLong)
////
////        // setupStyleForMap(googleMap)
////        setupMarkerRestaurantAndUserLocation(googleMap,dataUserInfo)
//
//}



    private fun setupMarkerRestaurantAndUserLocation(
        googleMap: GoogleMap,
        dataUserInfo: UserInfoDB?
    ) {


       val restaurantLocation = dataUserInfo?.latLng?.latitude?.let { LatLng(it, dataUserInfo.latLng.longitude) }
        val userLocation=dataUserInfo!!.latLng

        val drawable: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.circle_btn, null)

        val bitmap = drawableToBitmap(drawable!!)

        val groundOverlayRestaurant = googleMap.addGroundOverlay(
            GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromBitmap(bitmap!!))
                .position(dataUserInfo.latLng, 200f)
        )

        val groundAnimationRestaurant = RadiusAnimation(groundOverlayRestaurant!!).apply {
            repeatCount = Animation.INFINITE
            repeatMode = Animation.RESTART
            duration = 2000
        }
        val  mapView = binding.root
        mapView.startAnimation(groundAnimationRestaurant) // MapView where i show my map

        googleMap.addMarker(MarkerOptions().position(dataUserInfo.latLng).title("You")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.userlocation)))

        //  addCircle(googleMap,dataUserInfo.latLng)
        googleMap.addMarker(MarkerOptions().position(restaurantLocation!!).title("Sivano")
            .snippet("E_shop")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_marker_map)))
        //  addCircle(googleMap,restaurantLocation)
        val restaurantCameraPosition=buildCameraPosition(restaurantLocation)

//        addPolyLine(googleMap,userLocation,restaurantLocation)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, 17f))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        Log.e("map", "onViewCreated: mapsdone" )
//
//        locationResult = object : MyLocation.LocationResult() {
//            override fun gotLocation(location: Location?) {
//                Log.i("location", "gotLocation: ${location.toString()}")
//                location?.let {
//                    latLong= LatLng(it.latitude,it.longitude)
//                }
//            }
//        }
//        Log.e("maps", "onViewCreated: ${latLong.longitude}", )
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var width = drawable.intrinsicWidth
        width = if (width > 0) width else 1
        var height = drawable.intrinsicHeight
        height = if (height > 0) height else 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
//    private fun addCircle(map: GoogleMap, latLng: LatLng) {
//        val circle: Circle = map.addCircle(
//            CircleOptions()
//                .center(latLng)
//                .radius(400.0)
//                .strokeColor(Color.BLUE)
//                .strokeWidth(5f)
//                .fillColor(R.color.brown_a)
//        )
//    }
//
    private fun buildCameraPosition(latLng: LatLng) =
        CameraPosition.Builder()
            .target(latLng) // Sets the center of the map to Mountain View
            .zoom(13f) // Sets the zoom
            .bearing(90f) // Sets the orientation of the camera to east
            .tilt(30f) // Sets the tilt of the camera to 30 degrees
            .build()

    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

}