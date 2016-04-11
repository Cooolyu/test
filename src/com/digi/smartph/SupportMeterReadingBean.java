package com.digi.smartph;


import java.io.Serializable;
import java.util.Date;
/**
 * class name:SupportMeterReadingBean <BR>
 * class description:  服务支持-远程抄表字段定义<BR>
 * Remark: <BR>
 * @version 1.00 2014-9-4
 * @author luyao
 */
public class SupportMeterReadingBean implements Serializable{
	
	private static final long serialVersionUID = -8402998482347044194L;
    
		private long id;//主键ID
		private int room_id;//房间号
		private Date generate_time;//生成时间
		private Double water;//水量
		private Double water_fee;//水费单价
		private Double elec;//电量
		private Double elec_fee;//电费单价
		private Double gas;//燃气
		private Double gas_fee;//燃气单价
		private Double total_fee;//总价
		private Date create_time;//创建时间
		private int status;//缴费状态
		private int deleted=0;//是否删除
		private String room_no;//房号
		private String mansion_no;//楼号
		private String elder_name;//长者姓名
		private String month;//月份
		
		private Double lastWater;
		private Double lastElec;
		
		private String elec_no;//电表设备号
		private String water_no;//水表设备号
		
		public Double getLastWater() {
			return lastWater;
		}
		public void setLastWater(Double lastWater) {
			this.lastWater = lastWater;
		}
		public Double getLastElec() {
			return lastElec;
		}
		public void setLastElec(Double lastElec) {
			this.lastElec = lastElec;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public int getRoom_id() {
			return room_id;
		}
		public void setRoom_id(int room_id) {
			this.room_id = room_id;
		}
		public Date getGenerate_time() {
			return generate_time;
		}
		public void setGenerate_time(Date generate_time) {
			this.generate_time = generate_time;
		}
		public Double getWater() {
			return water;
		}
		public void setWater(Double water) {
			this.water = water;
		}
		public Double getWater_fee() {
			return water_fee;
		}
		public void setWater_fee(Double water_fee) {
			this.water_fee = water_fee;
		}
		public Double getElec() {
			return elec;
		}
		public void setElec(Double elec) {
			this.elec = elec;
		}
		public Double getElec_fee() {
			return elec_fee;
		}
		public void setElec_fee(Double elec_fee) {
			this.elec_fee = elec_fee;
		}
		public Double getGas() {
			return gas;
		}
		public void setGas(Double gas) {
			this.gas = gas;
		}
		public Double getGas_fee() {
			return gas_fee;
		}
		public void setGas_fee(Double gas_fee) {
			this.gas_fee = gas_fee;
		}
		public Double getTotal_fee() {
			return total_fee;
		}
		public void setTotal_fee(Double total_fee) {
			this.total_fee = total_fee;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getDeleted() {
			return deleted;
		}
		public void setDeleted(int deleted) {
			this.deleted = deleted;
		}
		public String getRoom_no() {
			return room_no;
		}
		public void setRoom_no(String room_no) {
			this.room_no = room_no;
		}
		public String getMansion_no() {
			return mansion_no;
		}
		public void setMansion_no(String mansion_no) {
			this.mansion_no = mansion_no;
		}
		public String getElder_name() {
			return elder_name;
		}
		public void setElder_name(String elder_name) {
			this.elder_name = elder_name;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getElec_no() {
			return elec_no;
		}
		public void setElec_no(String elec_no) {
			this.elec_no = elec_no;
		}
		public String getWater_no() {
			return water_no;
		}
		public void setWater_no(String water_no) {
			this.water_no = water_no;
		}
		
}
