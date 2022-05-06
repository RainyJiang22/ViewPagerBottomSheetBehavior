package com.base.vpbs.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author jiangshiyu
 * @date 2022/5/2
 */
abstract class BaseSheetDialogFragment<V : ViewBinding, VM : AndroidViewModel> :
    AppCompatDialogFragment() {
    var binding: V? = null


    private var mBottomSheetBehavior: (CustomBottomSheetBehavior<*>.() -> Unit)? = null

    var behavior: CustomBottomSheetBehavior<*>? = null
    private var mDialogCreate: (CustomBottomSheetDialog.() -> Unit)? = null

    fun bottomSheetBehavior(behavior: (CustomBottomSheetBehavior<*>.() -> Unit)): BaseSheetDialogFragment<V, VM> {
        mBottomSheetBehavior = behavior
        return this
    }


    val viewModel: VM by lazy {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        val clazz = types[1] as Class<VM>
        ViewModelProvider(
            requireActivity().viewModelStore,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(clazz)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            onBundle(requireArguments())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setContentView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
        init(savedInstanceState)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return CustomBottomSheetDialog(requireContext(), theme)
    }

    open fun onVisible() {


    }


    open fun onHidden() {

    }

    override fun onResume() {
        super.onResume()
        if (isAdded && !isHidden) {
            onVisible()
        }
    }

    override fun onStart() {
        super.onStart()
        (((view?.parent as View?)?.layoutParams as CoordinatorLayout.LayoutParams).behavior as CustomBottomSheetBehavior<View>?)?.let {
            mBottomSheetBehavior?.invoke(it)
            behavior = it
        }
    }


    override fun onPause() {
        if (isVisible) {
            onHidden()
        }
        super.onPause()
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onHidden()
        }
    }


    private fun setContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        if (view == null) {
            val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
            val aClass = types[0] as Class<V>
            try {
                binding =
                    aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
                        .invoke(null, getLayoutInflater()) as V?
                return binding?.root
            } catch (e: Error) {
                e.printStackTrace();
            }
        }
        return null
    }


    abstract fun onBundle(bundle: Bundle)

    abstract fun init(savedInstanceState: Bundle?)


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    fun binding(block: V?.() -> Unit) {
        block.invoke(binding)
    }


}