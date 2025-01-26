package io.hvk.koreanculturecenterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.hvk.koreanculturecenterapp.screen.details.DetailScreen
import io.hvk.koreanculturecenterapp.screen.events.EventScreen
import io.hvk.koreanculturecenterapp.screen.news.NewsScreen
import io.hvk.koreanculturecenterapp.screen.press.PressScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.News.route) {
        composable(NavigationItem.News.route) {
            NewsScreen(
                onClick = { link ->
                    navController.navigate("detail_screen?link=$link")
                }
            )
        }
        composable(NavigationItem.Event.route) {
            EventScreen(
                onClick = { link ->
                    navController.navigate("detail_screen?link=$link")
                }
            )
        }
        composable(NavigationItem.Press.route) {
            PressScreen(
                onClick = { link ->
                    navController.navigate("detail_screen?link=$link")
                }
            )
        }

        composable(
            route = "detail_screen?link={link}",
            arguments = listOf(
                navArgument("link") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val link = backStackEntry.arguments?.getString("link")

            DetailScreen(
                link = link,
                onBackClick = { navController.popBackStack() }
            )
        }
//        composable(
//            route = "quiz_detail/{quizId}",
//            arguments = listOf(
//                navArgument("quizId") { type = NavType.IntType }
//            )
//        ) { backStackEntry ->
//            val quizId = backStackEntry.arguments?.getInt("quizId") ?: 1
//            when (quizId) {
//                1 -> BasicVocabularyQuizScreen(
//                    onBackClick = { navController.popBackStack() }
//                )
//                2 -> GrammarPatternsQuizScreen(
//                    onBackClick = { navController.popBackStack() }
//                )
//                3 -> NumbersQuizScreen(
//                    onBackClick = { navController.popBackStack() }
//                )
//                4 -> DailyConversationQuizScreen(
//                    onBackClick = { navController.popBackStack() }
//                )
//                5 -> FoodQuizScreen(
//                    onBackClick = { navController.popBackStack() }
//                )
//            }
//        }
    }
}
