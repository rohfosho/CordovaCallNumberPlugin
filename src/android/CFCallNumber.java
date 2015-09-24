package mx.ferreyra.callnumber;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class CFCallNumber extends CordovaPlugin
{
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        String number = args.getString(0);
        number = number.replaceAll("#","%23");
        
        if( number.startsWith("tel:") == false){
            number = String.format("tel:%s", number);
        }

        try {
            Intent intent = new Intent(isTelephonyEnabled() ? Intent.ACTION_CALL : Intent.ACTION_VIEW);
            intent.setData(Uri.parse(number));
            cordova.getActivity().startActivity(intent);
            callbackContext.success();
        }
        catch (Exception e) {
            callbackContext.error("CouldNotCallPhoneNumber");
        }

        return true;
    }

    private boolean isTelephonyEnabled(){
        TelephonyManager tm = (TelephonyManager)cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        return tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }
}
