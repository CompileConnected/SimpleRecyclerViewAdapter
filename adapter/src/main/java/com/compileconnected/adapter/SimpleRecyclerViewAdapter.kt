package com.compileconnected.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.compileconnected.adapter.ViewHolderResource as resource


open class SimpleViewHolder constructor(view: View) : RecyclerView.ViewHolder(view)

class SimpleRecyclerViewAdapter<out TYPE : resource> private constructor(
    private val listItem: ArrayList<TYPE>,
    private val absViewHolder: AbsSimpleViewHolder<TYPE>
) : RecyclerView.Adapter<SimpleViewHolder>() {
    companion object {
        @JvmStatic
        fun <T : resource> single(
            listItem: ArrayList<T>,
            singleViewHolder: SingleViewHolder<T>
        ): SimpleRecyclerViewAdapter<T> {
            val temp = ArrayList<T>(listItem.size)
            listItem.map { temp.add(it) }
            return SimpleRecyclerViewAdapter(temp, singleViewHolder)
        }

        fun <T : resource> multiple(
            listItem: ArrayList<T>,
            listOfViewType: MultipleViewHolder<T>
        ): SimpleRecyclerViewAdapter<T> {
            return SimpleRecyclerViewAdapter(listItem, listOfViewType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(absViewHolder.getLayoutRes(viewType), parent, false)
        return SimpleViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return listItem[position].getViewType()
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        absViewHolder.onBindView(holder, listItem[position])
    }

    interface AbsSimpleViewHolder<T> {
        fun getItemViewType(viewType: Int): Int
        fun getLayoutRes(viewType: Int): Int
        fun onBindView(holder: SimpleViewHolder, item: T)
    }
}