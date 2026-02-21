package org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.delcom.pam_2026_p3_ifs23009_ikan.data.DummyFishData
import org.delcom.pam_2026_p3_ifs23009_ikan.helper.ConstsHelper
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.TopAppBarComponent

@Composable
fun FishDetailScreen(
    navController: NavController,
    fishId: Int
) {

    val fish = DummyFishData.fishList.find { it.id == fishId }

    if (fish == null) {
        Scaffold(
            topBar = {
                TopAppBarComponent(
                    title = ConstsHelper.TITLE_DETAIL,
                    showBackButton = true,
                    navController = navController
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Data ikan tidak ditemukan")
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = ConstsHelper.TITLE_DETAIL,
                showBackButton = true,
                navController = navController
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // --- Gambar Ikan ---
            Image(
                painter = painterResource(id = fish.imageRes),
                contentDescription = fish.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Crop
            )

            // --- Card Utama ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    // Nama Ikan
                    Text(
                        text = fish.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Harga
                    Text(
                        text = fish.price,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))

                    // Deskripsi (Field 1)
                    SectionLabel(label = "Deskripsi")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = fish.description,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Grid 4 info card (Field 2–5)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Asal",
                            value = fish.origin
                        )
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Ukuran",
                            value = fish.size
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Umur",
                            value = fish.lifespan
                        )
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            label = "Perawatan",
                            value = fish.difficulty,
                            valueColor = difficultyColor(fish.difficulty)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun InfoCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )
        }
    }
}

@Composable
private fun difficultyColor(difficulty: String): androidx.compose.ui.graphics.Color {
    return when (difficulty) {
        "Mudah" -> androidx.compose.ui.graphics.Color(0xFF2E7D32)
        "Sedang" -> androidx.compose.ui.graphics.Color(0xFFF57F17)
        "Tinggi" -> androidx.compose.ui.graphics.Color(0xFFD32F2F)
        "Sangat Tinggi" -> androidx.compose.ui.graphics.Color(0xFF6A1B9A)
        else -> androidx.compose.ui.graphics.Color.Unspecified
    }
}