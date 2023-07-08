package com.marcfearby.audio

import com.marcfearby.model.PlayerState
import com.marcfearby.model.PlayerState.*
import org.koin.dsl.module

// component for dependency injection via koin
internal val audioPlayerModule = module {
    val worker = AudioPlayerWorker()
    single<IAudioPlayer> { AudioPlayer(worker) }
}

data class AudioPlayerState(
    val currentTrackIndex: Int,
    val currentTrackTitle: String,
    val playerState: PlayerState
)

interface IAudioPlayer {
    val playerState: PlayerState
    fun setTrackList(files: List<String>, startIndex: Int): AudioPlayerState
    fun togglePlayerState(state: PlayerState): AudioPlayerState
    fun play(): AudioPlayerState
    fun pause(): AudioPlayerState
    fun stop(): AudioPlayerState
    fun next(): AudioPlayerState
    fun previous(): AudioPlayerState
}

class AudioPlayer(
    private val audioPlayerWorker: IAudioPlayerWorker
): IAudioPlayer {

    override var playerState = Stopped
    private var currentTrackIndex = -1
    private var currentTrackTitle = ""
    private var trackList = mutableListOf("111", "222", "333", "444", "555")

    override fun togglePlayerState(state: PlayerState): AudioPlayerState = when (state) {
        Playing -> play()
        Paused -> pause()
        Stopped -> stop()
        MovingNext -> next()
        MovingPrevious -> previous()
    }

    override fun setTrackList(files: List<String>, startIndex: Int): AudioPlayerState {
        trackList = files.toMutableList()
        currentTrackIndex = startIndex
        return play()
    }

    private fun currentState() = AudioPlayerState(currentTrackIndex, currentTrackTitle, playerState)

    override fun play(): AudioPlayerState {
        println("play")
        playerState = Playing
        if (currentTrackIndex < 0) {
            currentTrackIndex = 0
        }
        currentTrackTitle = trackList[currentTrackIndex]
        audioPlayerWorker.play(currentTrackTitle)
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
        currentTrackTitle = ""
        return currentState()
    }

    override fun next(): AudioPlayerState {
        println("next")
        if (currentTrackIndex < trackList.lastIndex) {
            playerState = Playing
            currentTrackTitle = trackList[++currentTrackIndex]
            play()
        }
        return currentState()
    }

    override fun previous(): AudioPlayerState {
        println("previous")
        if (currentTrackIndex > 0) {
            playerState = Playing
            currentTrackTitle = trackList[--currentTrackIndex]
            play()
        }
        return currentState()
    }

}