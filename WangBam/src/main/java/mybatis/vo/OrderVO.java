package mybatis.vo;

public class OrderVO {
	private String or_idx,
	us_idx,
	or_date,
	or_end_date,
	or_name,
	or_postal_code,
	or_addr,
	or_addr_detail,
	or_tel,
	or_request,
	or_payment_code,
	or_tracking_number,
	or_total_price,
	or_status_code;
	private UserVO uvo;
	
	public String getOr_total_price() {
		return or_total_price;
	}
	public void setOr_total_price(String or_total_price) {
		this.or_total_price = or_total_price;
	}
	
	public String getOr_idx() {
		return or_idx;
	}
	public void setOr_idx(String or_idx) {
		this.or_idx = or_idx;
	}
	public String getUs_idx() {
		return us_idx;
	}
	public void setUs_idx(String us_idx) {
		this.us_idx = us_idx;
	}
	public String getOr_date() {
		return or_date;
	}
	public void setOr_date(String or_date) {
		this.or_date = or_date;
	}
	public String getOr_end_date() {
		return or_end_date;
	}
	public void setOr_end_date(String or_end_date) {
		this.or_end_date = or_end_date;
	}
	public String getOr_name() {
		return or_name;
	}
	public void setOr_name(String or_name) {
		this.or_name = or_name;
	}
	public String getOr_postal_code() {
		return or_postal_code;
	}
	public void setOr_postal_code(String or_postal_code) {
		this.or_postal_code = or_postal_code;
	}
	public String getOr_addr() {
		return or_addr;
	}
	public void setOr_addr(String or_addr) {
		this.or_addr = or_addr;
	}
	public String getOr_addr_detail() {
		return or_addr_detail;
	}
	public void setOr_addr_detail(String or_addr_detail) {
		this.or_addr_detail = or_addr_detail;
	}
	public String getOr_tel() {
		return or_tel;
	}
	public void setOr_tel(String or_tel) {
		this.or_tel = or_tel;
	}
	public String getOr_request() {
		return or_request;
	}
	public void setOr_request(String or_request) {
		this.or_request = or_request;
	}
	public String getOr_payment_code() {
		return or_payment_code;
	}
	public void setOr_payment_code(String or_payment_code) {
		this.or_payment_code = or_payment_code;
	}
	public String getOr_tracking_number() {
		return or_tracking_number;
	}
	public void setOr_tracking_number(String or_tracking_number) {
		this.or_tracking_number = or_tracking_number;
	}
	public String getOr_status_code() {
		return or_status_code;
	}
	public void setOr_status_code(String or_status_code) {
		this.or_status_code = or_status_code;
	}
	public UserVO getUvo() {
		return uvo;
	}
	public void setUvo(UserVO uvo) {
		this.uvo = uvo;
	}
}