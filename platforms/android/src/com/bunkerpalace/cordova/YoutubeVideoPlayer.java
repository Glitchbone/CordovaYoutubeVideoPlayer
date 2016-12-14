package com.bunkerpalace.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.youtube.player.YouTubeIntents;
import com.keyes.youtube.OpenYouTubePlayerActivity;
import android.os.Build;
import android.util.Log;

public class YoutubeVideoPlayer extends CordovaPlugin {

	private CallbackContext callbackContext;

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if(action.equals("openVideo")) {
			String url = args.getString(0);
        	this.openVideo(url);
			this.callbackContext = callbackContext;
        	return true;
        }
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 242) {
			if (resultCode == this.cordova.getActivity().RESULT_OK) {
				this.callbackContext.success();
			} else {
				this.callbackContext.error("Error");
			}
		}
	}

	private void openVideo(String videoId) {
		Intent intent = createYoutubeIntent(videoId);
		cordova.startActivityForResult(this, intent, 242);
	}

	private Intent createYoutubeIntent(String videoId) {
		if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
			Intent intent;
			Context cordovaContext = cordova.getActivity();
			String version = YouTubeIntents.getInstalledYouTubeVersionName(cordovaContext);
			if(version.startsWith("11.16") && YouTubeIntents.canResolvePlayVideoIntent(cordovaContext)) {
				intent = YouTubeIntents.createPlayVideoIntent(cordovaContext, videoId);
			} else {
				if(YouTubeIntents.canResolvePlayVideoIntentWithOptions(cordovaContext)){
					intent = YouTubeIntents.createPlayVideoIntentWithOptions(cordovaContext, videoId, true, true);
				} else {
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId), cordovaContext, YouTubeActivity.class);
					intent.putExtra("videoId", videoId);
	                ConfigXmlParser parser = new ConfigXmlParser();
	                parser.parse(cordovaContext);
	                CordovaPreferences prefs = parser.getPreferences();
	                intent.putExtra("YouTubeApiId", prefs.getString("YouTubeDataApiKey","YOUTUBE_API_KEY"));
				}
			}
			return intent;
		}

		return new Intent(null, Uri.parse("ytv://" + videoId), cordova.getActivity(), OpenYouTubePlayerActivity.class);
	}
}
