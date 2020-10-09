package cl.arlanditech.dota2calculator.activities.hero

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.arlanditech.dota2calculator.R
import cl.arlanditech.dota2calculator.activities.hero.model.HeroRepo
import cl.arlanditech.dota2calculator.databinding.ActivityHeroBinding
import cl.arlanditech.dota2calculator.databinding.ContentHeroBinding
import cl.arlanditech.dota2calculator.fragments.heroes.HeroesViewModel
import cl.arlanditech.dota2calculator.fragments.heroes.model.HeroesRepo
import cl.arlanditech.dota2calculator.fragments.heroes.model.adapters.HeroesRvAdapter
import cl.arlanditech.dota2calculator.model.Hero
import cl.arlanditech.dota2calculator.model.data.Common
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_hero.*
import kotlinx.android.synthetic.main.fragment_heroes.*

class HeroActivity : AppCompatActivity() {
    private lateinit var heroViewModel: HeroViewModel
    private lateinit var repo: HeroRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        setSupportActionBar(findViewById(R.id.hero_toolbar))

        val binding: ActivityHeroBinding = DataBindingUtil.setContentView(this, R.layout.activity_hero)

        heroViewModel = ViewModelProvider(this).get(HeroViewModel::class.java)

        heroViewModel.hero.observe(this, Observer {
            binding.hero = it
            Picasso.get().load(it!!.getURLPng()).into(hero_icon)
            switch_favorite.isChecked = it.isFavorite
        })
        repo = HeroRepo(heroViewModel)

        repo.getHero()

        switch_favorite.setOnClickListener {
            repo.updateFavoriteHero()
        }
    }
}