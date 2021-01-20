package Adapter.Notice;

public class Notice {
    // 필드
    String name;
    String notice;
    String date;

    // 생성자
    public Notice(String name, String notice, String date) {
        this.name = name;
        this.notice = notice;
        this.date = date;
    }

    // 메소드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}