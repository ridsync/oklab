package com.okitoki.okchat.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.okitoki.okchat.R
import com.okitoki.okchat.ui.dialog.TestDialogFragment
import com.okitoki.okchat.ui.testui.TestLabMainFragment
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_DIALOG_EVENT = "TAG_DIALOG_EVENT"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frTransaction = supportFragmentManager.beginTransaction()
        frTransaction.add(R.id.fragment_container,TestLabMainFragment())
        frTransaction.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        findViewById<Button>(R.id.mrbButton).setOnClickListener {
            val intent = Intent(applicationContext, FontStyleTestActivity::class.java)
            startActivity(intent)

            Handler().postDelayed( {
                Logger.d("TestDialogFragment start")
//            bundle.putParcelable(EXTRA_NOTICE_SAVE, FavoriteNotice(it.num, it.title, it.link))
                val dialog: TestDialogFragment = TestDialogFragment.newInstance()
//            dialog.arguments = bundle
                supportFragmentManager.let { fragmentManager ->
                    dialog.show(
                        fragmentManager,
                        TAG_DIALOG_EVENT
                    )
                }
            } , 2000)
            //            val bundle = Bundle()


        }
    }
}
