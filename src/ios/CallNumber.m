#import <Cordova/CDVPlugin.h>
#import "CallNumber.h"

- (void) callNumber:(CDVInvokedUrlCommand*)command {

  NSNumber* number = [command.arguments objectAtIndex:0];
  NSString *phoneNumber = [@"telprompt://" stringByAppendingString:number];
  [[UIApplication sharedApplication] openURL:[NSURL URLWithString:phoneNumber]];

}
