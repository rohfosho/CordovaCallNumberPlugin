var CallNumber = function(){};

CallNumber.prototype.callNumber = function(success, failure, number, bypassAppChooser){
    cordova.exec(success, failure, "CallNumber", "callNumber", [number, bypassAppChooser]);
};

CallNumber.prototype.isCallSupported = function(success, failure){
    cordova.exec(success, failure, "CallNumber", "isCallSupported");
}

//Plug in to Cordova
cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.CallNumber = new CallNumber();
});
