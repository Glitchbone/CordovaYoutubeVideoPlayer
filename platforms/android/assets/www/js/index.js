var app = {
    initialize: function() {
        this.bindEvents();
    },
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    onDeviceReady: function() {
        
    },
	playVideo: function() {
		YoutubeVideoPlayer.openVideo('npjF032TDDQ', function(result) { console.log('YoutubeVideoPlayer result = ' + result); });
	}
};
