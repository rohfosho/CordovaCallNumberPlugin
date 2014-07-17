var CallNumber = function(){};

CallNumber.prototype.callNumber = function(success, failure, number){
    cordova.exec(success, failure, "CallNumber", "callNumber", [number]);
};

//Plug in to Cordova
cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.CallNumber = new CallNumber();
});
