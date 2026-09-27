package com.jeepark.onestep.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.jeepark.onestep.ui.theme.CreamSurface
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.PrimaryGreen

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

// 순서: 발자취 · 홈 · 진척도 (확정된 목업 순서)
private val bottomNavItems = listOf(
    BottomNavItem(route = "completed", label = "발자취", icon = Icons.AutoMirrored.Filled.DirectionsWalk),
    BottomNavItem(route = "main", label = "홈", icon = Icons.Filled.Home),
    BottomNavItem(route = "progress", label = "진척도", icon = Icons.Filled.BarChart)
)

/** 홈/진척도/발자취 세 화면 전용 하단 탭 바. 스와이프 대신 탭으로만 전환한다. */
@Composable
fun OneStepBottomBar(
    navController: NavHostController,
    currentRoute: String?,
    onReselectFootprints: () -> Unit = {}
) {
    NavigationBar(containerColor = CreamSurface) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (selected) {
                        // 이미 발자취 화면에 있을 때 다시 누르면 이전 퀘스트/통계 탭을 토글
                        if (item.route == "completed") onReselectFootprints()
                    } else {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            popUpTo("main") { saveState = true }
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    unselectedIconColor = MutedText,
                    unselectedTextColor = MutedText,
                    indicatorColor = CreamSurface
                )
            )
        }
    }
}
