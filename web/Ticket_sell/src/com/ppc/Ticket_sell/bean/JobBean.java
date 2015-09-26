package com.ppc.Ticket_sell.bean;

public class JobBean {

	private Integer jobNum;
	private String jobName;
	
	public JobBean(){}
	

	@Override
	public String toString() {
		return "JobBean [jobNum=" + jobNum + ", jobName=" + jobName + "]";
	}

	public Integer getJobNum() {
		return jobNum;
	}

	public void setJobNum(Integer jobNum) {
		this.jobNum = jobNum;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
}
