# Magnificat

The name "Magnificat" is a play on the first line of the popular sacred choral work which begins "[Magnificat anima mea Dominum](https://www.youtube.com/watch?v=gQY4uWJAiTQ&t=8s)" (and is typically translated into English as "My soul doth magnify the Lord"). It lends itself well to what I hope might one day be a magnificent music cataloguing app, and my cat "Peanut" (2005 - 17 Nov 2021) was rather magnificent as well.

This is the Kotlin replacement of the old [JavaFX version of Magnificat](https://github.com/mfearby/magnificat-java).

## Pre-requisites

- You must have [VLC Media Player](https://www.videolan.org/vlc/) installed on your system.
- This project uses Java 17 SDK (if you use [asdf](https://asdf-vm.com/) to manage different tool versions on Mac, you can type `asdf install` to setup the java version specified in `.tool-versions`).


## Running the app

- Clone this repository:

```
git clone https://github.com/mfearby/Magnificat.git
```

- Open the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/).
- Edit the path to the test files folder at the bottom of `AudioPlayerWorker.kt`.
- Make sure you have ten test MP3 files (named `000.mp3` to `999.mp3`) in that folder. I will eventually get around to loading files from the selected directory).
- Select the `Magnificat [run]` configuration and click the green play button.
- I'm still auditioning suitable audio playing libraries, and at the moment clicking the "Next track" button is all it does until I'm happy it will do everything I need.