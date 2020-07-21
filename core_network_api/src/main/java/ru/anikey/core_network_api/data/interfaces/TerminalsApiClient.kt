package ru.anikey.core_network_api.data.interfaces

import io.reactivex.Single
import ru.anikey.core_network_api.data.models.TerminalsResponseModel

interface TerminalsApiClient {

    fun makeTerminalsRequest(): Single<TerminalsResponseModel>

}
