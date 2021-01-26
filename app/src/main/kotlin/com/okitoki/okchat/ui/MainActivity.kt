package com.okitoki.okchat.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.okitoki.okchat.R
import com.okitoki.okchat.ui.dialog.TestDialogFragment
import com.okitoki.okchat.ui.testui.TestLabMainFragment
import kotlinx.android.synthetic.main.activity_main.*

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
//            val bundle = Bundle()
//            bundle.putParcelable(EXTRA_NOTICE_SAVE, FavoriteNotice(it.num, it.title, it.link))
            val dialog: TestDialogFragment = TestDialogFragment.newInstance()
//            dialog.arguments = bundle
            supportFragmentManager.let { fragmentManager ->
                dialog.show(
                    fragmentManager,
                    TAG_DIALOG_EVENT
                )
            }
        }
    }
}
