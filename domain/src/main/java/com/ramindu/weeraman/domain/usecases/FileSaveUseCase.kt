package com.ramindu.weeraman.domain.usecases

import android.graphics.pdf.PdfDocument
import arrow.core.Either
import com.ramindu.weeraman.domain.entities.ErrorCode
import java.io.File

interface FileSaveUseCase {

    suspend fun savePdfFile(document: PdfDocument): Either<ErrorCode, File>
}