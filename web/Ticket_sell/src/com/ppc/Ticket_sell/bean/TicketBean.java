package com.ppc.Ticket_sell.bean;

public class TicketBean {

	private Integer ticketNum;
	private String start_station;
	private String end_station;
	private Double price;
	private String time;
	private String motorcoach_number;
	private Integer seatNum;
	
	//无参构造方法
	public TicketBean(){}

	@Override
	public String toString() {
		return "TicketBean [ticketNum=" + ticketNum + ", start_station="
				+ start_station + ", end_station=" + end_station + ", price="
				+ price + ", time=" + time + ", motorcoach_number="
				+ motorcoach_number + ", seatNum=" + seatNum + "]";
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getStart_station() {
		return start_station;
	}

	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}



	public String getEnd_station() {
		return end_station;
	}

	public void setEnd_station(String end_station) {
		this.end_station = end_station;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMotorcoach_number() {
		return motorcoach_number;
	}

	public void setMotorcoach_number(String motorcoach_number) {
		this.motorcoach_number = motorcoach_number;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
	
}
