package com.android4you.moviedb.data.response

class ImagesModel {

    var id: Int = 0

    var backdrops: List<BackdropsEntity>? = null

    class BackdropsEntity {

        var id: Int = 0

        var file_path: String? = null

    }
}