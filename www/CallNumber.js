var CallNumber = function(){};

window.callNumber = function(success, failure, number) {
    cordova.exec(success, failure, "CallNumber", "callNumber", [number]);
};

cordova.addConstructor(function() {

    if (!window.Cordova) {
        window.Cordova = cordova;
    };

    if(!window.plugins) window.plugins = {};
    window.plugins.ContactPicker = new ContactPicker();
});
