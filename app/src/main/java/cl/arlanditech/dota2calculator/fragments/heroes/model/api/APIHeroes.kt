package cl.arlanditech.dota2calculator.fragments.heroes.model.api

import cl.arlanditech.dota2calculator.fragments.heroes.model.api.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIHeroes {
    @GET("Hero/")
    fun getHeroes(): Call<BaseResponse>
}