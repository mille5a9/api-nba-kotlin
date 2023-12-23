package api.nba.kotlin.params

interface EndpointParams {
    fun getParams(): Map<String, String?>
}