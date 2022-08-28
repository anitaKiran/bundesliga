package com.kickbase.matches.ui.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kickbase.matches.data.model.MatchList
import com.kickbase.matches.databinding.SingleCompetitionItemBinding

/**
 * Created by Anita Kiran on 8/28/2022.
 */
class MatchAdapter() : ListAdapter<MatchList, MatchAdapter.MatchViewHolder>(DIFF_UTIL_MOVIES) {

    inner class MatchViewHolder(
        private val binding: SingleCompetitionItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(matchItem: MatchList) = with(binding) {
            // set name of the teams
            //t1Name.text = matchItem.t1.clubShortName
            //t2Name.text = matchItem.t2.clubShortName
            // set logo of teams 1
            //Log.e("Imageurl", BuildConfig.BASE_IMAGE_PATH +matchItem.t1.clubId +"/9")
            Glide.with(context).load("https://kickbase.b-cdn.net/testpool/teams_svg_v2/30.svg")
               .into(imgT1Logo)

//            SvgLoader.pluck()
//                .with(this)
//                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//                .load(uri, image);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            SingleCompetitionItemBinding.inflate(
                LayoutInflater.from(parent.context)), parent.context)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val DIFF_UTIL_MOVIES = object : DiffUtil.ItemCallback<MatchList>() {
    override fun areItemsTheSame(oldItem: MatchList, newItem: MatchList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MatchList, newItem: MatchList): Boolean {
        return oldItem  == newItem
    }
}