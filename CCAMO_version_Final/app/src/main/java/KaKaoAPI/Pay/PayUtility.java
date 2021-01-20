package KaKaoAPI.Pay;

public class PayUtility {
    public static final String ADMIN_KEY = "63cc41f4f031e9fd35c5ef44fc0312ad";
    public static final String NATIVE_KEY = "321e3fec64fe5ba97624aaf18060f3d9"; // 네이티브 키

    public static final String REQUEST_URL = "https://kapi.kakao.com/v1/payment/ready"; // 결제 요청 url
    public static final String APPROVE_URL = "https://kapi.kakao.com/v1/payment/approve"; // 결제 성공 Url
    public static final String DOMAIN_URL = "https://developers.kakao.com/"; // 도메인

    public static final String SUCCESS_URL = DOMAIN_URL + "kakaoPaySuccess"; // 결제 완료 url
    public static final String SUCCESSFAIL_URL = DOMAIN_URL + "kakaoPaySuccessFail"; // 결제 실패 url
    public static final String CANCEL_URL = DOMAIN_URL + "kakaoPayCancel"; // 결제 취소 url
}
