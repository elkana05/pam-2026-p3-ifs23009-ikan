package org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.delcom.pam_2026_p3_ifs23009_ikan.data.DummyFishData
import org.delcom.pam_2026_p3_ifs23009_ikan.helper.ConstsHelper
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.BottomNavComponent
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.components.TopAppBarComponent

@Composable
fun FishScreen(
    navController: NavController
) {

    val fishList = DummyFishData.fishList

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = ConstsHelper.TITLE_FISH
            )
        },
        bottomBar = {
            BottomNavComponent(navController)
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(fishList) { fish ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                "${ConstsHelper.ROUTE_FISH_DETAIL}/${fish.id}"
                            )
                        },
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {

                    Column {

                        Image(
                            painter = painterResource(id = fish.imageRes),
                            contentDescription = fish.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = fish.name,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = fish.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
