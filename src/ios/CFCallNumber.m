#import <Cordova/CDVPlugin.h>
#import "CFCallNumber.h"
 
@implementation CFCallNumber
 
- (void) callNumber:(CDVInvokedUrlCommand*)command {
 
    [self.commandDelegate runInBackground:^{
 
        CDVPluginResult* pluginResult = nil;
        NSString* number = [command.arguments objectAtIndex:0];
 
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
 
        // return result
        NSString *varyingString1 = @"tel:";
        NSString *s = [NSString stringWithFormat: @"%@%@", varyingString1, number];
 
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:s]];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}
 
@end
