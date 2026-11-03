package com.example.soal.model

data class ItemPesanan(
    val makanan: Makanan,
    val jumlah: Int
) {
    fun hitungSubtotal(): Int = makanan.harga * jumlah
}
