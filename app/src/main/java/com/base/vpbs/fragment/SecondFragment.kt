package com.base.vpbs.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.vpbs.NumberAdapter
import com.base.vpbs.base.BaseFragment
import com.base.vpbs.base.EmptyViewModel
import com.base.vpbs.databinding.FragmentSecondBinding
import com.base.vpbs.setData

/**
 * @author jiangshiyu
 * @date 2022/5/2
 */
class SecondFragment : BaseFragment<FragmentSecondBinding,EmptyViewModel>() {
    override fun onBundle(bundle: Bundle) {

    }

    override fun init(savedInstanceState: Bundle?) {

        binding?.rvSecond?.let {
            it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.adapter = NumberAdapter(setData().toMutableList())
        }
    }
}