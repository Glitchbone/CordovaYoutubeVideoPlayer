package com.bunkerpalace.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import android.net.Uri;
import com.keyes.youtube.OpenYouTubePlayerActivity;
import android.os.Build;

public class YoutubeVideoPlayer extends CordovaPlugin {

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if(action.equals("openVideo")) {
			String url = args.getString(0);
        	this.openVideo(url);
        	return true;
        }        
		
		return false;
	}
	
	private void openVideo(String videoId) {

		Intent intent = createYoutubeIntent(videoId);
		cordova.getActivity().startActivity(intent);
	}

	private Intent createYoutubeIntent(String videoId) {

		if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
//			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId), cordova.getActivity(), YouTubeActivity.class);
//			intent.putExtra("videoId", videoId);
//			return intent;
			Intent intent;
			Context cordovaContext = cordova.getActivity();
            //The following would be more ideal, but doesn't work!
            //if(YouTubeIntents.canResolvePlayVideoIntentWithOptions(cordovaContext)){
            //    intent = YouTubeIntents.createPlayVideoIntentWithOptions(cordovaContext, videoId, true, true);
            //} else
			if(YouTubeIntents.canResolvePlayVideoIntent(cordovaContext)) {
				intent = YouTubeIntents.createPlayVideoIntent(cordovaContext, videoId);
			} else {
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId), cordovaContext, YouTubeActivity.class);
				intent.putExtra("videoId", videoId);
                ConfigXmlParser parser = new ConfigXmlParser();
                parser.parse(cordovaContext);
                CordovaPreferences prefs = parser.getPreferences();

                intent.putExtra("YouTubeApiId", prefs.getString("YouTubeDataApiKey","YOUTUBE_API_KEY"));
			}
			return intent;
		}

		return new Intent(null, Uri.parse("ytv://" + videoId), cordova.getActivity(), OpenYouTubePlayerActivity.class);
	}
}
