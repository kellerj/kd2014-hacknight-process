package engine;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import process.Activity;
import process.ProcessDefinition;
import document.ProcessInstance;

public class WorkflowEngine {

	public ProcessInstance advanceDocument( ProcessInstance processInstance ) {
		RestTemplate restTemplate = new RestTemplate();
		ProcessDefinition pd = restTemplate.getForObject("http://localhost:8090/processDefinitions/" + processInstance.getProcessId(), ProcessDefinition.class );
		if ( pd == null || pd.getId() == null ) {
			throw new IllegalArgumentException("Document type does not exist: " + processInstance.getProcessId() );
		}
		Logger.getLogger(getClass()).info( "Process Definition: " + pd);
		List<Activity> activities = restTemplate.getForObject("http://localhost:8090/activities", List.class);
		Activity nextActivity = null;
		Logger.getLogger(getClass()).info( "Activities: " + activities);
		// check state of document
		if ( processInstance.getState().equals("PREROUTE") ) {
			processInstance.setState("ENROUTE");
			processInstance.setSubmitDate( new Date() );
			// get the first route node
			if ( !activities.isEmpty() ) {
				nextActivity = activities.get(0);
			}
		} else {
			// obtain next workflow activity for process
			for ( Activity a : activities ) {
				if ( processInstance.getCurrentActivityId() == null || a.getId().equals(processInstance.getCurrentActivityId() ) ) {
					nextActivity = a;
					break;
				}
			}
		}		

		// are we at the last route node?  Yes?, then change document to approved and set the approval date
		if ( nextActivity == null ) {
			processInstance.setState("FINAL");
			processInstance.setApprovedDate( new Date() );
		}
		
		// save process instance
		restTemplate.put("http://localhost:8090/processInstances", processInstance);
		
		return processInstance;
	}
}
