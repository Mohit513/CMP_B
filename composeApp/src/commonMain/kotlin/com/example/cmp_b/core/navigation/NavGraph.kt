package com.example.cmp_b.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmp_b.ui.post_list.PostListScreen
import com.example.cmp_b.ui.post_list.PostViewModel
import org.koin.compose.currentKoinScope

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.PostList
    ) {
        composable<Route.PostList> {
            val koinScope = currentKoinScope()
            PostListScreen(
                viewModel = viewModel { koinScope.get<PostViewModel>() }
            )
        }

        // Example for future screens
        // composable<Route.PostDetail> { backStackEntry ->
        //     val detail: Route.PostDetail = backStackEntry.toRoute()
        //     PostDetailScreen(postId = detail.id)
        // }
    }
}
