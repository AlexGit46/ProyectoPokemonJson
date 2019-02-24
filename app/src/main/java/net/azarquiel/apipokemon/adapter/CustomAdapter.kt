package net.azarquiel.apipokemon.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*
import net.azarquiel.apipokemon.api.NameUrl

class CustomAdapter(val context: Context, val layout: Int, val dataList: List<NameUrl>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: NameUrl){
            itemView.tvNombreRow.text = dataItem.name
            var url = dataItem.url
            url = url.substring(0,url.length-1)
            url = url.substringAfterLast("/")
            itemView.tvNumeroRow.text = url
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${url}.png").into(itemView.ivRow)
            itemView.tag = dataItem
        }
    }
}