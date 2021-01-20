package KaKaoAPI.Pay;

import android.util.Log;

import androidx.annotation.NonNull;

public class PayInfo {

    public String tid;
    public String next_redirect_app_url;
    public String next_redirect_mobile_url;
    public String next_redirect_pc_url;
    public String android_app_scheme;
    public String ios_app_scheme;
    public String created_at;
    public String pg_token;

    @NonNull
    @Override
    public String toString() {
        String print = "tid : " + tid + "\n"
                + "next_redirect_app_url : " + next_redirect_app_url + "\n"
                + "next_redirect_mobile_url : " + next_redirect_mobile_url + "\n"
                + "next_redirect_pc_url : " + next_redirect_pc_url + "\n"
                + "android_app_scheme : " + android_app_scheme + "\n"
                + "ios_app_scheme : " + ios_app_scheme + "\n"
                + "created_at : " + created_at;
        Log.e("Debug", print);

        return super.toString();
    }
}
