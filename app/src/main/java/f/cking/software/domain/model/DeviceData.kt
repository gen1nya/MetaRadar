package f.cking.software.domain.model

import f.cking.software.getTimePeriodStr

data class DeviceData(
    val address: String,
    val name: String?,
    val lastDetectTimeMs: Long,
    val firstDetectTimeMs: Long,
    val manufacturerInfo: ManufacturerInfo?,
    val detectCount: Int,
    val customName: String?,
    val favorite: Boolean,
) {

    fun buildDisplayName(): String {
        return if (!customName.isNullOrBlank()) customName else name ?: address
    }

    fun firstDetectionPeriod(): String {
        return getTimePeriodStr(System.currentTimeMillis() - firstDetectTimeMs)
    }

    fun lastDetectionPeriod(): String {
        return getTimePeriodStr(System.currentTimeMillis() - lastDetectTimeMs)
    }

    fun mergeWithNewDetected(new: DeviceData): DeviceData {
        return this.copy(
            detectCount = detectCount + 1,
            lastDetectTimeMs = new.lastDetectTimeMs,
            name = new.name,
            manufacturerInfo = new.manufacturerInfo,
        )
    }
}