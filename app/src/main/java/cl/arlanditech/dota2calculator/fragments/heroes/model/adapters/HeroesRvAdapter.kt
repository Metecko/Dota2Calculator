package cl.arlanditech.dota2calculator.fragments.heroes.model.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.arlanditech.dota2calculator.R
import cl.arlanditech.dota2calculator.activities.hero.HeroActivity
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.data.Common
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class HeroesRvAdapter(private val mHeroes: List<Hero>,
                      private val context: Context) :
    RecyclerView.Adapter<HeroesRvAdapter.ViewHolder>() {
    val TAG = this.javaClass.simpleName
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val containerView = itemView.findViewById<LinearLayout>(R.id.hero_item)
        val heroNameView = itemView.findViewById<TextView>(R.id.hero_name)
        val heroIconView = itemView.findViewById<ImageView>(R.id.hero_icon)
        val progressView = itemView.findViewById<ShimmerFrameLayout>(R.id.progress_item)
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
        val container = holder.containerView
        val heroName = holder.heroNameView
        val heroIcon = holder.heroIconView
        val progress = holder.progressView

        heroName.text = "Cargando..."
        Picasso.get().load(hero.getURLPng()).into(heroIcon, object: Callback {
            override fun onSuccess() {
                progress.hideShimmer()
                progress.stopShimmer()
                heroName.text = hero.displayName
                setOnClickListener(container, hero)
            }

            override fun onError(e: Exception?) {
                progress.hideShimmer()
                progress.stopShimmer()
                heroName.text = hero.displayName
                setOnClickListener(container, hero)
            }

        })
    }

    private fun setOnClickListener(container: LinearLayout, hero: Hero) {
        container.setOnClickListener {
            Common.currentHero = hero
            val intent = Intent(context, HeroActivity::class.java)
            context.startActivity(intent)
        }
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