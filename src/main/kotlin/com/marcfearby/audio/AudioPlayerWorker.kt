package com.marcfearby.audio

import com.marcfearby.common.utils.isMacOS
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

interface IAudioPlayerWorker {
    fun play(track: String?)
    fun stop()
    fun pause()
    fun setVolume(volume: Double)
    fun release()
}

class AudioPlayerWorker: IAudioPlayerWorker {
    private var mediaPlayer: MediaPlayer? = null

    override fun release() {
        println("Releasing MediaPlayer resources")
        mediaPlayer?.release()
    }

    override fun play(track: String?) {
        track?.let {
            playTrack(it)
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

    private fun playTrack(track: String) {
        val path = getTestTrack(track)
        initPlayer(path)
    }

    // This requires you to have VLC installed on your computer (when debugging in IntelliJ).
    // Apparently there's a way to package libvlc from that installation. I'll worry about that later.
    private fun initPlayer(path: String) {
        NativeDiscovery().discover()

        // MacOS needs a different version, see: https://capricasoftware.co.uk/tutorials/vlcj/4/prerequisites
        mediaPlayer = if (isMacOS()) {
            CallbackMediaPlayerComponent().mediaPlayer()
        } else {
            EmbeddedMediaPlayerComponent().mediaPlayer()
        }

        mediaPlayer?.media()?.play(path)

        mediaPlayer?.events()?.addMediaPlayerEventListener(object: MediaPlayerEventAdapter() {
            override fun mediaPlayerReady(mediaPlayer: MediaPlayer?) {
                super.mediaPlayerReady(mediaPlayer)
                println("on ready")
            }

            override fun timeChanged(mediaPlayer: MediaPlayer?, newTime: Long) {
                println("new time: $newTime")
            }

            override fun lengthChanged(mediaPlayer: MediaPlayer?, newLength: Long) {
                println("new length: $newLength")
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