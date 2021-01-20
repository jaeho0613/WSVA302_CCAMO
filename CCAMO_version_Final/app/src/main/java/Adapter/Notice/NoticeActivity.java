package Adapter.Notice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import team.wsva302.ccamo.R;

public class NoticeActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        noticeList.add(new Notice("관리자", "1월 - 반영된 유저님들 의견", "2020-01-10"));
        noticeList.add(new Notice("관리자", "2월 - 반영된 유저님들 의견", "2020-02-13"));
        noticeList.add(new Notice("관리자", "3월 - 반영된 유저님들 의견", "2020-03-17"));
        noticeList.add(new Notice("관리자", "4월 - 반영된 유저님들 의견", "2020-04-20"));
        noticeList.add(new Notice("관리자", "5월 - 반영된 유저님들 의견", "2020-05-07"));
        noticeList.add(new Notice("관리자", "6월 - 반영된 유저님들 의견", "2020-06-05"));
        noticeList.add(new Notice("관리자", "7월 - 반영된 유저님들 의견", "2020-07-22"));
        noticeList.add(new Notice("관리자", "8월 - 반영된 유저님들 의견", "2020-08-11"));
        noticeList.add(new Notice("관리자", "9월 - 반영된 유저님들 의견", "2020-09-19"));
        noticeList.add(new Notice("관리자", "10월 - 반영된 유저님들 의견", "2020-10-12"));
        noticeList.add(new Notice("관리자", "11월 - 반영된 유저님들 의견", "2020-11-11"));
        noticeList.add(new Notice("관리자", "12월 - 반영된 유저님들 의견", "2020-12-23"));

        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);
    }
}