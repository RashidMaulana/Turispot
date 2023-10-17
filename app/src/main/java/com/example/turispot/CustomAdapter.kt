package com.example.turispot

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.turispot.CustomAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class CustomAdapter internal constructor(
    private val context: Context,
    private val namauser: ArrayList<String>,
    private val ratinguser: ArrayList<Int>,
    private val tanggalreview: ArrayList<String>,
    private val kontenuser: ArrayList<String>,

) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.review_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvUsernameReview.text = namauser[position]
        holder.tvRatingReview.text = ratinguser[position].toString()
        holder.tvTanggalReview.text = tanggalreview[position]
        holder.tvKontenReview.text = kontenuser[position]
        holder.image.setImageResource(R.drawable.avatar)

    }

    override fun getItemCount(): Int {
        return namauser.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsernameReview: TextView
        var tvTanggalReview: TextView
        var tvKontenReview: TextView
        var tvRatingReview: TextView
        var image: ImageView

        init {
            tvUsernameReview = itemView.findViewById(R.id.tvUsernameReview)
            tvTanggalReview = itemView.findViewById(R.id.tvTanggalReview)
            tvKontenReview = itemView.findViewById(R.id.tvKontenReview)
            tvRatingReview = itemView.findViewById(R.id.tvRatingKonten)
            image = itemView.findViewById<ImageView>(R.id.imageView16)
        }
    }
}