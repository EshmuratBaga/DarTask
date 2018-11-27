package project.dar.dartask.model

import com.google.gson.annotations.SerializedName

class CityRequest {
    @SerializedName("predictions") var city: List<City>? = null
}

class City{
    @SerializedName("status") var status: String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("id") var id: String? = null
    @SerializedName("terms") var terms: List<Term>? = null
    var temp: Float? = null
}

class Term {
    @SerializedName("offset") var offset: Int? = null
    @SerializedName("value") var value: String? = null
}