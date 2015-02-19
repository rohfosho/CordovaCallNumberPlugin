#import <Cordova/CDVPlugin.h>
#import "CFCallNumber.h"

@implementation CFCallNumber

- (void) callNumber:(CDVInvokedUrlCommand*)command {

 	NSString* number = [command.arguments objectAtIndex:0];
  	number = [number stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]

 	if( ! [string hasPrefix:@"tel:"]){
		number =  [NSString stringWithFormat:@"tel:%@", number];
 	}	

 	if(![[UIApplication sharedApplication] openURL:[NSURL URLWithString:url]]) {
		pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"NoFeatureCallSupported"];
	}
	else {
		// missing phone number
		pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"CouldNotCallPhoneNumber"];
	}

	// return result
	[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

@end