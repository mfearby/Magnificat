package com.marcfearby.model

enum class PlayerState(val state: Int) {
    Stopped(-1),
    Paused(0),
    Playing(1)
}