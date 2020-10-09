package cl.arlanditech.dota2calculator.fragments.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.arlanditech.dota2calculator.R
import cl.arlanditech.dota2calculator.fragments.heroes.model.HeroesRepo
import cl.arlanditech.dota2calculator.fragments.heroes.model.adapters.HeroesRvAdapter
import kotlinx.android.synthetic.main.fragment_heroes.*


class HeroesFragment : Fragment() {
    val TAG = "HeroesFragment"
    private lateinit var heroesViewModel: HeroesViewModel
    private lateinit var repo: HeroesRepo

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        heroesViewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_heroes, container, false)
        val rvHeroes: RecyclerView = root.findViewById(R.id.rv_heroes)

        heroesViewModel.heroes.observe(viewLifecycleOwner, Observer {
            progress_heroes.visibility = View.GONE
            val viewAdapter = HeroesRvAdapter(it, requireContext())
            rvHeroes.adapter = viewAdapter
            rvHeroes.layoutManager = LinearLayoutManager(context)
        })
        repo = HeroesRepo(heroesViewModel)
        repo.getHeroes()
        return root
    }
}