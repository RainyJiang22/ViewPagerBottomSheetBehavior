package com.base.vpbs.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.custom_bottomsheet.CallBackScrollChild
import com.base.vpbs.NumberAdapter
import com.base.vpbs.base.BaseFragment
import com.base.vpbs.base.EmptyViewModel
import com.base.vpbs.databinding.FragmentFirstBinding
import com.base.vpbs.setData

/**
 * @author jiangshiyu
 * @date 2022/5/2
 */
class FirstFragment : BaseFragment<FragmentFirstBinding, EmptyViewModel>(){


    var callBackScrollChild: CallBackScrollChild? = null

    override fun onBundle(bundle: Bundle) {

    }

    override fun init(savedInstanceState: Bundle?) {

        binding?.rvFirst?.let {
            it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.adapter = NumberAdapter(setData().toMutableList())
        }
    }

    override fun onVisible() {
        super.onVisible()
        binding?.rvFirst?.let { callBackScrollChild?.backScrollChild(it) }
    }


}