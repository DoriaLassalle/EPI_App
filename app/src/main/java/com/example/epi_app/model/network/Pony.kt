package com.example.epi_app.model.network
import com.google.gson.annotations.SerializedName


data class Pony(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)

data class Hit(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageHeight")
    val imageHeight: Int,
    @SerializedName("imageSize")
    val imageSize: Int,
    @SerializedName("imageWidth")
    val imageWidth: Int,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("pageURL")
    val pageURL: String,
    @SerializedName("previewHeight")
    val previewHeight: Int,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("previewWidth")
    val previewWidth: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("userImageURL")
    val userImageURL: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("webformatHeight")
    val webformatHeight: Int,
    @SerializedName("webformatURL")
    val webformatURL: String,
    @SerializedName("webformatWidth")
    val webformatWidth: Int
)