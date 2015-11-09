CordovaCallNumberPlugin
=======================

Call a number directly from your cordova application.

Install the plugin using:

``` java
cordova plugin add https://github.com/Rohfosho/CordovaCallNumberPlugin.git
```

or

``` java
npm install call-number
```

Use the plugin in your JS file:
``` javascript
window.plugins.CallNumber.callNumber(onSuccess, onError, number);
```

Make sure to create onSuccess and onError call back functions.
