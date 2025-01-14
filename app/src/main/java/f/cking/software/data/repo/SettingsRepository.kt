package f.cking.software.data.repo

import android.content.SharedPreferences
import f.cking.software.TheAppConfig

class SettingsRepository(
    private val sharedPreferences: SharedPreferences,
) {

    fun setScanInterval(scanInterval: Long) {
        sharedPreferences.edit().putLong(KEY_SCAN_INTERVAL, scanInterval).apply()
    }

    fun getScanInterval(): Long {
        return sharedPreferences.getLong(KEY_SCAN_INTERVAL, TheAppConfig.DEFAULT_SCAN_INTERVAL_MS)
    }

    fun setScanRestrictedInterval(scanInterval: Long) {
        sharedPreferences.edit().putLong(KEY_SCAN_RESTRICTED_INTERVAL, scanInterval).apply()
    }

    fun getScanRestrictedInterval(): Long {
        return sharedPreferences.getLong(KEY_SCAN_RESTRICTED_INTERVAL, TheAppConfig.RESTRICTED_MODE_SCAN_INTERVAL_MS)
    }

    fun setScanDuration(scanDuration: Long) {
        sharedPreferences.edit().putLong(KEY_SCAN_DURATION, scanDuration).apply()
    }

    fun getScanDuration(): Long {
        return sharedPreferences.getLong(KEY_SCAN_DURATION, TheAppConfig.DEFAULT_SCAN_DURATION_MS)
    }

    fun setKnownDevicePeriod(period: Long) {
        sharedPreferences.edit().putLong(KEY_KNOWN_PERIOD, period).apply()
    }

    fun getKnownDevicePeriod(): Long {
        return sharedPreferences.getLong(KEY_KNOWN_PERIOD, TheAppConfig.DEFAULT_KNOWN_DEVICE_PERIOD_MS)
    }

    fun setWantedDevicePeriod(period: Long) {
        sharedPreferences.edit().putLong(KEY_WANTED_PERIOD, period).apply()
    }

    fun getWantedDevicePeriod(): Long {
        return sharedPreferences.getLong(KEY_WANTED_PERIOD, TheAppConfig.DEFAULT_WANTED_PERIOD_MS)
    }

    fun setGarbagingTime(time: Long) {
        sharedPreferences.edit().putLong(KEY_GARBAGING_TIME, time).apply()
    }

    fun getGarbagingTIme(): Long {
        return sharedPreferences.getLong(KEY_GARBAGING_TIME, TheAppConfig.DEVICE_GARBAGING_TIME)
    }

    companion object {
        private const val KEY_SCAN_INTERVAL = "key_scan_interval"
        private const val KEY_SCAN_RESTRICTED_INTERVAL = "key_scan_restricted_interval"
        private const val KEY_SCAN_DURATION = "key_scan_duration"
        private const val KEY_KNOWN_PERIOD = "key_known_period"
        private const val KEY_WANTED_PERIOD = "key_wanted_period"
        private const val KEY_GARBAGING_TIME = "key_garbaging_time"
    }
}