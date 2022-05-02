package com.base.vpbs

import android.os.Bundle
import android.view.View
import com.base.vpbs.base.BaseViewPagerSheetDialogFragment
import com.base.vpbs.base.EmptyViewModel
import com.base.vpbs.base.ScreenUtil
import com.base.vpbs.databinding.FragmentContentBinding
import com.base.vpbs.fragment.FirstFragment
import com.base.vpbs.fragment.SecondFragment
import com.base.vpbs.fragment.ThirdFragment
import com.google.android.material.tabs.TabLayout

/**
 * @author jiangshiyu
 * @date 2022/5/2
 */
class ContentSheetFragment :
    BaseViewPagerSheetDialogFragment<FragmentContentBinding, EmptyViewModel>(),
    CallBackScrollChild {


    private val fragments by lazy {
        arrayOf(
            FirstFragment().apply {
                this.callBackScrollChild = this@ContentSheetFragment
            }, SecondFragment().apply {
                this.callBackScrollChild = this@ContentSheetFragment
            }, ThirdFragment().apply {
                this.callBackScrollChild = this@ContentSheetFragment
            }
        )
    }

    override fun onBundle(bundle: Bundle) {
    }

    override fun init(savedInstanceState: Bundle?) {
        bottomSheetBehavior {
            this.peekHeight = ScreenUtil.dip2px(context, 420f)
            binding?.contentLayout?.layoutParams?.height = ScreenUtil.getScreenHeight(context)
        }

        binding?.tabLayout?.getTabAt(0)?.select()
        replaceFragment(fragments[0], "first")

        binding?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        replaceFragment(fragments[0], "first")
                    }
                    1 -> {
                        replaceFragment(fragments[1], "second")
                    }
                    2 -> {
                        replaceFragment(fragments[2], "third")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    override fun backScrollChild(scrollChild: View) {
        behavior?.invalidateScrollingChild(scrollChild)
    }
}