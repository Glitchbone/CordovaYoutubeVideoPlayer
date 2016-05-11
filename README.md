#Cordova YoutubeVideoPlayer Plugin

**Play Youtube Videos in a native Video Player on Android &amp; iOS.**

iOS plugin uses **XCDYouTubeKit** by Cédric Luthi:  
https://github.com/0xced/XCDYouTubeKit

Android version (up to 4.4) uses **OpenYoutubeActivity** by Keyes Labs:  
https://code.google.com/p/android-youtube-player

Android version (5.0+) uses YouTube Android Player API:
https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubePlayer

##Installation

```sh
cordova plugin add https://github.com/d0cz/CordovaYoutubeVideoPlayer
```

##Usage

```javascript
YoutubeVideoPlayer.openVideo('YOUTUBE_VIDEO_ID');
```

For Android 5.0+ you will need to update the YOUTUBE_API_KEY **(package com.bunkerpalace.cordova.Config)** with your own YouTube Key.

 For more information: https://developers.google.com/youtube/v3/getting-started

##Author

**Adrien Glitchbone**

+ [https://twitter.com/glitchbone](https://twitter.com/glitchbone)
+ [http://github.com/Glitchbone](http://github.com/Glitchbone)

##License

CordovaYoutubeVideoPlayer is available under the MIT license. See the [LICENSE](LICENSE) file for more information.  
XCDYouTubeKit is available under the MIT license.  
OpenYoutubeActivity is available under the Apache License 2.0.  
