package com.fajarsn.coronavirus.onboarding.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fajarsn.coronavirus.databinding.LayoutContainerOnboardingBinding

class OnBoardingAdapter(private val list: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = LayoutContainerOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val onBoardingItem = list[position]
        holder.binding.ivOnBoardingImage.setImageResource(onBoardingItem.image)
        holder.binding.tvOnBoardingHeading.text = onBoardingItem.title
    }

    override fun getItemCount() = list.size

    class OnBoardingViewHolder(var binding: LayoutContainerOnboardingBinding) :
        ViewHolder(binding.root)
}