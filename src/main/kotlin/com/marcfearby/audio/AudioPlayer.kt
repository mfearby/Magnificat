package com.marcfearby.audio

import com.marcfearby.model.PlayerState
import com.marcfearby.model.PlayerState.*
import org.koin.dsl.module

// component for dependency injection via koin
internal val audioPlayerModule = module {
    single<IAudioPlayer> { AudioPlayer() }
}

data class AudioPlayerState(
    val currentTrackIndex: Int,
    val currentTrack: String,
    val playerState: PlayerState
)

interface IAudioPlayer {
    val playerState: PlayerState
    var currentTrackIndex: Int
    var currentTrack: String
    var trackList: List<String>
    fun togglePlayerState(state: PlayerState): AudioPlayerState
    fun play(): AudioPlayerState
    fun pause(): AudioPlayerState
    fun stop(): AudioPlayerState
    fun next(): AudioPlayerState
    fun previous(): AudioPlayerState
}

class AudioPlayer: IAudioPlayer {

    override var playerState = Stopped
    override var currentTrackIndex = -1
    override var currentTrack = ""
    override var trackList = listOf("111", "222", "333", "444", "555")

    override fun togglePlayerState(state: PlayerState): AudioPlayerState = when (state) {
        Playing -> play()
        Paused -> pause()
        Stopped -> stop()
        MovingNext -> next()
        MovingPrevious -> previous()
    }

    private fun currentState() = AudioPlayerState(currentTrackIndex, currentTrack, playerState)

    override fun play(): AudioPlayerState {
        println("play")
        playerState = Playing
        if (currentTrackIndex < 0) {
            currentTrackIndex = 0
        }
        currentTrack = trackList[currentTrackIndex]
        return currentState()
    }

    override fun pause(): AudioPlayerState {
        println("pause")
        playerState = Paused
        return currentState()
    }

    override fun stop(): AudioPlayerState {
        println("stop")
        playerState = Stopped
        currentTrackIndex = -1
        currentTrack = ""
        return currentState()
    }

    override fun next(): AudioPlayerState {
        println("next")
        if (currentTrackIndex < trackList.lastIndex) {
            playerState = Playing
            currentTrack = trackList[++currentTrackIndex]
            play()
        }
        return currentState()
    }

    override fun previous(): AudioPlayerState {
        println("previous")
        if (currentTrackIndex > 0) {
            playerState = Playing
            currentTrack = trackList[--currentTrackIndex]
            play()
        }
        return currentState()
    }

}