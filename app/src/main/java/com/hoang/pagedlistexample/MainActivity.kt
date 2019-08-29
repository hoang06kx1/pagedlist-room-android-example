package com.hoang.pagedlistexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoang.pagedlistexample.repository.City
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    val mainViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_cities.setHasFixedSize(true)
        rv_cities.layoutManager = LinearLayoutManager(this)
        rv_cities.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        rv_cities.adapter = CityAdapter()
        mainViewModel.cityList.observe(this, Observer {
            (rv_cities.adapter as CityAdapter).submitList(it)
        })
    }
}

class CityAdapter : PagedListAdapter<City, CityViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        if (city != null) {
            holder.bindData(city)
        } else {
            holder.clear()
        }
    }
}

class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var tvCity: TextView
    private var tvCountry: TextView
    private var tvPopulation: TextView

    init {
        tvCity = view.findViewById(R.id.tv_city)
        tvCountry = view.findViewById(R.id.tv_country)
        tvPopulation = view.findViewById(R.id.tv_population)
    }

    fun bindData(city: City) {
        tvCity.text = city.city
        tvCountry.text = city.country
        tvPopulation.text = String.format("%,d", city.population)
    }

    fun clear() {
        tvCity.text = ""
        tvCountry.text = ""
        tvPopulation.text = ""
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldCity: City, newCity: City): Boolean {
        return oldCity.id == newCity.id
    }

    override fun areContentsTheSame(oldCity: City, newCity: City): Boolean {
        return oldCity == newCity
    }
}