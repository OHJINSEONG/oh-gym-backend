package megatera.makaoGymbackEnd.models;


import java.util.Objects;

public class KakaopayReady {
    private String tid;
    private String next_redirect_pc_url;

    public KakaopayReady() {
    }

    public KakaopayReady(String tid, String next_redirect_pc_url) {
        this.tid = tid;
        this.next_redirect_pc_url = next_redirect_pc_url;
    }

    public String getTid() {
        return tid;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    @Override
    public String toString() {
        return "KakaopayReady{" +
                "tid='" + tid + '\'' +
                ", next_redirect_pc_url='" + next_redirect_pc_url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == KakaopayReady.class &&
                Objects.equals(this.tid, ((KakaopayReady) other).tid) &&
                Objects.equals(this.next_redirect_pc_url, ((KakaopayReady) other).next_redirect_pc_url);

    }

    @Override
    public int hashCode() {
        return Objects.hash(tid,next_redirect_pc_url);
    }
}
