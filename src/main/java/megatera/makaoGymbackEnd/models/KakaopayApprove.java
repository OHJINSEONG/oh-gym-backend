package megatera.makaoGymbackEnd.models;

public class KakaopayApprove {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private String item_name;
    private String item_code;
    private int quantity;
    private String created_at;
    private String approved_at;
    private String payload;
    private KakaoPayAmount amount;

    public KakaopayApprove() {
    }

    public KakaopayApprove(
            String aid,
            String tid,
            String cid,
            String sid,
            String partner_order_id,
            String partner_user_id,
            String payment_method_type,
            String item_name,
            String item_code,
            int quantity,
            String created_at,
            String approved_at,
            String payload,
            KakaoPayAmount amount
    ) {
        this.aid = aid;
        this.tid = tid;
        this.cid = cid;
        this.sid = sid;
        this.partner_order_id = partner_order_id;
        this.partner_user_id = partner_user_id;
        this.payment_method_type = payment_method_type;
        this.item_name = item_name;
        this.item_code = item_code;
        this.quantity = quantity;
        this.created_at = created_at;
        this.approved_at = approved_at;
        this.payload = payload;
        this.amount = amount;
    }

    public String getAid() {
        return aid;
    }

    public String getTid() {
        return tid;
    }

    public String getCid() {
        return cid;
    }

    public String getSid() {
        return sid;
    }

    public String getPartner_order_id() {
        return partner_order_id;
    }

    public String getPartner_user_id() {
        return partner_user_id;
    }

    public String getPayment_method_type() {
        return payment_method_type;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_code() {
        return item_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getApproved_at() {
        return approved_at;
    }

    public String getPayload() {
        return payload;
    }

    public KakaoPayAmount getAmount() {
        return amount;
    }
}