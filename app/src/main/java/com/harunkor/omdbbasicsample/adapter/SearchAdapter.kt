package com.harunkor.omdbbasicsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.omdbbasicsample.R
import com.harunkor.omdbbasicsample.model.Search.Search
import com.squareup.picasso.Picasso

class SearchAdapter(val searchList: List<Search>, val navController: NavController):RecyclerView.Adapter<SearchAdapter.ModelViewHolder>(){



    class ModelViewHolder(view: View, navController: NavController) : RecyclerView.ViewHolder(view) {
        val filmtitle: TextView = view.findViewById(R.id.filimtitle)
        val filmyear: TextView = view.findViewById(R.id.filimyear)
        val type: TextView = view.findViewById(R.id.typee)
        val filmimage: ImageView = view.findViewById(R.id.filmimgleft)
        val view:View
        val imdbIDTAG="imdbID"
        val navController:NavController

        init {
            this.view=view
            this.navController=navController
        }

        fun bindItems(item: Search) {
            filmtitle.text=item.Title
            filmyear.text=item.Year
            type.text=item.Type.uppercase()
            Picasso.get().load(item.Poster).into(filmimage);
            view.setOnClickListener (object :View.OnClickListener{
                override fun onClick(p0: View?) {
                    val bundle = bundleOf(imdbIDTAG to item.imdbID  )
                    navController.navigate(R.id.action_searchFragment_to_detailFragment,bundle)
                }
            })
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ModelViewHolder(view,navController)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(searchList.get(position))
    }

    override fun getItemCount(): Int {
       return searchList.size
    }
}