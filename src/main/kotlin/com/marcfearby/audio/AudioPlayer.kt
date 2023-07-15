package com.marcfearby.audio

import com.marcfearby.common.utils.Milliseconds
import com.marcfearby.model.PlayerState
import com.marcfearby.model.PlayerState.*
import com.marcfearby.model.ProgressUpdate
import org.koin.dsl.module

// component for dependency injection via koin
internal val audioPlayerModule = module {
    val worker = AudioPlayerWorker()
    single<IAudioPlayer> { AudioPlayer(worker) }
}

data class AudioPlayerState(
    val currentTrackIndex: Int,
    val currentTrackTitle: String,
    val playerState: PlayerState,
)

interface IAudioPlayer {
    val playerState: PlayerState
    fun setTrackList(files: List<String>, startIndex: Int): AudioPlayerState
    fun togglePlayerState(state: PlayerState, update: (progress: ProgressUpdate) -> Unit): AudioPlayerState
    fun play(): AudioPlayerState
    fun pause(): AudioPlayerState
    fun stop(): AudioPlayerState
    fun next(): AudioPlayerState
    fun previous(): AudioPlayerState
    fun release()
}

class AudioPlayer(
    private val audioPlayerWorker: IAudioPlayerWorker
): IAudioPlayer {

    override var playerState = Stopped
    private var currentTrackIndex = -1
    private var currentTrackLength: Milliseconds = 0
    private var currentTrackTime: Milliseconds = 0
    private var currentTrackTitle = ""
    private var trackList = mutableListOf("111", "222", "333", "444", "555")

    override fun release() {
        audioPlayerWorker.release()
    }

    private var progressEmitter: ((ProgressUpdate) -> Unit)? = null

    override fun togglePlayerState(state: PlayerState, update: (progress: ProgressUpdate) -> Unit): AudioPlayerState {
        progressEmitter = update

        return when (state) {
            Playing -> play()
            Paused -> pause()
            Stopped -> stop()
            MovingNext -> next()
            MovingPrevious -> previous()
        }
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

        audioPlayerWorker.play(currentTrackTitle, object: ITrackListener {
            override fun onPlayerReady(duration: Milliseconds) {
                currentTrackLength = duration
            }

            override fun onTimeChanged(progress: Milliseconds) {
                currentTrackTime = progress
                progressEmitter?.invoke(ProgressUpdate(currentTrackLength, currentTrackTime))
            }
        })

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