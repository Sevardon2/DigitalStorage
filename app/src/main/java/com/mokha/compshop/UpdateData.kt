package com.mokha.compshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_data.*

class UpdateData : AppCompatActivity() {
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNama: String? = null
    private var cekJenis: String? = null
    private var cekJumlah: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        supportActionBar!!.title = "Update Data"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        data
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                cekNama = new_nama.getText().toString()
                cekJenis = new_jenis.getText().toString()
                cekJumlah = new_jumlah.getText().toString()

                if (isEmpty(cekNama!!) || isEmpty(cekJenis!!) || isEmpty(cekJumlah!!)) {
                    Toast.makeText(this@UpdateData,
                        "Data tidak boleh kosong",
                        Toast.LENGTH_SHORT).show()
                } else {
                    val setdata_barang = data_comp()
                    setdata_barang.nama = new_nama.getText().toString()
                    setdata_barang.jenis = new_jenis.getText().toString()
                    setdata_barang.jumlah = new_jumlah.getText().toString()
                    updateBarang(setdata_barang)
                }
            }
        })
    }

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }

    private val data: Unit
        private get() {
            val getNama = intent.extras!!.getString("dataNama")
            val getJenis = intent.extras!!.getString("dataJenis")
            val getJumlah = intent.extras!!.getString("dataJumlah")

            new_nama!!.setText(getNama)
            new_jenis!!.setText(getJenis)
            new_jumlah!!.setText(getJumlah)
        }

    private fun updateBarang(comp: data_comp) {
        val userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("DataComputer")
            .child(getKey!!)
            .setValue(comp)
            .addOnSuccessListener {
                new_nama!!.setText("")
                new_jenis!!.setText("")
                new_jumlah!!.setText("")
                Toast.makeText(this@UpdateData, "Data berhasil diubah",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}