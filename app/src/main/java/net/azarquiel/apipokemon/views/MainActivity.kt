package net.azarquiel.apipokemon.views

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.apipokemon.R
import net.azarquiel.apipokemon.api.NameUrl
import net.azarquiel.apipokemon.api.Request
import net.azarquiel.apipokemon.api.Result
import net.azarquiel.apipokemon.adapter.CustomAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.uiThread

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var result: Result
    private lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pd=indeterminateProgressDialog("Cargando datos...")
        pd.show()
        doAsync {
            result = Request.getPokemons()
            uiThread {
                pd.hide()
                uiPokemons()
            }
        }
    }

    private fun uiPokemons() {
        val adapter = CustomAdapter(this, R.layout.row,result.results)
        rvPokemons.layoutManager = LinearLayoutManager(this)
        rvPokemons.adapter = adapter
    }

    fun onClickPokemon(view: View){
        val pokemon = view.tag as NameUrl
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }
}
