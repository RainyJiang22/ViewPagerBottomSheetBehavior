package com.base.vpbs

import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

/**
 * @author jiangshiyu
 * @date 2022/4/29
 */
fun Fragment.replaceFragment(replaceFragment: Fragment, tag: String, id: Int) {
    var tempFragment = childFragmentManager.findFragmentByTag(tag)
    val transaction = childFragmentManager.beginTransaction()
    if (tempFragment == null) {
        try {
            tempFragment = replaceFragment.apply {
                enterTransition = createTransition()
            }
            transaction
                .add(id, tempFragment, tag)
                .setMaxLifecycle(tempFragment, Lifecycle.State.RESUMED)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    val fragments = childFragmentManager.fragments

    for (i in fragments.indices) {
        val fragment = fragments[i]
        if (fragment.tag == tag) {
            transaction
                .show(fragment)
        } else {
            transaction
                .hide(fragment)
        }
    }
    transaction.commitAllowingStateLoss()
}

private fun createTransition(): androidx.transition.TransitionSet {
    val transitionSet = androidx.transition.TransitionSet()
    transitionSet.interpolator = LinearInterpolator()
    transitionSet.duration = 300
    transitionSet.addTransition(androidx.transition.Fade())
    return transitionSet
}
