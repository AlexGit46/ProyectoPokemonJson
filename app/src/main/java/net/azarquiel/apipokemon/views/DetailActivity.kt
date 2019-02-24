package net.azarquiel.apipokemon.views

import android.app.ProgressDialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row.view.*
import net.azarquiel.apipokemon.R
import net.azarquiel.apipokemon.api.NameUrl
import net.azarquiel.apipokemon.api.Request
import net.azarquiel.apipokemon.api.Result
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.uiThread

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var result: Result
    private lateinit var pokemon: NameUrl
    private lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        pokemon = intent.getSerializableExtra("pokemon") as NameUrl
        val urlBuena = pokemon.url.replace("-species","")
        pd=indeterminateProgressDialog("Cargando datos...")
        pd.show()
        doAsync {
            result = Request.getPokemon(urlBuena)
            uiThread {
                pd.hide()
                uiPokemon()
            }
        }

    }

    private fun uiPokemon() {
        tvNombre.text = pokemon.name.toUpperCase()
        Picasso.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${result.id}.png").into(iv)
        var lp= LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10,10,10,10)
        for (tipo in result.types.sortedWith(compareBy({ it.type.name}))){
            var tv = TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.setBackgroundColor(ContextCompat.getColor(this, R.color.ftypes))
            tv.setTextColor(Color.WHITE)
            tv.textSize = 20f
            tv.text = tipo.type.name
            linearTypes.addView(tv)
        }
        for (ability in result.abilities.sortedWith(compareBy({it.ability.name}))){
            var tv = TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.setBackgroundColor(ContextCompat.getColor(this, R.color.fabilities))
            tv.setTextColor(Color.WHITE)
            tv.textSize = 20f
            tv.text = ability.ability.name
            linearAbilities.addView(tv)
        }
        for (move in result.moves.sortedWith(compareBy({it.move.name}))){
            var tv = TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.setBackgroundColor(ContextCompat.getColor(this, R.color.fmoves))
            tv.setTextColor(Color.WHITE)
            tv.textSize = 20f
            tv.text = move.move.name
            linearMoves.addView(tv)
        }
    }
}
