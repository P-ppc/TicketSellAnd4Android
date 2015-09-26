package com.ppc.Ticket_sell.bean;

import java.sql.Date;

public class OrderBean {

	private Integer orderNum;
	private String passengerName;
	private String passengerId;
	private String orderTime;
	private String date;
	private Integer seatNo;
	private TicketBean ticket;
	private PhoneUserBean user;
	private OrderStateBean state;
	private WorkerBean worker;
	
	public OrderBean(){}

	@Override
	public String toString() {
		return "OrderBean [orderNum=" + orderNum + ", passengerName="
				+ passengerName + ", passengerId=" + passengerId
				+ ", orderTime=" + orderTime + ", date=" + date + ", seatNo="
				+ seatNo + ", ticket=" + ticket + ", user=" + user + ", state="
				+ state + ", worker=" + worker + "]";
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public TicketBean getTicket() {
		return ticket;
	}

	public void setTicket(TicketBean ticket) {
		this.ticket = ticket;
	}

	public PhoneUserBean getUser() {
		return user;
	}

	public void setUser(PhoneUserBean user) {
		this.user = user;
	}

	public OrderStateBean getState() {
		return state;
	}

	public void setState(OrderStateBean state) {
		this.state = state;
	}

	public WorkerBean getWorker() {
		return worker;
	}

	public void setWorker(WorkerBean worker) {
		this.worker = worker;
	}
	
	
	
}
