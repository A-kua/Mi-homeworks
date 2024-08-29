package fan.akua.day11.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import fan.akua.day11.R


class MineFragment : Fragment(R.layout.fragment_mine) {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        webView.setWebViewClient(WebViewClient())
        webView.getSettings().javaScriptEnabled = true
        webView.loadUrl("http://blog.akua.fan")
    }

    override fun onDestroyView() {
        webView.destroy()
        super.onDestroyView()
    }
}