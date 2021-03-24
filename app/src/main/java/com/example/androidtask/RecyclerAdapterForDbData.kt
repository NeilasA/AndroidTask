package com.example.androidtask


import android.os.SystemClock
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.api.Data
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapterForDbData(private var titles: List<String>, private var updatedAt: List<String>) :RecyclerView.Adapter<RecyclerAdapterForDbData.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.todos_tittle)
        val itemUpdatedAt: TextView = itemView.findViewById(R.id.todos_updated_at)

        init {

            //On click event show or hides date when todos was updated

            itemView.cardView.setOnClickListener { _: View ->

                if (itemView.expandableLayout.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(itemView.cardView, AutoTransition())
                    itemView.expandableLayout.visibility = View.GONE
                }
                else {
                    TransitionManager.beginDelayedTransition(itemView.cardView, AutoTransition())
                    itemView.expandableLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent, false))
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemUpdatedAt.text = updatedAt[position]
    }


}