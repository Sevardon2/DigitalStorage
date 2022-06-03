package com.mokha.compshop

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val listdata_barang: ArrayList<data_comp>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private val context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nama: TextView
        val Jenis: TextView
        val Jumlah: TextView
        val ListItem: LinearLayout

        init {
            Nama = itemView.findViewById(R.id.nama)
            Jenis = itemView.findViewById(R.id.jenis)
            Jumlah = itemView.findViewById(R.id.jumlah)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design,
            parent, false)
        return ViewHolder(V)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val Nama: String? = listdata_barang.get(position).nama
        val Jenis: String? = listdata_barang.get(position).jenis
        val Jumlah: String? = listdata_barang.get(position).jumlah

        holder.Nama.text = "Nama Barang : $Nama"
        holder.Jenis.text = "Jenis Barang : $Jenis"
        holder.Jumlah.text = "Jumlah : $Jumlah"
        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                holder.ListItem.setOnLongClickListener { view ->
                    val action = arrayOf("Update", "Delete")
                    val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                        when (i) { 0 -> {
                            val bundle = Bundle()
                            bundle.putString("dataNama", listdata_barang[position].nama)
                            bundle.putString("dataJenis", listdata_barang[position].jenis)
                            bundle.putString("dataJumlah", listdata_barang[position].jumlah)
                            bundle.putString("getPrimaryKey", listdata_barang[position].key)
                            val intent = Intent(view.context, UpdateData::class.java)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                        } 1 -> {
                            listener?.onDeleteData(listdata_barang.get(position), position)
                        }
                        }
                    })
                    alert.create()
                    alert.show()
                    true
                }
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return listdata_barang.size
    }

    var listener: dataListener? = null
    init {
        this.context = context
        this.listener = context as MyListData
    }

    interface dataListener {
        fun onDeleteData(data: data_comp?, position: Int)
    }


}