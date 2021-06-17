package com.example.weatherapplication.Activities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.app.Service
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.Adapter.DaysDataAdapter
import com.example.weatherapplication.Adapter.HoursDataAdapter
import com.example.weatherapplication.DaysDataResp
import com.example.weatherapplication.HoursDataResp
import com.example.weatherapplication.R
import com.example.weatherapplication.WeatherInterface
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var hoursDataAdapter: HoursDataAdapter
    private lateinit var daysDataAdapter: DaysDataAdapter
    var API_KEY:String ?= null
    lateinit var tabLayout: TabLayout
    var connectivity : ConnectivityManager? = null
    var info : NetworkInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        API_KEY="a0e178e53c6e4077834d7d4b21b6311b"
        tabLayout = findViewById(R.id.tabLayout)

        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null)
        {
            info = connectivity!!.activeNetworkInfo

            if (info != null)
            {
                if (info!!.state == NetworkInfo.State.CONNECTED)
                {
                    getWeatherHoursDataActivity(
                        "riodejaneiro",
                        API_KEY!!,
                        12
                    )
                    getWeatherDaysDataActivity(
                        "Friedrichshafen",
                        5,
                        API_KEY!!
                    )

                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL

                    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab) {

                            when (tab.position) {
                                0 -> {
                                    getWeatherHoursDataActivity(
                                        "riodejaneiro",
                                        API_KEY!!,
                                        12
                                    )
                                    getWeatherDaysDataActivity(
                                        "riodejaneiro",
                                        5,
                                        API_KEY!!
                                    )
                                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL
                                }
                                1 -> {
                                    getWeatherHoursDataActivity(
                                        "beijing",
                                        API_KEY!!,
                                        12
                                    )
                                    getWeatherDaysDataActivity(
                                        "beijing",
                                        5,
                                        API_KEY!!
                                    )
                                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL

                                }
                                2 -> {
                                    getWeatherHoursDataActivity(
                                        "losangeles",
                                        API_KEY!!,
                                        12
                                    )
                                    getWeatherDaysDataActivity(
                                        "losangeles",
                                        5,
                                        API_KEY!!
                                    )
                                    tabLayout.tabGravity = TabLayout.GRAVITY_FILL

                                }

                            }


                        }

                        override fun onTabUnselected(tab: TabLayout.Tab) {

                        }

                        override fun onTabReselected(tab: TabLayout.Tab) {

                        }

                    })

                }

            }
            else
            {
                showAlertDialog()
                // Toast.makeText(this, "NOT CONNECTED", Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun getWeatherHoursDataActivity(strCity: String, strKey: String, strHours: Int) {
        val mProgressDialog = ProgressDialog(this)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()
        var rf = Retrofit.Builder()
            .baseUrl(WeatherInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(WeatherInterface::class.java)
        var call = API.getHoursData(strCity, strKey, strHours)

        call?.enqueue(object : Callback<HoursDataResp?> {
            override fun onFailure(call: Call<HoursDataResp?>, t: Throwable) {
                mProgressDialog.dismiss();
            }

            override fun onResponse(
                call: Call<HoursDataResp?>,
                response: Response<HoursDataResp?>
            ) {
                mProgressDialog.dismiss();
                var postlist: String? = response.body()?.country_code
                Log.d("Response", "-->" + postlist)

                val recyclerViewHours: RecyclerView = findViewById(R.id.recyclerViewHours)
                hoursDataAdapter =
                    HoursDataAdapter(response.body()?.data!!, applicationContext)
                val layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewHours.layoutManager = layoutManager
                recyclerViewHours.adapter = hoursDataAdapter


            }

        })
    }
    private fun getWeatherDaysDataActivity(strCity: String, strDays: Int, strKey: String) {
        var rf = Retrofit.Builder()
            .baseUrl(WeatherInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(WeatherInterface::class.java)
        var call = API.getDaysData(strCity, strDays, strKey)

        call?.enqueue(object : Callback<DaysDataResp?> {
            override fun onFailure(call: Call<DaysDataResp?>, t: Throwable) {

            }


            override fun onResponse(
                call: Call<DaysDataResp?>,
                response: Response<DaysDataResp?>
            ) {

                var postlist: String? = response.body()?.country_code
                Log.d("Response", "-->" + postlist)


                val recyclerViewDays: RecyclerView = findViewById(R.id.recyclerViewDays)
                daysDataAdapter =
                    DaysDataAdapter(response.body()?.data!!, applicationContext)
                val layoutManagerDays =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                recyclerViewDays.layoutManager = layoutManagerDays
                recyclerViewDays.adapter = daysDataAdapter

            }

        })
    }
    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Login App")
        alertDialog.setMessage("Internet Unavailable, Please check your connection")
        alertDialog.setPositiveButton(
            "Ok"
        ) { _, _ ->
            //Toast.makeText(this@MainActivity, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }


}
