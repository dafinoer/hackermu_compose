package com.dafinrs.hackermu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dafinrs.hackermu.pages.detail.DETAIL_SCREEN_PAGE_ROUTE
import com.dafinrs.hackermu.pages.detail.DetailScreenPage
import com.dafinrs.hackermu.pages.home.HOME_SCREEN_PAGE
import com.dafinrs.hackermu.pages.home.HomeScreenPage
import com.dafinrs.hackermu.presents.news.StoryNewsViewModel
import com.dafinrs.hackermu.ui.theme.HackermuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackermuTheme {
                HackerMuNavHost()
            }
        }
    }
}


@Composable
fun HackerMuNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_SCREEN_PAGE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(HOME_SCREEN_PAGE) {
            HomeScreenPage(
                onNavigateDetail = {
                    navController.navigate("$DETAIL_SCREEN_PAGE_ROUTE?urlStory=$it")
                },
            )
        }
        composable(
            "$DETAIL_SCREEN_PAGE_ROUTE?urlStory={urlLink}",
            arguments = listOf(navArgument("urlLink") {
                type = NavType.StringType
            })
        ) {
            DetailScreenPage(
                url = it.arguments?.getString("urlLink"),
                isBack = true,
                onBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}