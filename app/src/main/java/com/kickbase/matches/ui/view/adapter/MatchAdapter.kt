package com.kickbase.matches.ui.view.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.kickbase.matches.BuildConfig
import com.kickbase.matches.R
import com.kickbase.matches.data.model.MatchList
import com.kickbase.matches.databinding.SingleCompetitionItemBinding
import com.kickbase.matches.utils.AppConstants
import com.kickbase.matches.utils.DateSettings

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
            t1Name.text = matchItem.t1.clubShortName
            t2Name.text = matchItem.t2.clubShortName
            // setting goals of teams
            t1Goals.text = matchItem.t1.goals.toString()
            t2Goals.text = matchItem.t2.goals.toString()

            // set logo of teams 1
            GlideToVectorYou
                .init()
                .with(context)
                .load(Uri.parse(BuildConfig.BASE_IMAGE_PATH +matchItem.t1.clubId +"/9"), imgT1Logo)

            // set logo of team 2
            GlideToVectorYou
                .init()
                .with(context)
                .load(Uri.parse(BuildConfig.BASE_IMAGE_PATH +matchItem.t2.clubId +"/9"), imgT2Logo)

            // set date and month
            tvDateMonth.text = DateSettings.getDateAndMonth(matchItem.dateOfMatch)

            // set status of matches
            // match status started and none -- show only date and time
            if(matchItem.statusOfMatch == AppConstants.MATCH_STATUS_NONE ||
                    matchItem.statusOfMatch == AppConstants.MATCH_STATUS_STARTED){
                // set time of match
                tvTime.text = DateSettings.getTimeOfMatch(matchItem.dateOfMatch)
            }

            // match status 'FINISHED'-- set finished in textview
            else if(matchItem.statusOfMatch == AppConstants.MATCH_STATUS_FINISHED ||
                matchItem.statusOfMatch == AppConstants.MATCH_STATUS_SECOND_HALF_STARTED){
                // set finished
                tvTime.setText(R.string.match_finished)
            }

            // match status 'Postponed'-- set postponed in textview
            else if(matchItem.statusOfMatch == AppConstants.MATCH_STATUS_POSTPONED){
                tvTime.setText(R.string.match_postponed)
            }
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