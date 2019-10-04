package com.compileconnected.adapter

abstract class AbsSimpleViewHolderImpl<T : ViewHolderResource>(private val layoutRes: List<Pair<Int, Int>>) :
    SimpleRecyclerViewAdapter.AbsSimpleViewHolder<T> {
    override fun getItemViewType(viewType: Int): Int = layoutRes[viewType].first
    override fun getLayoutRes(viewType: Int): Int = layoutRes[viewType].second

    abstract override fun onBindView(holder: SimpleViewHolder, item: T)
}

typealias MultipleViewHolder<T> = AbsSimpleViewHolderImpl<T>