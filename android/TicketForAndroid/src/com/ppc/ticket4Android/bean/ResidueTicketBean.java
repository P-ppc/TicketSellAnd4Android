package com.ppc.ticket4Android.bean;

import java.io.Serializable;

public class ResidueTicketBean implements Serializable
	{
		private TicketBean ticket;
		private Integer residueSeat;
		private String date;
		
		public ResidueTicketBean(){}
		
		@Override
		public String toString() {
			return "ResidueTicketBean [ticket=" + ticket + ", residueSeat="
					+ residueSeat + ", date=" + date + "]";
		}



		public TicketBean getTicket() {
			return ticket;
		}

		public void setTicket(TicketBean ticket) {
			this.ticket = ticket;
		}

		public Integer getResidueSeat() {
			return residueSeat;
		}

		public void setResidueSeat(Integer residueSeat) {
			this.residueSeat = residueSeat;
		}



		public String getDate() {
			return date;
		}



		public void setDate(String date) {
			this.date = date;
		}
	}
