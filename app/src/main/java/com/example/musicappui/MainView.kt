package com.example.musicappui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember {
        mutableStateOf(currentScreen.title)
    }
    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val showBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn {
                    items(screensInDrawer) { item ->
                        DrawerItem(
                            selected = currentRoute == item.dRoute,
                            item = item,
                            onDrawerItemClicked = {
                                scope.launch {
                                    drawerState.close()
                                }

                                if (item.dRoute == "add_account") {
                                    dialogOpen.value = true
                                } else {
                                    controller.navigate(item.dRoute)
                                    title.value = item.dTitle
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {

        val bottomBar: @Composable () -> Unit = {
            if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
                NavigationBar {
                    screensInBottomBar.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.bRoute,
                            onClick = {
                                title.value = item.bTitle
                                controller.navigate(item.bRoute) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.bTitle
                                )
                            },
                            label = { Text(text = item.bTitle) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        @Composable
        fun MoreBottomSheet(modifier: Modifier.Companion = Modifier) {
            Box(
                Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .clickable { /* TODO: navigate */ }) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = "Settings"
                        )

                        Text("Settings", fontSize = 20.sp, color = Color.White)
                    }
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .clickable { /* TODO: navigate */ }) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            painter = painterResource(id = R.drawable.baseline_share_24),
                            contentDescription = "Share"
                        )

                        Text("Share", fontSize = 20.sp, color = Color.White)
                    }
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .clickable { /* TODO: navigate */ }) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            painter = painterResource(id = R.drawable.baseline_help_24),
                            contentDescription = "Help"
                        )

                        Text("Help", fontSize = 20.sp, color = Color.White)
                    }
                }
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title.value) },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Account"
                            )
                        }

                        IconButton(
                            onClick = { showBottomSheet.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                    }
                )
            },
            bottomBar = bottomBar
        ) { paddingValues ->
            Navigation(controller, viewModel, paddingValues)
            AccountDialog(dialogOpen = dialogOpen)

            if (showBottomSheet.value) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet.value = false },
                    sheetState = bottomSheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        MoreBottomSheet()
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(color = background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(text = item.dTitle, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(
        navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.BottomScreen.Home.route) {
            HomeView()
        }

        composable(Screen.BottomScreen.Library.route) {
            LibraryView()
        }

        composable(Screen.BottomScreen.Browse.route) {
            BrowseView()
        }

        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }

        composable(Screen.DrawerScreen.Subscription.route) {
            SubscriptionView()
        }
    }
}