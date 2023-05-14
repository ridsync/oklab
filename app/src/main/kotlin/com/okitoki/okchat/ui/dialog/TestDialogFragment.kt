package com.okitoki.okchat.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.okitoki.okchat.R

/**
 * Created by okwon on 2021/01/12.
 */
class TestDialogFragment : DialogFragment() , View.OnClickListener{

    companion object {
        fun newInstance(): TestDialogFragment {
            return TestDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_test_fragment, container, false)
        return view
    }


    override fun onClick(p0: View?) {
        dismiss()
    }

}