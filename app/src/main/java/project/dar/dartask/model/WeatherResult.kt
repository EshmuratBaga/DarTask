package project.dar.dartask.model

import com.google.gson.annotations.SerializedName

class WeatherResult {
    @SerializedName("main") var data: Data? = null
    @SerializedName("name") var name: String? = null
}

class Data {
    @SerializedName("temp") var temp: Float? = null
}