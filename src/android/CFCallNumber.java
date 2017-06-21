package mx.ferreyra.callnumber;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;

public class CFCallNumber extends CordovaPlugin {
  public static final int CALL_REQ_CODE = 0;
  public static final int PERMISSION_DENIED_ERROR = 20;
  public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;

  private CallbackContext callbackContext;        // The callback context from which we were invoked.
  private JSONArray executeArgs;

  protected void getCallPermission(int requestCode) {
    cordova.requestPermission(this, requestCode, CALL_PHONE);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    this.callbackContext = callbackContext;
    this.executeArgs = args;

    if (action.equals("callNumber")) {
      if (cordova.hasPermission(CALL_PHONE)) {
        callPhone(executeArgs);
      } else {
        getCallPermission(CALL_REQ_CODE);
      }
    } else if (action.equals("isCallSupported")) {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isTelephonyEnabled()));
    } else {
      return false;
    }

    return true;
  }

  public void onRequestPermissionResult(int requestCode, String[] permissions,
                                        int[] grantResults) throws JSONException {
    for (int r : grantResults) {
      if (r == PackageManager.PERMISSION_DENIED) {
        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, PERMISSION_DENIED_ERROR));
        return;
      }
    }
    switch (requestCode) {
      case CALL_REQ_CODE:
        callPhone(executeArgs);
        break;
    }
  }

  private void callPhone(JSONArray args) throws JSONException {
    String number = args.getString(0);
    number = number.replaceAll("#", "%23");

    if (!number.startsWith("tel:")) {
      number = String.format("tel:%s", number);
    }
    try {
      Intent intent = new Intent(isTelephonyEnabled() ? Intent.ACTION_CALL : Intent.ACTION_VIEW);
      intent.setData(Uri.parse(number));

      boolean bypassAppChooser = Boolean.parseBoolean(args.getString(1));
      if (bypassAppChooser) {
        intent.setPackage(getDialerPackage(intent));
      }

      cordova.getActivity().startActivity(intent);
      callbackContext.success();
    } catch (Exception e) {
      callbackContext.error("CouldNotCallPhoneNumber");
    }
  }

  private boolean isTelephonyEnabled() {
    TelephonyManager tm = (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
    return tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
  }

  private String getDialerPackage(Intent intent) {
    PackageManager packageManager = (PackageManager) cordova.getActivity().getPackageManager();
    List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

    for (int i = 0; i < activities.size(); i++) {
      if (activities.get(i).toString().toLowerCase().contains("com.android.server.telecom")) {
        return "com.android.server.telecom";
      }
      if (activities.get(i).toString().toLowerCase().contains("com.android.phone")) {
        return "com.android.phone";
      } else if (activities.get(i).toString().toLowerCase().contains("call")) {
        return activities.get(i).toString().split("[ ]")[1].split("[/]")[0];
      }
    }
    return "";
  }
}
