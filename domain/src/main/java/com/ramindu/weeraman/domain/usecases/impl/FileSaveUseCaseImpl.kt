package com.ramindu.weeraman.domain.usecases.impl


import android.graphics.pdf.PdfDocument
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ramindu.weeraman.domain.PDF_SAVE_ERROR
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.domain.usecases.FileSaveUseCase
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject

class FileSaveUseCaseImpl @Inject constructor(private val externalFileDirectory: File?) : FileSaveUseCase {

    override suspend fun savePdfFile(document: PdfDocument): Either<ErrorCode, File> {
        if (externalFileDirectory == null) {
            return ErrorCode(PDF_SAVE_ERROR).left()
        }
        val pdfFile = File(externalFileDirectory, generateFileName())
        delay(1000)
        try {
            val os = FileOutputStream(pdfFile)
            document.writeTo(os)
            document.close()
            os.close()
        } catch (e: Exception) {
            return ErrorCode(PDF_SAVE_ERROR).left()
        }
        return pdfFile.right()
    }

    private fun generateFileName(): String {
        val id = Random().nextInt(1000).toString()
        return "QR_code_$id.pdf"
    }
}