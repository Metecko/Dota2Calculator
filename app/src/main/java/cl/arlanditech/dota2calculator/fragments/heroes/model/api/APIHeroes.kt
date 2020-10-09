package cl.arlanditech.dota2calculator.fragments.heroes.model.api

import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIHeroes {
    @GET("Hero?languageId=21")
    fun getHeroes(): Call<BaseResponse>
}