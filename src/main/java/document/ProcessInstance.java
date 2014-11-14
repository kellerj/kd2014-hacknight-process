package document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProcessInstance {

	@Id
	String id;
	String processId;
	String currentActivityId;
	
	String state = "PREROUTE";
	
	String initiatorId;
	Date createDate;
	Date submitDate;
	Date approvedDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCurrentActivityId() {
		return currentActivityId;
	}
	public void setCurrentActivityId(String currentActivityId) {
		this.currentActivityId = currentActivityId;
	}
	public String getInitiatorId() {
		return initiatorId;
	}
	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
}
