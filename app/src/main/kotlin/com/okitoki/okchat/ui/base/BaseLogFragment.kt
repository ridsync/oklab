package com.okitoki.okchat.ui.base

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by okc on 2021-01-22.
 */
open class BaseLogFragment : Fragment(){

    /**
     *  Fragment Lifecycle Start
     */
    override fun onAttach(context: Context) {
        Logger.i("[LifeCycle] onAttach context $context")
        super.onAttach(context)
    }

    override fun onAttach(activity: Activity) {
        Logger.i("[LifeCycle] onAttach activity $activity")
        super.onAttach(activity)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        Logger.i("[LifeCycle] onAttachFragment $childFragment")
        super.onAttachFragment(childFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.i("[LifeCycle] onCreate $savedInstanceState")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.i("[LifeCycle] onCreateView ")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Logger.i("[LifeCycle] onViewCreated $savedInstanceState")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Logger.i("[LifeCycle] onActivityCreated $savedInstanceState")
        super.onActivityCreated(savedInstanceState)
    }

    // Restore
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Logger.i("[LifeCycle] onViewStateRestored $savedInstanceState")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Logger.i("[LifeCycle] onStart ")
        super.onStart()
    }

    override fun onResume() {
        Logger.i("[LifeCycle] onResume ")
        super.onResume()
    }

    override fun onPause() {
        Logger.i("[LifeCycle] onPause ")
        super.onPause()
    }

    override fun onStop() {
        Logger.i("[LifeCycle] onStop ")
        super.onStop()
    }

    override fun onDestroyView() {
        Logger.i("[LifeCycle] onDestroyView ")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Logger.i("[LifeCycle] onDestroy ")
        super.onDestroy()
    }

    override fun onDetach() {
        Logger.i("[LifeCycle] onDetach ")
        super.onDetach()
    }

    /**
     *  Fragment Lifecycle Finish
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Logger.i("[LifeCycle] onActivityResult $requestCode $resultCode $data")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Logger.i("[LifeCycle] onHiddenChanged  - $hidden")
        super.onHiddenChanged(hidden)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Logger.i("[LifeCycle] onRequestPermissionsResult  - $requestCode $permissions $grantResults")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        Logger.i("[LifeCycle] onCreateContextMenu  - $menu")
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        Logger.i("[LifeCycle] onContextItemSelected  - $item")
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Logger.i("[LifeCycle] onCreateOptionsMenu  - $menu")
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Logger.i("[LifeCycle] onOptionsItemSelected  - $item")
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Logger.i("[LifeCycle] onPrepareOptionsMenu  - $menu")
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        Logger.i("[LifeCycle] onOptionsMenuClosed  - $menu")
        super.onOptionsMenuClosed(menu)
    }

    override fun onDestroyOptionsMenu() {
        Logger.i("[LifeCycle] onDestroyOptionsMenu ")
        super.onDestroyOptionsMenu()
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        Logger.i("[LifeCycle] onCreate $transit")
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        Logger.i("[LifeCycle] onCreate $transit")
        return super.onCreateAnimator(transit, enter, nextAnim)
    }

    override fun onLowMemory() {
        Logger.i("[LifeCycle] onLowMemory  -")
        super.onLowMemory()
    }


}