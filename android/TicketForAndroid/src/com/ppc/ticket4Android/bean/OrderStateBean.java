package com.ppc.ticket4Android.bean;

import java.io.Serializable;

public class OrderStateBean implements Serializable
	{
		private Integer stateNum;
		private String state;
		
		public OrderStateBean(){}

		@Override
		public String toString() {
			return "OrderState [stateNum=" + stateNum + ", state=" + state + "]";
		}

		public Integer getStateNum() {
			return stateNum;
		}

		public void setStateNum(Integer stateNum) {
			this.stateNum = stateNum;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
	}
