package org.delcom.pam_2026_p3_ifs23009_ikan.helper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens.FishDetailScreen
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens.FishScreen
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens.HomeScreen
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.screens.ProfileScreen

@Composable
fun RouteHelper(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = ConstsHelper.ROUTE_HOME
    ) {

        // ================= HOME =================
        composable(ConstsHelper.ROUTE_HOME) {
            HomeScreen(navController = navController)
        }

        // ================= FISH LIST =================
        composable(ConstsHelper.ROUTE_FISH) {
            FishScreen(navController = navController)
        }

        // ================= FISH DETAIL =================
        composable(
            route = "${ConstsHelper.ROUTE_FISH_DETAIL}/{${ConstsHelper.ARG_FISH_ID}}",
            arguments = listOf(
                navArgument(ConstsHelper.ARG_FISH_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val fishId = backStackEntry.arguments
                ?.getInt(ConstsHelper.ARG_FISH_ID) ?: 0

            FishDetailScreen(
                navController = navController,
                fishId = fishId
            )
        }

        // ================= PROFILE =================
        composable(ConstsHelper.ROUTE_PROFILE) {
            ProfileScreen(navController = navController)
        }
    }
}