package com.example.myapplication

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(wrapContextLocale(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 24) {
            language.text = resources.configuration.locales.get(0).displayLanguage
            country.text = resources.configuration.locales.get(0).displayCountry
        } else {
            language.text = resources.configuration.locale.displayLanguage
            country.text = resources.configuration.locale.displayCountry
        }
    }

    private fun wrapContextLocale(context: Context?): Context? {
        var ctx = context
        val locale = Locale("es", "ar")
        Locale.setDefault(locale)

        val res = ctx?.resources
        val config = Configuration(res?.configuration)

        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale)
            ctx = context?.createConfigurationContext(config)
        } else {
            config.locale = locale
            res?.updateConfiguration(config, res.displayMetrics)
        }

        return ctx
    }
}
