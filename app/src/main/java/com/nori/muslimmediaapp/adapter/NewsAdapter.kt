package com.nori.muslimmediaapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nori.muslimmediaapp.R
import com.nori.muslimmediaapp.databinding.NewsItemOneBinding
import com.nori.muslimmediaapp.model.network.ArticlesItem
import com.nori.muslimmediaapp.ui.DetailActivity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// TODO 18 - CREATE NEWS ADAPTER
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val listNews = ArrayList<ArticlesItem>()
    class NewsViewHolder(var binding: NewsItemOneBinding) : RecyclerView.ViewHolder(binding.root)

    // TODO 31 - EDIT NEWS ADAPTER
    fun setData(list: List<ArticlesItem>?) {
        if (list == null) return
        listNews.clear()
        listNews.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        NewsItemOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = listNews[position]

        val date = news.publishedAt?.take(10) // take first 10 char of API data
        val dateArray = date?.split("-")?.toTypedArray() // convert string type to typed array

        val time = news.publishedAt?.takeLast(9)
        val timeArray = time?.split(":")?.toTypedArray()

        val calendar = Calendar.getInstance()
        dateArray?.let {
            calendar.set(Calendar.YEAR, it[0].toInt())
            calendar.set(Calendar.MONTH, it[1].toInt() -1)
            calendar.set(Calendar.DAY_OF_MONTH, it[2].toInt())
        }
        timeArray?.let {
            calendar.set(Calendar.HOUR_OF_DAY, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
        }
        val dateResultFormat = SimpleDateFormat("EEE, dd MMMM", Locale.getDefault())
            .format(calendar.time).toString()
        val timeResultFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(calendar.time).toString()

        // print log info on logcat for date and time
        Log.i("NewsAdapter", "onBindViewHolder: $dateResultFormat")
        Log.i("NewsAdapter", "onBindViewHolder: $timeResultFormat")
        val newsDate = "$dateResultFormat | "
        val newsTime = "$timeResultFormat UTC"

        holder.binding.apply {
            tvSource.text = news.source?.name
            tvTitle.text = news.title
            tvDate.text = newsDate
            tvTime.text = newsTime
            Picasso.get()
                .load(news.urlToImage)
                .resize(2048, 1600)
                .onlyScaleDown()
                .placeholder(R.drawable.ic_logo)
                .into(ivNews)
//            Picasso.get().load(news.urlToImage)
//                .placeholder(R.drawable.ic_logo)
//                .into(ivNews)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_DATA, news)
            intent.putExtra(DetailActivity.EXTRA_DATA_DATE, newsDate)
            intent.putExtra(DetailActivity.EXTRA_DATA_TIME, newsTime)
            it.context.startActivity(intent)
        }
    }
}