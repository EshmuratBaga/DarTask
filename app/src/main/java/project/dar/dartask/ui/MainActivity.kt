package project.dar.dartask.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import project.dar.dartask.App
import project.dar.dartask.DiffUtils
import project.dar.dartask.R
import project.dar.dartask.model.Outcome
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    @Inject lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        App.component(this).inject(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter()

        listenerEvent()
    }

    private fun listenerEvent() {
        viewModel.weatherEvent.observe(this, Observer { o ->
            when(o){
                is Outcome.Progress -> {showProgress(o.loading)}
                is Outcome.Success -> {
                    (recyclerView.adapter as MainAdapter).update(o.data)
                }
                is Outcome.Failure -> {}
            }
        })
    }

    private fun showProgress(loading: Boolean) {
        if (loading) progress.visibility = View.VISIBLE else progress.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        searchView = myActionMenuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                handler.postDelayed({ if (p0!!.length > 2) viewModel.search(p0) }, 300)
                return true
            }
        })
        return true
    }
}
