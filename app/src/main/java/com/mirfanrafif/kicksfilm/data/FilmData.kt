package com.mirfanrafif.kicksfilm.data

import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity

object FilmData {
    private val movies = arrayListOf(
        MovieEntity(399566, "Godzilla vs. Kong", 2021,
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
        8.3, "Action, Science Fiction", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"),
    )

    private val tvSeries = arrayListOf(
        TvShowEntity(id = 95557, title = "Invincible", year = 2021,
            overview =  "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            rating =  8.9,  category = "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg"),
    )

    fun getMovies(): List<MovieEntity> = movies

    fun getTVShows(): List<TvShowEntity> = tvSeries
}