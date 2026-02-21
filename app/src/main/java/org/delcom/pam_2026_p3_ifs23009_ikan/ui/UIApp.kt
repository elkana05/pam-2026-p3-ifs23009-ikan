package org.delcom.pam_2026_p3_ifs23009_ikan.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.delcom.pam_2026_p3_ifs23009_ikan.helper.RouteHelper
import org.delcom.pam_2026_p3_ifs23009_ikan.ui.theme.PAM2026Theme

@Composable
fun UIApp() {

    val navController = rememberNavController()

    PAM2026Theme {
        RouteHelper(
            navController = navController
        )
    }
}
