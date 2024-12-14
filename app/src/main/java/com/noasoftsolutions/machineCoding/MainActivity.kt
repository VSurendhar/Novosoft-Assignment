package com.noasoftsolutions.machineCoding

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noasoftsolutions.machineCoding.comman.Comman.TAG
import com.noasoftsolutions.machineCoding.screens.BankScreen
import com.noasoftsolutions.machineCoding.screens.HistoryScreen
import com.noasoftsolutions.machineCoding.screens.HomeScreen
import com.noasoftsolutions.machineCoding.screens.ProfileScreen
import com.noasoftsolutions.machineCoding.screens.Screens
import com.noasoftsolutions.machineCoding.ui.theme.NoaSoftSolutionsTheme
import com.noasoftsolutions.machineCoding.viewModels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
            val navController = rememberNavController()
            val viewModel: MyViewModel = hiltViewModel()
            viewModel.getUsers()

            NoaSoftSolutionsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            selectedIndex,
                            navController,
                            listOf(
                                Screens.HomeScreen,
                                Screens.BankScreen,
                                Screens.HistoryScreen,
                                Screens.ProfileScreen
                            ),
                            onScreenChange = { selectedIndex = it })
                    }) { innerPadding ->
                    HomeNavigationComponent(
                        navController,
                        modifier = Modifier.padding(innerPadding),viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    navController: NavHostController,
    items: List<Screens>,
    onScreenChange: (Int) -> Unit
) {

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        items.forEachIndexed { index, screen ->
            val isSelected = selectedIndex == index
            NavigationBarItem(icon = {
                Icon(
                    painter = painterResource(
                        id = if (isSelected) screen.filledIcon else screen.outlineIcon
                    ),
                    contentDescription = "${screen.label} icon",
                )
            }, label = { Text(screen.label) }, selected = isSelected, onClick = {
                onScreenChange(index)
                if (!isSelected) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color(0XFF94CA9C),
                unselectedTextColor = Color(0XFF94CA9C)
            )
            )
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavigationComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel) {
        viewModel.toastMessages.collect { message ->
            Toast.makeText(context, "API Fetch : $message", Toast.LENGTH_SHORT).show()
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        addComposableScreens(this, modifier, viewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addComposableScreens(
    navGraphBuilder: NavGraphBuilder,
    modifier: Modifier,
    viewModel: MyViewModel,
) {
    navGraphBuilder.composable(Screens.HomeScreen.route) {
        HomeScreen(modifier, viewModel)
    }
    navGraphBuilder.composable(Screens.BankScreen.route) {
        BankScreen(modifier)
    }
    navGraphBuilder.composable(Screens.HistoryScreen.route) {
        HistoryScreen(modifier)
    }
    navGraphBuilder.composable(Screens.ProfileScreen.route) {
        ProfileScreen(modifier)
    }
}
