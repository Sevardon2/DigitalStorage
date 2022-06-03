package com.mokha.compshop

class data_comp {
    var nama: String? = null
    var jenis: String? = null
    var jumlah: String? = null
    var key: String? = null

    constructor()

    constructor(nama: String?, jenis: String?, jumlah: String?) {
        this.nama = nama
        this.jenis = jenis
        this.jumlah = jumlah
    }
}