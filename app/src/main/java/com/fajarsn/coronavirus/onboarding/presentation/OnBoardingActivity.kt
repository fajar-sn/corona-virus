package com.fajarsn.coronavirus.onboarding.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.fajarsn.coronavirus.R
import com.fajarsn.coronavirus.databinding.ActivityOnboardingBinding
import com.fajarsn.coronavirus.onboarding.domain.OnBoardingAdapter
import com.fajarsn.coronavirus.onboarding.domain.OnBoardingItem
import com.google.android.material.button.MaterialButton

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var layoutOnBoardingIndicator: LinearLayout
    private lateinit var onBoardingActionButton: MaterialButton

    @SuppressLint("AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        layoutOnBoardingIndicator = binding.layoutOnBoardingIndicators
        onBoardingActionButton = binding.btnOnBoardingAction
        setOnBoardingItem()
        binding.vp2OnBoarding.adapter = onBoardingAdapter
        setOnBoardingIndicator()
        setCurrentOnBoardingIndicator(0)

        binding.vp2OnBoarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingIndicator(position)
            }
        })

        onBoardingActionButton.setOnClickListener {
            if (binding.vp2OnBoarding.currentItem + 1 < onBoardingAdapter.itemCount) {
                binding.vp2OnBoarding.currentItem++
            } else {
//                start new activity here
            }
        }
    }

    private fun setOnBoardingItem() {
        val onBoardingList = listOf(
            OnBoardingItem(R.drawable.img_onboarding_1, getString(R.string.on_boarding_title_1)),
            OnBoardingItem(R.drawable.img_onboarding_2, getString(R.string.on_boarding_title_2)),
            OnBoardingItem(R.drawable.img_onboarding_3, getString(R.string.on_boarding_title_3)),
        )

        onBoardingAdapter = OnBoardingAdapter(onBoardingList)
    }

    private fun setOnBoardingIndicator() {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 1..onBoardingAdapter.itemCount) {
            val imageView = ImageView(applicationContext)
            imageView.setImageResource(R.drawable.ic_onboarding_indicator_inactive)
            imageView.layoutParams = layoutParams
            layoutOnBoardingIndicator.addView(imageView)
        }
    }

    private fun setCurrentOnBoardingIndicator(index: Int) {
        val childCount = layoutOnBoardingIndicator.childCount

        for (i in 0 until childCount) {
            val imageView = layoutOnBoardingIndicator.getChildAt(i) as ImageView

            if (i == index) {
                imageView.setImageResource(R.drawable.ic_onboarding_indicator_active)
            } else {
                imageView.setImageResource(R.drawable.ic_onboarding_indicator_inactive)
            }

            if (index == onBoardingAdapter.itemCount - 1) {
                onBoardingActionButton.text = getString(R.string.login_with_gmail)
            } else {
                onBoardingActionButton.text = getString(R.string.next)
            }
        }
    }
}