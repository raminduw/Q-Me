package com.ramindu.weeraman.myapplication.ui.generateQRCode

import android.content.Context.PRINT_SERVICE
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintAttributes.Resolution
import android.print.pdf.PrintedPdfDocument
import android.view.*
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ramindu.weeraman.domain.*
import com.ramindu.weeraman.domain.entities.ErrorCode
import com.ramindu.weeraman.myapplication.EVENT_CODE
import com.ramindu.weeraman.myapplication.PRINT_MESSAGE
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.utils.DialogHelperUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_generate_qrcode.*
import java.io.File
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class QRCodeGenerateFragment : Fragment() {

    private val ACTIVITY_RESULT = 123
    private val qrCodeGenerateViewModel: QRCodeGenerateViewModel by viewModels()
    @Inject
    lateinit var dialogHelperUtil: DialogHelperUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_generate_qrcode, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.text = arguments?.getString(PRINT_MESSAGE)
        qrCodeGenerateViewModel.generateQRCode(arguments?.getString(EVENT_CODE) ?: "")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnShare.setOnClickListener {
            generatePDF()
        }

        qrCodeGenerateViewModel.bitMapLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                imgViewQrCode.setImageBitmap(it)
                imgViewQrCode.visibility = VISIBLE
            } ?: run {
                imgViewQrCode.visibility = VISIBLE
                imgViewQrCode.setImageResource(R.drawable.baseline_error_outline_black_48dp)
            }
        })

        qrCodeGenerateViewModel.fileLiveData.observe(viewLifecycleOwner, Observer {
            it.fold(ifLeft = { error ->
                showToastMessage(error)
            }, ifRight = { file ->
                shareIntent(file)
            })
        })

        qrCodeGenerateViewModel.generateStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                dialogHelperUtil.showProgressBar(activity)
            } else {
                dialogHelperUtil.hideProgressBar()
            }
        })
    }

    private fun shareIntent(file: File) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, "QR code From Q-Me")
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            type = "application/pdf"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivityForResult(shareIntent, ACTIVITY_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_RESULT) {
            findNavController().navigate(R.id.action_QRCodeGenerateFragment_to_ServiceSelectionFragment)
        }
    }

    private fun generatePDF() {
        val printAttrs =
            PrintAttributes.Builder().setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4).setResolution(
                    Resolution(
                        "print", PRINT_SERVICE,
                        5, 5
                    )
                ).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build()
        val document: PdfDocument = PrintedPdfDocument(requireContext(), printAttrs)
        val pageInfo =
            PdfDocument.PageInfo.Builder(qrCodeLayout.width, qrCodeLayout.height, 1).create()
        val page = document.startPage(pageInfo)
        qrCodeLayout.draw(page.canvas)
        document.finishPage(page)
        qrCodeGenerateViewModel.savePdfFile(document)
    }

    private fun showToastMessage(errorCode: ErrorCode) {
        val message = when (errorCode.code) {
            PDF_SAVE_ERROR -> "PDF Save error"
            else -> "Error"
        }
        dialogHelperUtil.showToast(requireContext(), message)
    }
}


