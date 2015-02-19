package com.monmouth.callnumber;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class CFCallNumber extends CordovaPlugin
{
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
        if( number.startsWith("tel:") == false){
            number = String.format("tel:%s", number);
        }


        if (((TelephonyManager)cordova.getActivity().getContext().getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType() == TelephonyManager.PHONE_TYPE_NONE ){
            callbackContext.error("NoFeatureCallSupported");
        }
        
        {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(number));
            cordova.getActivity().startActivity(intent);
            callbackContext.success();
        }
        catch (Exception e) {
            callbackContext.error("CouldNotCallPhoneNumber");
        }

        callbackContext.success();

        return true;
    }
}
