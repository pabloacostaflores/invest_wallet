package com.pabloacosta.investwallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pabloacosta.investwallet.R
import com.pabloacosta.investwallet.data.Inversion

class AdapterInversiones(val listInversiones : List<Inversion>) : RecyclerView.Adapter<AdapterInversiones.InversionesHolder>() {

    class InversionesHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.imInversion)
        val textName: TextView = view.findViewById(R.id.textNameItem)
        val textValor: TextView = view.findViewById(R.id.textValorItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InversionesHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return InversionesHolder(layoutInflater.inflate(R.layout.item_mercado, parent, false))
    }

    override fun onBindViewHolder(holder: InversionesHolder, position: Int) {
        val inversion = listInversiones[position]
        holder.textName.text = inversion.name
        holder.textValor.text = inversion.valor
        holder.img.setImageResource(inversion.img)
    }

    override fun getItemCount(): Int {
        return listInversiones.size
    }

}