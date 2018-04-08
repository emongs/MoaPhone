package model;

import java.sql.Timestamp;

public class PhoneBean {

	private int lg_ser;
	private int ss_ser;
	private int apple_ser;
	
	private String model_kor;
	private String model_eng;
	private String model_eng_sk;
	private String model_eng_lg;
	private String model_eng_kt;
	private String price_sk;
	private String price_lg;
	private String price_kt;

	private Timestamp release_date;
	
	private String os;
	private String standard;
	private String battery;
	private String cpu;
	private String memory;
	private String display;
	private String camera;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getLg_ser() {
		return lg_ser;
	}
	public void setLg_ser(int lg_ser) {
		this.lg_ser = lg_ser;
	}
	public int getSs_ser() {
		return ss_ser;
	}
	public void setSs_ser(int ss_ser) {
		this.ss_ser = ss_ser;
	}
	public int getApple_ser() {
		return apple_ser;
	}
	public void setApple_ser(int apple_ser) {
		this.apple_ser = apple_ser;
	}
	public String getModel_kor() {
		return model_kor;
	}
	public void setModel_kor(String model_kor) {
		this.model_kor = model_kor;
	}
	public String getModel_eng() {
		return model_eng;
	}
	public void setModel_eng(String model_eng) {
		this.model_eng = model_eng;
	}
	public String getModel_eng_sk() {
		return model_eng_sk;
	}
	public void setModel_eng_sk(String model_eng_sk) {
		this.model_eng_sk = model_eng_sk;
	}
	public String getModel_eng_lg() {
		return model_eng_lg;
	}
	public void setModel_eng_lg(String model_eng_lg) {
		this.model_eng_lg = model_eng_lg;
	}
	public String getModel_eng_kt() {
		return model_eng_kt;
	}
	public void setModel_eng_kt(String model_eng_kt) {
		this.model_eng_kt = model_eng_kt;
	}
	public String getPrice_sk() {
		return price_sk;
	}
	public void setPrice_sk(String price_sk) {
		this.price_sk = price_sk;
	}
	public String getPrice_lg() {
		return price_lg;
	}
	public void setPrice_lg(String price_lg) {
		this.price_lg = price_lg;
	}
	public String getPrice_kt() {
		return price_kt;
	}
	public void setPrice_kt(String price_kt) {
		this.price_kt = price_kt;
	}
	public Timestamp getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Timestamp release_date) {
		this.release_date = release_date;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
}
