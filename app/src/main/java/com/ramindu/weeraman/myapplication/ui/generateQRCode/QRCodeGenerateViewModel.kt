package com.ramindu.weeraman.myapplication.ui.generateQRCode

import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.ramindu.weeraman.data.coroutines.CoroutinesDispatcherProvider
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.usecases.FileSaveUseCase
import com.ramindu.weeraman.myapplication.QR_CODE_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File


class QRCodeGenerateViewModel @ViewModelInject constructor(
    private val fileSaveUseCase: FileSaveUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    val generateStatusLiveData = MutableLiveData<Boolean>()
    val bitMapLiveData = MutableLiveData<Bitmap?>()
    val fileLiveData = MutableLiveData<Either<ErrorCode, File>>()

    fun generateQRCode(url: String) {
        viewModelScope.launch(coroutinesDispatcherProvider.io) {
            generateStatusLiveData.postValue(true)
            delay(1000)
            try {
                val url1 = "https://testmockapi-d23bc.web.app"
                val multiFormatWriter = MultiFormatWriter()
                val bitMatrix =
                    multiFormatWriter.encode(url1, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE)
                val barcodeEncoder = BarcodeEncoder()
                bitMapLiveData.postValue(barcodeEncoder.createBitmap(bitMatrix))
            } catch (e: Exception) {
                bitMapLiveData.postValue(null)
            } finally {
                generateStatusLiveData.postValue(false)
            }
        }
    }

    fun savePdfFile(document: PdfDocument) {
        viewModelScope.launch {
            generateStatusLiveData.postValue(true)
            fileLiveData.postValue(fileSaveUseCase.savePdfFile(document))
            generateStatusLiveData.postValue(false)
        }
    }

}