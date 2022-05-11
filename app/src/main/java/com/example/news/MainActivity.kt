package com.example.news

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.adapter.NewAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.models.New
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewAdapter
    private val news = mutableListOf<New>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecViewNews()

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getNews("emailed/7.json?api-key=4R28OIIz7UAQUR1AHROYqmR8K2CsuxFb")
            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    Log.i("DEBUG_INFO", "${response}")
                    var newsAux = response?.results ?: emptyList()
                    news.clear()
                    news.addAll(newsAux)
                    adapter.notifyDataSetChanged()
                } else {

                }
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecViewNews() {
        adapter = NewAdapter(news) {
            Log.i("DEBUG_INFO", "${it.url}")
            var viewWeb = binding.webView
            viewWeb.loadUrl(it.url);
        }
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNews.adapter = adapter
    }
}