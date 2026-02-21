package org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.delcom.pam_2026_p3_ifs23009_ikan.data.DummyFishData
import org.delcom.pam_2026_p3_ifs23009_ikan.helper.ConstsHelper
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.BottomNavComponent
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.TopAppBarComponent

@Composable
fun ProfileScreen(navController: NavController) {

    val totalFish = DummyFishData.fishList.size

    Scaffold(
        topBar = {
            TopAppBarComponent(title = ConstsHelper.TITLE_PROFILE)
        },
        bottomBar = {
            BottomNavComponent(navController)
        },
        containerColor = Color(0xFFF5F7FA)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── HEADER GRADIENT BANNER ─────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1565C0),
                                Color(0xFF0D47A1),
                                Color(0xFF1B5E20)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Decorative circles
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .offset(x = 100.dp, y = (-30).dp)
                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = (-90).dp, y = 40.dp)
                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Fish Collection",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp,
                        letterSpacing = 3.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "My Profile",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            // ── AVATAR (overlap banner) ────────────────────────────────────
            Box(
                modifier = Modifier
                    .offset(y = (-48).dp)
                    .size(96.dp)
                    .shadow(12.dp, CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF42A5F5), Color(0xFF1565C0))
                        ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(52.dp)
                )
            }

            // ── NAME & NIM ─────────────────────────────────────────────────
            Spacer(modifier = Modifier.offset(y = (-36).dp))
            Column(
                modifier = Modifier.offset(y = (-36).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Elkana Sitorus",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFF1565C0).copy(alpha = 0.1f)
                ) {
                    Text(
                        text = "IFS23009",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFF1565C0),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // ── STATS ROW ──────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-20).dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MiniStatCard(modifier = Modifier.weight(1f), value = "$totalFish", label = "Koleksi")
                MiniStatCard(modifier = Modifier.weight(1f), value = "20", label = "Favorit")
                MiniStatCard(modifier = Modifier.weight(1f), value = "2026", label = "Tahun")
            }

            Spacer(modifier = Modifier.height(4.dp))

            // ── INFO CARD ──────────────────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Informasi Mahasiswa",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ProfileInfoRow(
                        icon = Icons.Default.Person,
                        label = "Nama Lengkap",
                        value = "Elkana Sitorus",
                        iconColor = Color(0xFF1565C0)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF0F0F0))
                    ProfileInfoRow(
                        icon = Icons.Default.Badge,
                        label = "NIM",
                        value = "IFS23009",
                        iconColor = Color(0xFF6A1B9A)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF0F0F0))
                    ProfileInfoRow(
                        icon = Icons.Default.School,
                        label = "Institusi",
                        value = "Institut Teknologi Del",
                        iconColor = Color(0xFF00695C)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF0F0F0))
                    ProfileInfoRow(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = "ifs23009@students.del.ac.id",
                        iconColor = Color(0xFFD32F2F)
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF0F0F0))
                    ProfileInfoRow(
                        icon = Icons.Default.Code,
                        label = "Program Studi",
                        value = "Informatika",
                        iconColor = Color(0xFFF57F17)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── ABOUT APP CARD ─────────────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFF1565C0).copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color(0xFF1565C0),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Tentang Aplikasi",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Aplikasi ini dibuat untuk menampilkan koleksi ikan hias termahal dan unik di dunia menggunakan Jetpack Compose. Dibangun sebagai bagian dari tugas PAM 2026.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF555555),
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFF5F7FA)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppMetaItem(label = "Versi", value = "1.0.0")
                            AppMetaItem(label = "Platform", value = "Android")
                            AppMetaItem(label = "Tahun", value = "2026")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── MINI STAT CARD ────────────────────────────────────────────────────────────
@Composable
private fun MiniStatCard(modifier: Modifier = Modifier, value: String, label: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color(0xFF1565C0)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF888888)
            )
        }
    }
}

// ── PROFILE INFO ROW ─────────────────────────────────────────────────────────
@Composable
private fun ProfileInfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF999999)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E)
            )
        }
    }
}

// ── APP META ITEM ─────────────────────────────────────────────────────────────
@Composable
private fun AppMetaItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color(0xFF1A1A2E)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF999999)
        )
    }
}