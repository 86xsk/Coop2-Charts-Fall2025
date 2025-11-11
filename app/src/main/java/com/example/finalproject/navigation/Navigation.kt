package com.example.finalproject.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import com.example.finalproject.destination.Destination
import com.example.finalproject.extensions.string.upperFirstChar
import com.example.finalproject.screens.EditScreen
import com.example.finalproject.screens.PracticeScreen
import com.example.finalproject.screens.StatsScreen

data class NavItem(val destination: Destination, val iconPainter: Painter?)
val NAV_ITEMS: List<NavItem> = listOf(
    NavItem(Destination.Stats, null),
    NavItem(Destination.Edit, null),
    NavItem(Destination.Practice, null)
);
val DEFAULT_DESTINATION = Destination.Stats

@Composable
fun BottomNav(navController: NavController) {
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        NAV_ITEMS.forEach { navItem ->
            NavigationBarItem(
                selected = currentDestination?.route == navItem.destination.route,
                onClick = {
                    navController.navigate(navItem.destination.route){
                        popUpTo(navItem.destination.route)
                        launchSingleTop = true
                    }},
                // Use icon if it was specified for the `navItem`.
                icon = {if (navItem.iconPainter != null) { Icon(painter = navItem.iconPainter, contentDescription = null)}},
                label = { Text(text = navItem.destination.route.upperFirstChar()) }
            )
        }
    }
}
