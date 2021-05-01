package com.example.bookingtrainapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingtrainapp.R
import com.example.bookingtrainapp.models.Ticket

class AllTicketAdapters (val tickets: ArrayList<Ticket>):RecyclerView.Adapter<AllTicketAdapters.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtPassengerName: TextView = view.findViewById(R.id.cname)
        val txtSourceFrom: TextView = view.findViewById(R.id.csource)
        val txtDestinationTo: TextView = view.findViewById(R.id.cdestination)
        val txtTkTNumber: TextView = view.findViewById(R.id.cticketno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticket_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = tickets[position]
        holder.txtTkTNumber.text = currentItem.ticket_number
        holder.txtPassengerName.text = currentItem.username
        holder.txtSourceFrom.text = currentItem.source
        holder.txtDestinationTo.text = currentItem.destination
    }
        override fun getItemCount() = tickets.size


}