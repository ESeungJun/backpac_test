package com.example.backpactest.vo

enum class WeatherStatus(val status: String) {
    SNOW("sn"){
        override val fullName: String
            get() = "Snow"
    },

    SLEET("sl"){
        override val fullName: String
            get() = "Sleet"
    },

    HAIL("h"){
        override val fullName: String
            get() = "Hail"
    },

    THUNDERSTORM("t"){
        override val fullName: String
            get() = "Thunderstorm"
    },

    HEAVY_RAIN("hr"){
        override val fullName: String
            get() = "Heavy Rain"
    },

    LIGHT_RAIN("kr"){
        override val fullName: String
            get() = "Light Rain"
    },

    SHOWERS("s"){
        override val fullName: String
            get() = "Showers"
    },

    HEAVY_CLOUD("hc"){
        override val fullName: String
            get() = "Heavy Cloud"
    },

    LIGHT_CLOUD("lc"){
        override val fullName: String
            get() = "Light Cloud"
    },

    CLEAT("c"){
        override val fullName: String
            get() = "Clear"
    },

    NONE("none"){
        override val fullName: String
            get() = "none"
    };

    abstract val fullName: String
}