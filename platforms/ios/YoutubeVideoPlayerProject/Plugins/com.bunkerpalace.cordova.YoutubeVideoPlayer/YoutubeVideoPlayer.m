//
//  YoutubeVideoPlayer.m
//
//  Created by Adrien Girbone on 15/04/2014.
//
//

#import "YoutubeVideoPlayer.h"
#import "XCDYouTubeKit.h"

@implementation YoutubeVideoPlayer

- (void)openVideo:(CDVInvokedUrlCommand*)command
{

    CDVPluginResult* pluginResult = nil;
    
    NSString* videoID = [command.arguments objectAtIndex:0];
    
    if (videoID != nil) {
        
        XCDYouTubeVideoPlayerViewController *videoPlayerViewController = [[XCDYouTubeVideoPlayerViewController alloc] initWithVideoIdentifier:videoID];
        [self.viewController presentMoviePlayerViewControllerAnimated:videoPlayerViewController];
        
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        
    } else {
        
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Missing videoID Argument"];
        
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
