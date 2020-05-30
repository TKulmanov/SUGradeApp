package su.app.presentation.ui.umkd.file.detail

import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import su.app.R
import su.app.databinding.FileDetailsFragmentBinding

class FileDetailFragment : Fragment() {

    private lateinit var binding: FileDetailsFragmentBinding

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.file_details_fragment, container, false)

        webView = binding.fileWebView
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val args = arguments?.let{ FileDetailFragmentArgs.fromBundle(it)}
        val fileId = args?.file?.fileId


        initWebView()
        setWebClient()
//        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=https://suimgserver/files?fileId=${fileId}")
        webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=https://drive.google.com/file/d/1NAUJ_7X7kpeGUXc8NlecdGowoQSU7r06/view?usp=sharing")
    }

    private fun initWebView(){
        val webViewSettings = webView.settings

        webViewSettings.javaScriptEnabled = true
        webViewSettings.allowFileAccess = true
        webViewSettings.loadWithOverviewMode = true
        webViewSettings.useWideViewPort = true
        webViewSettings.domStorageEnabled = true
    }

    private fun setWebClient(){
        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }
        }
    }
}