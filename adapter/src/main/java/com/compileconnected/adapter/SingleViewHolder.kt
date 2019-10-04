package com.compileconnected.adapter

abstract class SingleViewHolder<T : ViewHolderResource>(type: Int, layoutRes: Int) :
    AbsSimpleViewHolderImpl<T>(listOf(Pair(type, layoutRes)))