package com.example.soal.model

class Pesanan(
    val namaPelanggan: String,
    val daftarItem: ArrayList<ItemPesanan> = ArrayList()
) {
    fun hitungTotal(): Int {
        var total = 0
        for (item in daftarItem) {
            total += item.hitungSubtotal()
        }
        return total
    }
}
