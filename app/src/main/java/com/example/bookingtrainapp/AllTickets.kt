package com.example.bookingtrainapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingtrainapp.adapters.AllTicketAdapters
import com.example.bookingtrainapp.databinding.ActivityAllTicketsBinding
import com.example.bookingtrainapp.models.Ticket
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

private lateinit var binding: ActivityAllTicketsBinding
val mTicketDetails =  ArrayList<Ticket>()

class AllTickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tickets)

        binding = ActivityAllTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mRecyclerView = binding.theTickets
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        MyAsyncTask(applicationContext).execute()

    }
    class MyAsyncTask internal constructor(context: Context) : AsyncTask<String, String, String>() {
        lateinit var con: HttpURLConnection
        lateinit var resulta:String
        val builder = Uri.Builder()
        val mRecyclerView = binding.theTickets
        private val cont: Context =context
        override fun onPreExecute() {
            super.onPreExecute()
            

            progressBar.isIndeterminate=true
            progressBar.visibility= View.VISIBLE

        }

        override fun doInBackground(vararg params: String?):  String? {
            try {

                var query = builder.build().encodedQuery
                val url: String = "https://bulgarian-depots.000webhostapp.com/sgrapp/alltickets.php"
                val obj = java.net.URL(url)
                con = obj.openConnection() as HttpURLConnection
                con.setRequestMethod("GET")
                con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)")
                con.setRequestProperty("Accept-Language", "UTF-8")
                con.setDoOutput(true)
                val outputStreamWriter = OutputStreamWriter(con.getOutputStream())
                outputStreamWriter.write(query)
                outputStreamWriter.flush()
                Log.e("pass 1", "connection success ")
            } catch (e: Exception) {
                Log.e("Fail 1", e.toString())
            }
            try {
                resulta = con.inputStream.bufferedReader().readText()
                Log.e("data", resulta)
            } catch (e: java.lang.Exception) {
                Log.e("Fail 2", e.toString())
            }
            return null;
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressBar.visibility = View.GONE
            var json_data = JSONArray(resulta)
            for (i in 0 until json_data.length()) {
                val jsonObject = json_data.getJSONObject(i)
                val Uname = jsonObject.optString("name")
                val tktNumber = jsonObject.optString("ticket_number")
                val source = jsonObject.optString("source")
                val destination = jsonObject.optString("destination")
                mTicketDetails.add(Ticket(Uname, tktNumber,source, destination))
            }
            mRecyclerView.adapter = AllTicketAdapters(mTicketDetails)

            Log.e("data",json_data.toString())

        }
    }
}