var CallNumber = function(){};

CallNumber.prototype.makeCall = function(success, failure){
    cordova.exec(success, failure, "CallNumber", "callNumber", [number]);
};

cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.CallNumber = new CallNumber();
});
