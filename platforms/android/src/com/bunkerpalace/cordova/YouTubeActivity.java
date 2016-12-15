package com.bunkerpalace.cordova;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;

public class YouTubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,
        PlayerStateChangeListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private String videoId;
    private String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");
        apiKey = intent.getStringExtra("YouTubeApiId");
        youTubeView = new YouTubePlayerView(this);
        youTubeView.initialize(apiKey, this);
        setContentView(youTubeView);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.loadVideo(videoId);
            player.setPlayerStateChangeListener(this);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format("Error initializing YouTube player", errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onVideoEnded() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError(
            com.google.android.youtube.player.YouTubePlayer.ErrorReason arg0) {
        updateLog("onError(): " + arg0.toString());
        finish();
    }

    @Override
    public void onAdStarted() {}

    @Override
    public void onLoaded(String arg0) {}

    @Override
    public void onLoading() {}

    @Override
    public void onVideoStarted() {}

    private void updateLog(String text){
        Log.d("YouTubeActivity", text);
    };
}
