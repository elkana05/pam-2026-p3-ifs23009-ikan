package org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.delcom.pam_2026_p3_ifs23009_ikan.data.DummyFishData
import org.delcom.pam_2026_p3_ifs23009_ikan.data.Fish
import org.delcom.pam_2026_p3_ifs23009_ikan.helper.ConstsHelper
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.BottomNavComponent
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.TopAppBarComponent

@Composable
fun HomeScreen(navController: NavController) {

    val fishList = DummyFishData.fishList
    val totalFish = fishList.size
    val featuredFish = fishList.first()

    // Top 3 most expensive (index 0..2)
    val topThree = fishList.take(3)

    // "Most Popular" — pick 5 random/varied picks (index 4..8)
    val popularFish = fishList.subList(4, 9)

    Scaffold(
        topBar = {
            TopAppBarComponent(title = ConstsHelper.TITLE_HOME)
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
                .verticalScroll(rememberScrollState())
        ) {

            // ── HEADER BANNER ──────────────────────────────────────────────
            HeaderBanner(featuredFish = featuredFish, navController = navController)

            Spacer(modifier = Modifier.height(20.dp))

            // ── STATS ROW ──────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatChip(
                    modifier = Modifier.weight(1f),
                    value = "$totalFish",
                    label = "Jenis Ikan",
                    color = Color(0xFF1565C0)
                )
                StatChip(
                    modifier = Modifier.weight(1f),
                    value = "Premium",
                    label = "Koleksi",
                    color = Color(0xFF6A1B9A)
                )
                StatChip(
                    modifier = Modifier.weight(1f),
                    value = "Rare",
                    label = "Species",
                    color = Color(0xFF00695C)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── TOP 3 MAHAL ────────────────────────────────────────────────
            SectionTitle(
                title = "🏆 Ikan Termahal",
                subtitle = "Koleksi paling eksklusif di dunia",
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                topThree.forEachIndexed { index, fish ->
                    TopRankCard(
                        rank = index + 1,
                        fish = fish,
                        onClick = {
                            navController.navigate("${ConstsHelper.ROUTE_FISH_DETAIL}/${fish.id}")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── POPULAR HORIZONTAL SCROLL ──────────────────────────────────
            SectionTitle(
                title = "🌊 Populer Minggu Ini",
                subtitle = "Banyak dicari kolektor ikan hias",
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(popularFish) { fish ->
                    PopularFishCard(
                        fish = fish,
                        onClick = {
                            navController.navigate("${ConstsHelper.ROUTE_FISH_DETAIL}/${fish.id}")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── EXPLORE BUTTON ─────────────────────────────────────────────
            Button(
                onClick = { navController.navigate(ConstsHelper.ROUTE_FISH) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1565C0)
                )
            ) {
                Text(
                    text = "Lihat Semua Koleksi",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// ── HEADER BANNER ─────────────────────────────────────────────────────────────
@Composable
private fun HeaderBanner(featuredFish: Fish, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .clickable {
                navController.navigate("${ConstsHelper.ROUTE_FISH_DETAIL}/${featuredFish.id}")
            }
    ) {
        Image(
            painter = painterResource(id = featuredFish.imageRes),
            contentDescription = featuredFish.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.75f)
                        ),
                        startY = 80f
                    )
                )
        )

        // Badge "Featured"
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart),
            shape = RoundedCornerShape(50),
            color = Color(0xFFFFC107)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Featured",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        // Content bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = featuredFish.name,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = featuredFish.price,
                color = Color(0xFFFFC107),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = featuredFish.description,
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// ── STAT CHIP ─────────────────────────────────────────────────────────────────
@Composable
private fun StatChip(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    color: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = color.copy(alpha = 0.8f)
            )
        }
    }
}

// ── SECTION TITLE ─────────────────────────────────────────────────────────────
@Composable
private fun SectionTitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF888888)
        )
    }
}

// ── TOP RANK CARD ─────────────────────────────────────────────────────────────
@Composable
private fun TopRankCard(rank: Int, fish: Fish, onClick: () -> Unit) {
    val rankColor = when (rank) {
        1 -> Color(0xFFFFD700)
        2 -> Color(0xFFC0C0C0)
        else -> Color(0xFFCD7F32)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank badge
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(rankColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "#$rank",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Fish image
            Image(
                painter = painterResource(id = fish.imageRes),
                contentDescription = fish.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = fish.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = fish.price,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF1565C0),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fish.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF888888),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color(0xFFCCCCCC),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ── POPULAR FISH CARD (horizontal scroll) ────────────────────────────────────
@Composable
private fun PopularFishCard(fish: Fish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = fish.imageRes),
                contentDescription = fish.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = fish.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fish.price,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF1565C0),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}