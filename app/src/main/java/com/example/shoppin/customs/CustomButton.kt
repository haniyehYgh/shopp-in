package com.example.shoppin.customs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.shoppin.R
import com.example.shoppin.databinding.CustomButtonBinding

class CustomButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private lateinit var binding: CustomButtonBinding
    private lateinit  var _context:Context

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CustomButton, 0, 0)
            .apply {
                try {
                    initialize(context)
                    setTitleText(getString(R.styleable.CustomButton_customButtonTitle) ?: "")
                    setTypeBackgroundButton(getInt(R.styleable.CustomButton_typeButton,0))

                } finally {
                    recycle()
                }
            }

    }

    private fun initialize(context: Context?) {

        val inflater = LayoutInflater.from(context)
        if (context != null) {
            _context=context
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.custom_button, this, true)

    }

    fun setTitleText(text: String?) {
        binding.customButton.text = text
    }

    fun setTypeBackgroundButton(type:Int){
        when(type){
            0->{
                binding.customButton.setTextColor(_context.getColor(R.color.white))
                binding.customButtonLayout.setBackgroundResource(R.drawable.bg_button_normal)

            }
            1->{
                binding.customButton.setEnabled(false)
                binding.customButton.setTextColor(_context.getColor(R.color.purple_200))
                binding.customButtonLayout.setBackgroundResource(R.drawable.bg_button_blue_deactive)

            }
        }

    }

    fun setStateButton(state:Boolean){
        binding.customButton.isEnabled=state
        if (state){
            binding.customButton.setEnabled(true)
            binding.customButton.setTextColor(_context.getColor(R.color.purple_500))
            binding.customButtonLayout.setBackgroundResource(R.drawable.bg_button_blue_active)
        }else{
            hideLoadingButton()
            binding.customButton.setEnabled(false)
            binding.customButton.setTextColor(_context.getColor(R.color.purple_200))
            binding.customButtonLayout.setBackgroundResource(R.drawable.bg_button_blue_deactive)
        }

    }

    fun setOnClickListener(listener: () -> Unit) {
        binding.customButton.setOnClickListener {
            listener.invoke()
        }
    }

    fun showLoadingButton(){
        binding.customButton.visibility= GONE
        binding.progressBar.visibility= VISIBLE

    }

    fun hideLoadingButton(){
        binding.customButton.visibility= VISIBLE
        binding.progressBar.visibility= GONE

    }



}