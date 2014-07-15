package com.monmouth.callnumber;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.net.Uri;

public class CallNumber extends CordovaPlugin
{
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("call".equals(action)) {
            this.call(args.getString(0), callback);
            return true;
        }

        return false;
    }

    private void call(String number, CallbackContext callbackContext) {
    	try {
    		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
    		this.cordova.getActivity().startActivity(intent);
    		callbackContext.success();
    	}
    	catch (Exception e) {
    		callback.error(e.getMessage());
    	}
    }
}
