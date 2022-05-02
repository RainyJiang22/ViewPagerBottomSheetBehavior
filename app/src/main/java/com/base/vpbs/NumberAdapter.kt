package com.base.vpbs

import androidx.appcompat.widget.AppCompatTextView
import com.base.vpbs.data.NumberData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author jiangshiyu
 * @date 2022/5/2
 */
class NumberAdapter(data: MutableList<NumberData>) :
    BaseQuickAdapter<NumberData, BaseViewHolder>(R.layout.item_number_data, data) {
    override fun convert(holder: BaseViewHolder, item: NumberData) {

        holder.getView<AppCompatTextView>(R.id.tv_number).apply {
            text = item.number
        }
    }
}