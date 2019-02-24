package net.azarquiel.apipokemon.api

import java.io.Serializable

data class Result(var results:List<NameUrl>,
                  var abilities:List<Abilitie>,
                  var types:List<Type>,
                  var moves:List<Move>,
                  var id:String)

data class NameUrl(var name:String,
                   var url:String):Serializable

data class Abilitie(var ability:NameUrl)

data class Type(var type:NameUrl)

data class Move(var move:NameUrl)
