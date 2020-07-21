package ru.anikey.core_network_impl.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import ru.anikey.core_network_api.data.interfaces.TerminalsApiClient
import ru.anikey.core_network_api.data.models.TerminalsResponseModel
import ru.anikey.core_network_impl.di.NetworkScope
import javax.inject.Inject

@NetworkScope
class TerminalsApiClientImpl @Inject constructor(
    private val retrofit: Retrofit
) : TerminalsApiClient {

    override fun makeTerminalsRequest(): Single<TerminalsResponseModel> = retrofit
        .create(TerminalsCall::class.java)
        .getTerminals()

    interface TerminalsCall {
        @GET("/static/catalog/terminals_v3.json")
        fun getTerminals(): Single<TerminalsResponseModel>
    }

}
