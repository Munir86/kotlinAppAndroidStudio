package com.pokemonpro.munir

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.coroutines.experimental.buildIterator

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPermission()
    }

    val AccessLcoation=123
    fun checkPermission(){

       if (Build.VERSION.SDK_INT>23){
           if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                   PackageManager.PERMISSION_GRANTED){

               requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),AccessLcoation)
               return
           }
       }
        getUserLocation()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            AccessLcoation->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }else{
                    Toast.makeText(this,"Location access is Deny", Toast.LENGTH_LONG).show()
                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun getUserLocation(){
        Toast.makeText(this,"Location access Now" , Toast.LENGTH_LONG).show()
        //TODO: access user location

        val mylocation=myLocationlestener()
        val locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,mylocation)

        val mythread=MyThread()
        mythread.start()

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }
    var mylocation:Location?=null
    inner class myLocationlestener:LocationListener{
        constructor(){

            mylocation= Location("Me")
            mylocation!!.longitude=0.0
            mylocation!!.latitude=0.0


        }
        override fun onLocationChanged(location: Location?) {
           mylocation=location
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }

    inner class MyThread:Thread{
        constructor():super(){
            //TODO : set old location
        }

        override fun run() {

            while (true){
                try {

                runOnUiThread{
                    mMap!!.clear()
                    // Add a marker in Sydney and move the camera
                    val sydney = LatLng(mylocation!!.latitude, mylocation!!.longitude)
                    mMap!!.addMarker(MarkerOptions()
                            .position(sydney)
                            .title("Mario")
                            .snippet("Here is My location")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                    mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney , 14f))

                }

                    Thread.sleep(1000)
                }catch (ex:Exception){}

            }
        }


    }

}
