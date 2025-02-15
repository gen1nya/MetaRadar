package f.cking.software.domain.interactor

import org.koin.dsl.module

object InteractorsModule {

    val module = module {
        factory { AnalyseScanBatchInteractor(get(), get()) }
        factory { ClearGarbageInteractor(get(), get(), get()) }
        factory { GetKnownDevicesCountInteractor(get(), get()) }
        factory { GetKnownDevicesInteractor(get(), get()) }
        factory { IsKnownDeviceInteractor(get()) }
        factory { SaveScanBatchInteractor(get(), get()) }
        factory { GetBleRecordFramesFromRawInteractor() }
        factory { GetManufacturerInfoFromRawBleInteractor(get(), get()) }
        factory { CheckProfileDetectionInteractor(get(), get(), get()) }
        factory { BuildDeviceFromScanDataInteractor(get()) }
        factory { GetAirdropInfoFromBleFrame() }
    }
}