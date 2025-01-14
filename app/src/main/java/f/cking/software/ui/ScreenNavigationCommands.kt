package f.cking.software.ui

import f.cking.software.common.navigation.AddToStackCommand
import f.cking.software.common.navigation.DialogCommand
import f.cking.software.common.rememberDateDialog
import f.cking.software.common.rememberTimeDialog
import f.cking.software.domain.model.DeviceData
import f.cking.software.domain.model.ManufacturerInfo
import f.cking.software.ui.main.MainScreen
import f.cking.software.ui.profiledetails.ProfileDetailsScreen
import f.cking.software.ui.selectdevice.SelectDeviceScreen
import f.cking.software.ui.selectfiltertype.FilterType
import f.cking.software.ui.selectfiltertype.SelectFilterTypeScreen
import f.cking.software.ui.selectmanufacturer.SelectManufacturerScreen
import java.time.LocalDate
import java.time.LocalTime

object ScreenNavigationCommands {

    object OpenMainScreen : AddToStackCommand(screenFunction = { MainScreen.Screen() })

    class OpenProfileScreen(private val profileId: Int?) : AddToStackCommand(screenFunction = {
        ProfileDetailsScreen.Screen(profileId = profileId)
    })

    class OpenSelectFilterTypeScreen(
        onSelected: (type: FilterType) -> Unit
    ) : AddToStackCommand(screenFunction = {
        SelectFilterTypeScreen.Screen(onSelected = onSelected)
    })

    class OpenSelectManufacturerScreen(
        onSelected: (manufacturerInfo: ManufacturerInfo) -> Unit
    ) : AddToStackCommand(screenFunction = {
        SelectManufacturerScreen.Screen(onSelected = onSelected)
    })

    class OpenSelectDeviceScreen(
        onSelected: (device: DeviceData) -> Unit
    ) : AddToStackCommand(screenFunction = {
        SelectDeviceScreen.Screen(onSelected = onSelected)
    })

    class OpenDatePickerDialog(
        initialDate: LocalDate = LocalDate.now(),
        onSelected: (localDate: LocalDate) -> Unit
    ) : DialogCommand(dialogProvider = {
        rememberDateDialog(initialDate = initialDate, dateResult = onSelected, it)
    })

    class OpenTimePickerDialog(
        initialTime: LocalTime = LocalTime.now(),
        onSelected: (localDate: LocalTime) -> Unit
    ) : DialogCommand(dialogProvider = {
        rememberTimeDialog(initialTime = initialTime, dateResult = onSelected, it)
    })
}