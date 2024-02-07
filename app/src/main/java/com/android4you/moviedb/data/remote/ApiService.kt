package com.android4you.moviedb.data.remote

import com.android4you.moviedb.data.response.ImagesModel
import com.android4you.moviedb.data.response.MovieCreditsModel
import com.android4you.moviedb.data.response.MovieDetailResponseModel
import com.android4you.moviedb.data.response.MovieResponse
import com.android4you.moviedb.data.response.PeopleDetailModel
import com.android4you.moviedb.data.response.PopularResponseModel
import com.android4you.moviedb.data.response.TvShowsCreditsModel
import com.android4you.moviedb.data.response.people.PopularPeopleResponse
import com.android4you.moviedb.data.response.tvshows.TVShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{movietype}?")
    suspend fun getPopularMovies(
        @Path("movietype") movietype: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>

    @GET("person/popular?")
    suspend fun getPopularPeople(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<PopularPeopleResponse>

    @GET("movie/popular?")
    suspend fun getPopularMoviess(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET("tv/{tv_show_type}?")
    suspend fun getPopularTVShows(
        @Path("tv_show_type") movietype: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<TVShowsResponse>

//    @GET("tv/popular?")
//    fun getPopularTVShows(
//        @Query("api_key") api_key: String,
//        @Query("page") page: Int,
//    ): Response<TVShowResponseModel>

    @GET("movie/{movie_id}?")
    fun getMovieDetails(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
    ): Response<MovieDetailResponseModel>

    @GET("person/{person_id}?")
    fun getPerson(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String,
    ): Response<PeopleDetailModel>

    @GET("person/{person_id}/movie_credits?")
    fun getPersonMovies(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String,
    ): Response<MovieCreditsModel>

    @GET("person/{person_id}/tv_credits?")
    fun getPersonTVShows(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String,
    ): Response<TvShowsCreditsModel>

    @GET("movie/{movie_id}/images?")
    fun getMovieImages(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
    ): Response<ImagesModel>

    @GET("movie/{movie_id}/similar?")
    fun getSimilarMovies(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String,
    ): Response<PopularResponseModel>
}
