package f.cking.software.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import f.cking.software.R
import f.cking.software.TheApp

object MainScreen {

    @Composable
    fun Screen(viewModel: MainViewModel) {
        MaterialTheme(
            colors = MaterialTheme.colors.copy(
                primary = colorResource(id = R.color.orange_500),
                primaryVariant = colorResource(id = R.color.orange_700),
                onPrimary = Color.White,
                secondary = Color.Black,
                secondaryVariant = Color.Black,
                onSecondary = Color.White,
            )
        ) {
            Scaffold(
                topBar = {
                    TopBar(viewModel)
                },
                content = {
                    viewModel.currentTab.screen()
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    ScanFab(viewModel)
                },
                bottomBar = {
                    BottomNavigationBar(viewModel)
                }
            )
        }
    }

    @Composable
    fun BottomNavigationBar(viewModel: MainViewModel) {
        BottomAppBar {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TabButton(
                    targetTab = MainViewModel.Tab.DEVICE_LIST,
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.width(16.dp))
                TabButton(targetTab = MainViewModel.Tab.SETTINGS, modifier = Modifier.weight(1f), viewModel = viewModel)
            }
        }
    }

    @Composable
    private fun TabButton(
        viewModel: MainViewModel,
        targetTab: MainViewModel.Tab,
        modifier: Modifier = Modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .clickable { viewModel.currentTab = targetTab }
        ) {
            Image(
                painter = painterResource(id = targetTab.iconRes),
                contentDescription = targetTab.text,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = targetTab.text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }

    @Composable
    fun ScanFab(viewModel: MainViewModel) {
        val text: String
        val icon: Int

        if (TheApp.instance.backgroundScannerIsActive) {
            text = "Stop"
            icon = R.drawable.ic_cancel
        } else {
            text = "Scan"
            icon = R.drawable.ic_ble
        }

        ExtendedFloatingActionButton(
            text = { Text(text = text, fontWeight = FontWeight.Bold) },
            onClick = { viewModel.runBackgroundScanning() },
            icon = {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }
        )
    }

    @Composable
    fun TopBar(viewModel: MainViewModel) {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            },
            actions = {
                if (viewModel.scanStarted) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                } else {
                    IconButton(onClick = { viewModel.onScanButtonClick() }) {
                        Image(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "refresh",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }
        )
    }
}