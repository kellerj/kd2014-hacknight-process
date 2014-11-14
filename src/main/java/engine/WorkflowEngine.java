package engine;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import process.ProcessDefinition;
import document.ProcessInstance;

public class WorkflowEngine {

	public ProcessInstance advanceDocument( ProcessInstance processInstance ) {
		RestTemplate restTemplate = new RestTemplate();
		ProcessDefinition pd = restTemplate.getForObject("http://localhost:8090/processDefinitions/" + processInstance.getProcessId(), ProcessDefinition.class );
		if ( pd == null || pd.getId() == null ) {
			throw new IllegalArgumentException("Document type does not exist: " + processInstance.getProcessId() );
		}
		Logger.getLogger(getClass()).debug( "Process Definition: " + pd);
		// check state of document
		if ( processInstance.getState().equals("PREROUTE") ) {
			processInstance.setState("ENROUTE");
			processInstance.setSubmitDate( new Date() );
			// get the first route node
		}
		// obtain next workflow activity for process 

		// are we at the last route node?  Yes?, then change document to approved and set the approval date
		
		// set and save process instance
		
		restTemplate.put("http://localhost:8090/processInstances", processInstance);
		
		return processInstance;
	}
}
