package su.app.presentation.ui.settings

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.settings_fragment.*
import su.app.R
import su.app.presentation.MainActivity
import java.util.*

class SettingsFragment: DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.settings_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        change_language_button.setOnClickListener {
            val languageItems = activity?.resources?.getStringArray(R.array.languages)
            val dialogBuilder = AlertDialog.Builder(activity)
            var languageList = arrayOf("kz","ru")
            dialogBuilder.setTitle("Выберите язык")
            dialogBuilder.setSingleChoiceItems(languageItems,0){dialogInterface, i ->
                when(i){
                    1 -> setLocale("kz")
                    2 -> setLocale("ru")
                }
                dialogInterface.dismiss()
            }
            val mDialog = dialogBuilder.create()
            mDialog.show()
        }
    }

    fun setLocale(lang: String){
        val locale  = Locale(lang)
        Locale.setDefault(locale)
        val dm = context!!.resources.displayMetrics
        var config = context!!.resources.configuration
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(Locale(lang))
        } else {
            config.locale = Locale(lang)
        }
        resources.updateConfiguration(config, dm)
        println("----------LangChanged ${lang}")
    }

}