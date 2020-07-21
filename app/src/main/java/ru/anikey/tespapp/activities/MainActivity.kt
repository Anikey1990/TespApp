package ru.anikey.tespapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.anikey.core_injector.FeatureInjector
import ru.anikey.tespapp.R
import ru.anikey.tespapp.app.App
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = SupportAppNavigator(this, R.id.mainContent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) FeatureInjector.getDirectionFeature(context = this)
            .featureDirectionStarter()
            .startFeature(router = App.INSTANCE.getRouter())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.getNavigationHolder().setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.getNavigationHolder().removeNavigator()
        super.onPause()
    }

}
