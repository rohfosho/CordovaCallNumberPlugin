#import <Cordova/CDVPlugin.h>
#import "CallNumber.h"

@implementation CallNumber

- (void) callNumber:(CDVInvokedUrlCommand*)command {

  NSString* number = [command.arguments objectAtIndex:0];
  NSString *phoneNumber = [@"telprompt://" stringByAppendingString:number];
  [[UIApplication sharedApplication] openURL:[NSURL URLWithString:phoneNumber]];

}

@end