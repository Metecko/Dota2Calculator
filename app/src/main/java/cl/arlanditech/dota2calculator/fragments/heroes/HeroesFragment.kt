package cl.arlanditech.dota2calculator.fragments.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.arlanditech.dota2calculator.R
import cl.arlanditech.dota2calculator.fragments.heroes.model.DataHeroAccess
import cl.arlanditech.dota2calculator.fragments.heroes.model.adapters.HeroesRvAdapter
import cl.arlanditech.dota2calculator.fragments.heroes.model.observer.HeroesObserver
import cl.arlanditech.dota2calculator.model.Hero


class HeroesFragment : Fragment(), HeroesObserver {
    val TAG = "HeroesFragment"
    private lateinit var heroesViewModel: HeroesViewModel
    private lateinit var dataAccess: DataHeroAccess

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        heroesViewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_heroes, container, false)
        val rvHeroes: RecyclerView = root.findViewById(R.id.rv_heroes)

        heroesViewModel.heroes.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, it.toString())
            val viewAdapter = HeroesRvAdapter(it, requireContext())
            rvHeroes.adapter = viewAdapter
            rvHeroes.layoutManager = LinearLayoutManager(context)
        })
        dataAccess = DataHeroAccess(requireContext())
        dataAccess.getHeroes()
        return root
    }

    override fun onStart() {
        super.onStart()
        dataAccess.register(this)
    }

    override fun onStop() {
        super.onStop()
        dataAccess.unregister(this)
    }

    override fun addHeroes(heroes: ArrayList<Hero>) {
        heroesViewModel.heroes.value = heroes
    }
}