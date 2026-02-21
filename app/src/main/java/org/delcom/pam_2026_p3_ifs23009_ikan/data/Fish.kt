package org.delcom.pam_2026_p3_ifs23009_ikan.data

data class Fish(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageRes: Int,
    val origin: String,       // Asal habitat / negara
    val size: String,         // Ukuran tubuh
    val lifespan: String,     // Umur rata-rata
    val difficulty: String    // Tingkat perawatan
)