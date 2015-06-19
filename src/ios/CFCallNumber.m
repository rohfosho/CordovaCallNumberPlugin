#import <Cordova/CDVPlugin.h>
#import "CFCallNumber.h"

@implementation CFCallNumber

- (void) callNumber:(CDVInvokedUrlCommand*)command {

    [self.commandDelegate runInBackground:^{

        CDVPluginResult* pluginResult = nil;
        NSString* number = [command.arguments objectAtIndex:0];
        number = [number stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];

        if( ! [number hasPrefix:@"tel:"]){
            number =  [NSString stringWithFormat:@"tel:%@", number];
        }

        if(![[UIApplication sharedApplication] openURL:[NSURL URLWithString:number]]) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"NoFeatureCallSupported"];
        }
        else if ([number length] == 0){
            // missing phone number
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"CouldNotCallPhoneNumber"];
        } else {
          pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        }

        // return result
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

    }];
}

@end
