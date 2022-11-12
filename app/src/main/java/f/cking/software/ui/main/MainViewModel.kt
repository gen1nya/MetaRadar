package f.cking.software.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import f.cking.software.R
import f.cking.software.TheApp
import f.cking.software.domain.helpers.BleScannerHelper
import f.cking.software.domain.helpers.PermissionHelper
import f.cking.software.service.BgScanService
import f.cking.software.ui.devicelist.DeviceListScreen
import f.cking.software.ui.settings.SettingsScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val permissionHelper: PermissionHelper,
    private val bleScanner: BleScannerHelper,
) : ViewModel(), BleScannerHelper.ProgressListener {

    var scanStarted by mutableStateOf(false)
    var currentTab by mutableStateOf(Tab.DEVICE_LIST)

    init {
        bleScanner.addProgressListener(this)
    }

    override fun onScanProgressChanged(inProgress: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            scanStarted = inProgress
        }
    }

    fun onScanButtonClick() {
        checkPermissions {
            BgScanService.scan(TheApp.instance)
        }
    }

    fun runBackgroundScanning() {
        checkPermissions {
            permissionHelper.checkDozeModePermission()
            if (TheApp.instance.backgroundScannerIsActive) {
                BgScanService.stop(TheApp.instance)
            } else {
                BgScanService.start(TheApp.instance)
            }
        }
    }

    private fun checkPermissions(granted: () -> Unit) {
        permissionHelper.checkBlePermissions {
            permissionHelper.checkBlePermissions(permissions = PermissionHelper.BACKGROUND_LOCATION) {
                permissionHelper.checkDozeModePermission()
                granted.invoke()
            }
        }
    }

    enum class Tab(
        @DrawableRes val iconRes: Int,
        val text: String,
        val screen: @Composable () -> Unit
    ) {
        DEVICE_LIST(R.drawable.ic_list, "Device list", { DeviceListScreen.Screen() }),
        SETTINGS(R.drawable.ic_settings, "Settings", { SettingsScreen.Screen() }),
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                MainViewModel(
                    permissionHelper = TheApp.instance.permissionHelper,
                    bleScanner = TheApp.instance.bleScannerHelper
                )
            }
        }
    }
}