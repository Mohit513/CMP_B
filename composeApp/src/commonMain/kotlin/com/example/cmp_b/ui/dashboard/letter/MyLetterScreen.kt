package com.example.cmp_b.ui.dashboard.letter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_forward
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.AppOnBoardingListItem
import com.example.cmp_b.ui.components.AppSimpleTopBar
import com.example.cmp_b.ui.theme.Azure
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.dashboard.letter.vm.MyLetterEvent
import com.example.cmp_b.ui.dashboard.letter.vm.MyLetterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyLetterScreen(
    appState: AppState,
    viewModel: MyLetterViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.navigation.collect { event ->

            when (event) {

                is NavigationEvent.Navigate -> {
                    appState.navigator.navigate(event.route)
                }

                is NavigationEvent.PopBack -> {
                    appState.navigator.popBack()
                }

                is NavigationEvent.ClearBackStackAndNavigate -> {
                    appState.navigator.clearAndNavigate(event.route)
                }

                is NavigationEvent.PopBackWithResult -> {
                    appState.navigator.popBackWithResult(
                        key = event.key,
                        value = event.value
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = BackgroundLight)
    ) {

        AppSimpleTopBar(
            title = "My Letter",
            backgroundColor = Azure,
            onBackClick = {
                viewModel.onEvent(MyLetterEvent.OnBackClick)
            }
        )

        when {

            uiState.isLoading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {

                Text(
                    text = uiState.error!!,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {

                LazyColumn {

                    items(uiState.items) { item ->

                        AppOnBoardingListItem(

                            title = item.title,

                            imageRes = item.icon,

                            icon = Res.drawable.ic_arrow_forward,

                            onIconClick = {

                                viewModel.onEvent(
                                    MyLetterEvent.OnItemClick(item)
                                )
                            }
                        )
                    }
                }
            }
        }
    }

}
