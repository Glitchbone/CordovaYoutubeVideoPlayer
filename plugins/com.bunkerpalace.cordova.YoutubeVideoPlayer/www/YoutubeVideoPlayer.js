var exec = require('cordova/exec');

function YoutubeVideoPlayer() {}

YoutubeVideoPlayer.prototype.openVideo = function(YTid) {
	exec(function(result) {
		console.log(result);
	},
	function(error) {
		console.log(error);
	},
	"YoutubeVideoPlayer",
	"openVideo",
	[YTid]
	);
}

var YoutubeVideoPlayer = new YoutubeVideoPlayer();
module.exports = YoutubeVideoPlayer