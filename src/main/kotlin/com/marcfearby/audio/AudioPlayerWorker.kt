package com.marcfearby.audio

import com.marcfearby.common.utils.isMacOS
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

typealias Milliseconds = Long

interface ITrackListener {
    fun onPlayerReady(duration: Milliseconds)
    fun onTimeChanged(progress: Milliseconds)
}

interface IAudioPlayerWorker {
    fun play(track: String?, listener: ITrackListener)
    fun stop()
    fun pause()
    fun setVolume(volume: Double)
    fun release()
}

class AudioPlayerWorker: IAudioPlayerWorker {
    // vlcj 4.x Tutorial: https://capricasoftware.co.uk/tutorials/vlcj/4
    private var mediaPlayer: MediaPlayer? = null

    override fun release() {
        mediaPlayer?.release()
    }

    override fun play(track: String?, listener: ITrackListener) {
        track?.let {
            playTrack(it, listener)
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun setVolume(volume: Double) {
        TODO("Not yet implemented")
    }

    private fun playTrack(track: String, listener: ITrackListener) {
        val path = getTestTrack(track)

        if (mediaPlayer == null) {
            initPlayer(listener)
        }

        mediaPlayer?.media()?.play(path)
    }

    // This requires you to have VLC installed on your computer (when debugging in IntelliJ).
    // Apparently there's a way to package libvlc from that installation. I'll worry about that later.
    private fun initPlayer(listener: ITrackListener) {
        NativeDiscovery().discover()

        // MacOS needs a different version, see: https://capricasoftware.co.uk/tutorials/vlcj/4/prerequisites
        mediaPlayer = if (isMacOS()) {
            CallbackMediaPlayerComponent().mediaPlayer()
        } else {
            EmbeddedMediaPlayerComponent().mediaPlayer()
        }

        mediaPlayer?.events()?.addMediaPlayerEventListener(object: MediaPlayerEventAdapter() {
            override fun mediaPlayerReady(mediaPlayer: MediaPlayer?) {
                super.mediaPlayerReady(mediaPlayer)
                val duration = mediaPlayer?.media()?.info()?.duration() ?: -1
                listener.onPlayerReady(duration)
            }

            override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
                listener.onTimeChanged(newTime)
            }
        })
    }
}

// Get test tracks for now until I load real directories via the UI
fun getTestTrack(name: String): String {
    val path = "/Users/Marc.Fearby/Music/testing/"
    return path + when(name) {
        "111" -> "111.mp3"
        "222" -> "222.mp3"
        "333" -> "333.mp3"
        "444" -> "444.mp3"
        "555" -> "555.mp3"
        "666" -> "666.mp3"
        "777" -> "777.mp3"
        "888" -> "888.mp3"
        "999" -> "999.mp"
        else -> "000.mp3"
    }
}