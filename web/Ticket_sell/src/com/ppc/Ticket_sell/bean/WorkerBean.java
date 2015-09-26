package com.ppc.Ticket_sell.bean;

public class WorkerBean {
   
	private String workerName;
	private String password;
	private String name;
	private Long tel;
	private JobBean job;
	private JurisdictionBean jurisdiction;
	
	public WorkerBean(){}


	@Override
	public String toString() {
		return "WorkerBean [workerName=" + workerName + ", password="
				+ password + ", name=" + name + ", tel=" + tel + ", job=" + job
				+ ", jurisdiction=" + jurisdiction + "]";
	}






	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}


	public JobBean getJob() {
		return job;
	}


	public void setJob(JobBean job) {
		this.job = job;
	}


	public JurisdictionBean getJurisdiction() {
		return jurisdiction;
	}


	public void setJurisdiction(JurisdictionBean jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	
	
}
