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
        String number = args.getString(0);
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            this.cordova.getActivity().startActivity(intent);
            callbackContext.success();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            callbackContext.error(e.getMessage());
        }
        return true;
    }
}
