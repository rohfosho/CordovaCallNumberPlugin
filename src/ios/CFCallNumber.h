#import <Cordova/CDVPlugin.h>

@interface CFCallNumber : CDVPlugin

- (void) callNumber:(CDVInvokedUrlCommand*)command;
- (void) isCallSupported:(CDVInvokedUrlCommand*)command;

@end
