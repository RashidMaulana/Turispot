package com.example.turispot

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.recyclerview.widget.RecyclerView
import com.example.turispot.AdapterWisata.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import java.util.ArrayList

class AdapterWisata internal constructor(
    private val context: Context,
    private val namawisata: ArrayList<String>,
    private val alamatwisata: ArrayList<String>,
    private val hargawisata: ArrayList<String>,
    private val kategoriwisata: ArrayList<String>,
    private val deskripsiwisata: ArrayList<String>,
    private val iduser: Int
    ) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_wisata, parent, false)
        return MyViewHolder(view)
    }



    override fun getItemCount(): Int {
        return namawisata.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView
        var tvAlamat: TextView
        var tvHarga: TextView
        var tvKategori: TextView
        var image : ImageView


        init {
            tvNama = itemView.findViewById(R.id.tvNamaWisata)
            tvAlamat = itemView.findViewById(R.id.tvAlamatWisata)
            tvHarga = itemView.findViewById(R.id.tvHargaWisata)
            tvKategori = itemView.findViewById(R.id.tvKategori)
            image = itemView.findViewById(R.id.imageView16)



        }
    }




    override fun onBindViewHolder(holder: AdapterWisata.MyViewHolder, position: Int) {
        val intent: Intent? = null
        holder.tvNama.text = namawisata[position]
        holder.tvAlamat.text = alamatwisata[position].toString()
        holder.tvHarga.text = hargawisata[position]
        holder.tvKategori.text = kategoriwisata[position]
        holder.image.setImageResource(R.drawable.tamansari)


        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, DetailWisata::class.java).apply {
                putExtra("tvNama", namawisata[position])
                putExtra("tvAlamat", alamatwisata[position])
                putExtra("tvHarga", hargawisata[position])
                putExtra("tvKategori", kategoriwisata[position])
                putExtra("deskripsi", deskripsiwisata[position])
                putExtra("iduser", iduser)


            })
        }



    }
}