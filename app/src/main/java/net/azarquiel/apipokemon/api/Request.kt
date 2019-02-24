package net.azarquiel.apipokemon.api

import com.google.gson.Gson
import java.net.URL

object Request {

    val URLApi="https://pokeapi.co/api/v2/pokemon-species/"

    fun getPokemons():Result{
        val json = URL(URLApi).readText()
        val result = Gson().fromJson(json, Result::class.java)
        return result
    }

    fun getPokemon(url:String):Result{
        val json = URL(url).readText()
        val result = Gson().fromJson(json, Result::class.java)
        return result
    }
}