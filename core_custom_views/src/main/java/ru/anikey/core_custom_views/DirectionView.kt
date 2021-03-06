package ru.anikey.core_custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.layout_view_direction.view.*

class DirectionView : CardView {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        initAttrs(attrs)
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_view_direction, this)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DirectionView)

        setTerminalName(value = typedArray.getString(R.styleable.DirectionView_terminal_name))
        setTerminalAddress(value = typedArray.getString(R.styleable.DirectionView_terminal_address))
        setEnabled(value = typedArray.getBoolean(R.styleable.DirectionView_enabled, true))

        typedArray.recycle()
    }

    fun setTerminalName(value: String?) {
        title.text = value
    }

    fun setTerminalAddress(value: String?) {
        value?.let {
            description.apply {
                text = it
                visibility = View.VISIBLE
            }
        }
    }

    fun setEnabled(value: Boolean?) {
        value?.let {
            isClickable = it
            alpha = if (it) 1f else 0.5f
        }
    }

}
