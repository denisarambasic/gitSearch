package com.algebra.mobiletaskmvvm

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity


class Browser {
    companion object {
        fun open (context: Context, url : String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(context, browserIntent, null)
        }
    }
}