package io.hvk.koreanculturecenterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.hvk.koreanculturecenterapp.screen.events.EventScreen
import io.hvk.koreanculturecenterapp.screen.news.NewsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.news.route) {
        composable(NavigationItem.news.route) {
            NewsScreen()
        }
        composable(NavigationItem.event.route) {
            EventScreen()
        }
        Compsoe
//        composable(NavigationItem.Lessons.route) {
//            val viewModel: MainViewModel = koinViewModel()
//            val lessons by viewModel.lessons.observeAsState(emptyList())
//            LessonsScreen(
//                lessons = lessons,
//                onSelect = { lessonNumber ->
//                    navController.navigate("lesson_detail/$lessonNumber") {
//                        launchSingleTop = true
//                    }
//                }
//            )
//        }
//        composable(NavigationItem.Quiz.route) {
//            QuizScreen(
//                onStartQuiz = { quizId ->
//                    navController.navigate("quiz_detail/$quizId")
//                }
//            )
//        }
//        composable(NavigationItem.Profile.route) {
//            ProfileScreen()
//        }
//        composable(
//            route = "lesson_detail/{lessonNumber}",
//            arguments = listOf(
//                navArgument("lessonNumber") { type = NavType.IntType }
//            )
//        ) { backStackEntry ->
//            val lessonNumber = backStackEntry.arguments?.getInt("lessonNumber") ?: 1
//            LessonDetailScreen(
//                number = lessonNumber,
//                onBackClick = { navController.popBackStack() }
//            )
//        }
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
