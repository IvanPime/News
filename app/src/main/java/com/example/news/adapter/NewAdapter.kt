package com.example.news.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.ItemNewBinding
import com.example.news.models.New
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class NewAdapter(
        private val news: List<New>,
        private var setOnClickListener: (New) -> Unit
    ): RecyclerView.Adapter<NewAdapter.ViewHolder>() {

    class ViewHolder(val item: View): RecyclerView.ViewHolder(item) {
        val tvTitulo = item.findViewById<TextView>(R.id.tvTituloNew)
        val tvFecha = item.findViewById<TextView>(R.id.tvFecha)
        val imageView = item.findViewById<ImageView>(R.id.imgNew)
        val loadingWheel = item.findViewById<ProgressBar>(R.id.loading_wheel)

        fun bindNew(new: New) {
            tvTitulo.text = new.title
            tvFecha.text = new.fecha
            var listenerPicasso = object: Callback {
                override fun onSuccess() {
                    loadingWheel.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    loadingWheel.visibility = View.GONE
                    imageView.setImageResource(R.drawable.ic_image_not_supported_black)
                }
            }

            Picasso.get().load(new.media.first().mediasMetaData.first().url).into(imageView, listenerPicasso);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapter.ViewHolder {
        var binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewAdapter.ViewHolder, position: Int) {
        val new = news[position]
        holder.bindNew(new)
        holder.item.setOnClickListener {
            setOnClickListener(new)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

}