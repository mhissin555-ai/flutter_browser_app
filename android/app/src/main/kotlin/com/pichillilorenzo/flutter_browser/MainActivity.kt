package com.pichillilorenzo.flutter_browser

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    private var url: String? = null
    private val CHANNEL = "com.pichillilorenzo.flutter_browser.intent_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var url: String? = null
        val action = intent.action

        if (Intent.ACTION_VIEW == action) {
            val data: Uri? = intent.data
            if (data != null) url = data.toString()
        } else if (Intent.ACTION_SEARCH == action ||
            MediaStore.INTENT_ACTION_MEDIA_SEARCH == action ||
            Intent.ACTION_WEB_SEARCH == action
        ) {
            url = intent.getStringExtra(SearchManager.QUERY)
        }

        this.url = url
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                if (call.method == "getIntentData") {
                    result.success(url)
                    this.url = null
                }
            }
    }
}