package com.example.soundinch9.ui.model

data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val durationSeconds: Int,
    val playlistId: Int
)