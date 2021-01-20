package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import FireDatabase.FirebaseHelper;
import KaKaoAPI.Pay.PayInfo;
import KaKaoAPI.Pay.PayUtility;
import Utility.MyUtility;
import Utility.SharedKey;
import Utility.UtilSharedPreference;
import team.wsva302.ccamo.R;

public class PayActivity extends AppCompatActivity {

    Gson gson;
    WebView webView;
    MyWebViewClient myWebViewClient;
    static RequestQueue requestQueue;
    public static PayActivity payActivity;

    // 결제할 커피 정보
    static String coffeeName;
    static String coffeePrice;

    // 파이어베이스
    FirebaseHelper helper;

    public PayActivity() {

    }

    public PayActivity(String coffeeName, String coffeePrice) {

        MyUtility.println("PayAcitivty 실행");
        MyUtility.println("coffeeName : " + coffeeName);
        MyUtility.println("coffeePrice : " + coffeePrice);

        PayActivity.coffeeName = coffeeName;
        PayActivity.coffeePrice = coffeePrice;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        gson = new Gson();
        payActivity = PayActivity.this;

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        myWebViewClient = new MyWebViewClient();

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(myWebViewClient);

        requestQueue.add(myWebViewClient.request);

        helper = new FirebaseHelper(getApplicationContext(), UtilSharedPreference.getString(getApplicationContext(), SharedKey.USER_ID));
    }

    public class MyWebViewClient extends WebViewClient {

        PayInfo payInfo;

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtility.println("결제 승인 단계 : " + response);
                payInfo = gson.fromJson(response, PayInfo.class);

                webView.loadUrl(payInfo.next_redirect_mobile_url);
            }
        };

        Response.Listener<String> approveListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int point = UtilSharedPreference.getInteger(getApplicationContext(), SharedKey.USER_POINT);
                MyUtility.println("원래 포인트 : " + point);

                int payPrices = Integer.parseInt(coffeePrice);
                payPrices *= 0.01;

                point += payPrices;
                helper.WriteDatabase(point);
                UtilSharedPreference.setInteger(getApplicationContext(), SharedKey.USER_POINT, point);

                payActivity.finish();
            }
        };

        Response.ErrorListener errorListener = error -> Log.e("Debug", "Error : " + error.toString());

        StringRequest request = new StringRequest(Request.Method.POST, PayUtility.REQUEST_URL, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cid", "TC0ONETIME");
                params.put("partner_order_id", "1001");
                params.put("partner_user_id", "gorany");
                params.put("item_name", coffeeName);
                params.put("quantity", "1");
                params.put("total_amount", coffeePrice);
                params.put("tax_free_amount", "0");
                params.put("approval_url", PayUtility.SUCCESS_URL);
                params.put("cancel_url", PayUtility.CANCEL_URL);
                params.put("fail_url", PayUtility.SUCCESSFAIL_URL);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "KakaoAK " + PayUtility.ADMIN_KEY);
                headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

                return headers;
            }
        };

        StringRequest postStringRequest = new StringRequest(Request.Method.POST, PayUtility.APPROVE_URL, approveListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cid", "TC0ONETIME");
                params.put("tid", payInfo.tid);
                params.put("partner_order_id", "1001");
                params.put("partner_user_id", "gorany");
                params.put("pg_token", payInfo.pg_token);
                params.put("total_amount", coffeePrice);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "KakaoAK " + PayUtility.ADMIN_KEY);
                headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

                return headers;
            }
        };

        // URL 변경시 발생 이벤트
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.contains("pg_token=")) {
                String pg_token = url.substring(url.indexOf("pg_token=") + 9);
                payInfo.pg_token = pg_token;

                requestQueue.add(postStringRequest);

            } else if (url != null && url.startsWith("intent://")) {
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());
                    if (existPackage != null) {
                        startActivity(intent);
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            view.loadUrl(url);
            return false;
        }

        // 페이 정보 얻기
        public PayInfo getPayInfo() {
            return payInfo;
        }
    }
}