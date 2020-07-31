package cl.arlanditech.dota2calculator.fragments.heroes.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.arlanditech.dota2calculator.R
import cl.arlanditech.dota2calculator.model.Hero

class HeroesRvAdapter(private val mHeroes: List<Hero>, private val context: Context) :
    RecyclerView.Adapter<HeroesRvAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val heroNameView = itemView.findViewById<TextView>(R.id.hero_name)
        val heroIconView = itemView.findViewById<ImageView>(R.id.hero_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context

        val inflater = LayoutInflater.from(context)
        val documentoItemView = inflater.inflate(R.layout.item_heroe, parent, false)

        return ViewHolder(documentoItemView)
    }

    override fun getItemCount(): Int {
        return mHeroes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero: Hero = mHeroes[position]
        val heroName = holder.heroNameView
        val heroIcon = holder.heroIconView

        heroName.text = hero.displayName
        heroIcon.setImageResource(hero.iconId)
    }

    fun scaleUpTextAnimation(
        v: View
    ) {
        val anim: Animation = ScaleAnimation(
            1.2f, 1.0f,  // Start and end values for the X axis scaling
            1.2f, 1.0f,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f
        ) // Pivot point of Y scaling
        anim.fillAfter = true // Needed to keep the result of the animation
        anim.duration = 300
        v.startAnimation(anim)
    }
}