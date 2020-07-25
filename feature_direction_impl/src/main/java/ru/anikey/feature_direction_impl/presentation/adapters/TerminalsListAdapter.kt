package ru.anikey.feature_direction_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_terminals_list.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import javax.inject.Inject

class TerminalsListAdapter @Inject constructor() :
    RecyclerView.Adapter<TerminalsListAdapter.TerminalsListVH>() {

    private val mTerminals = mutableListOf<TerminalUIModel>()
    private val mTempList = mutableListOf<TerminalUIModel>()
    private var mClickListener: OnTerminalClickListener? = null

    fun fetchData(terminals: List<TerminalUIModel>) {
        mTerminals.clear()
        mTerminals.addAll(terminals)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onTerminalClickListener: OnTerminalClickListener) {
        mClickListener = onTerminalClickListener
    }

    fun filterList(query: String?) {
        if (mTempList.isEmpty()) mTempList.addAll(mTerminals)

        if (!query.isNullOrEmpty()) {
            mTerminals.clear()

            mTerminals.addAll(mTempList.filter {
                it.name.startsWith(prefix = query, ignoreCase = true) or
                        it.address.startsWith(prefix = query, ignoreCase = true)
            })

            notifyDataSetChanged()
        } else fetchData(mTempList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerminalsListVH =
        TerminalsListVH(
            view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_terminals_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mTerminals.count()

    override fun onBindViewHolder(holder: TerminalsListVH, position: Int) = holder
        .bind(position)

    inner class TerminalsListVH(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val terminal = mTerminals[position]

            view.apply {
                name.text = terminal.name
                address.text = terminal.address

                setOnClickListener { mClickListener?.onTerminalClicked(terminal) }
            }
        }
    }

    interface OnTerminalClickListener {
        fun onTerminalClicked(terminal: TerminalUIModel)
    }

}
