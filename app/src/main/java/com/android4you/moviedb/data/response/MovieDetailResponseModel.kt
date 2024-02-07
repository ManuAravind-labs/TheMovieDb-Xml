package com.android4you.moviedb.data.response

class MovieDetailResponseModel {

    var adult: Boolean? = false

    var backdrop_path: String? = null

    // var belongs_to_collection: String? =null

    var budget: Int = 0

    var genres: List<Genres>? = null

    var homepage: String? = null

    var id: Int = 0

    var imdb_id: String? = null

    var original_language: String? = null

    var original_title: String? = null

    var overview: String? = null

    var popularity: Double? = null

    var poster_path: String? = null

    // // var production_companies: List<Production_companies>? = null

    // var production_countries: List<Production_countries>? = null

    var release_date: String? = null

    var revenue: String? = null

    var runtime: Int = 0

    //  var spoken_languages: List<Spoken_languages>? = null

    var status: String? = null

    var tagline: String? = null

    var title: String? = null

    var video: Boolean = false

    var vote_average: Double = 0.toDouble()

    var vote_count: Int = 0
}

class Genres {
    var id: Int = 0

    var name: String? = null
}
//
//
// class Spoken_languages {
// //    var iso_639_1: String? = null
//
//    var name: String? = null
// }
//
//
// class Production_countries {
// //   var iso_3166_1 : String? = null
//
//    var name : String? = null
// }
//
//
// class Production_companies {
//
//    var id: Int = 0
//    var logo_path: String? = null
//    var name: String? = null
//    var origin_country: String?= null
// }
