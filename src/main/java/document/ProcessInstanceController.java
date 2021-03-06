package document;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import engine.WorkflowEngine;

@RestController
@RequestMapping(value = "/processInstances")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProcessInstance get( @PathVariable String id ) {
        return repository.findOne(id);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ProcessInstance create(@RequestBody ProcessInstance processInstance) {
    	ProcessInstance newProcessInstance = new ProcessInstance();
    	newProcessInstance.setCreateDate( new Date() );
    	newProcessInstance.setProcessId(processInstance.getProcessId());
    	newProcessInstance.setInitiatorId(processInstance.getInitiatorId());
    	newProcessInstance.setState( "PREROUTE" );
    	
        return repository.save(newProcessInstance);
    }

//    @RequestMapping(value = "/submit", method = RequestMethod.POST)
//    @ResponseBody
//    public ProcessInstance submit(@RequestBody ProcessInstance processInstance) {
//    	ProcessInstance existingProcessInstance = repository.findOne( processInstance.getId() );
//    	if ( existingProcessInstance == null ) {
//    		throw new IllegalArgumentException("Unknown processInstanceId");
//    	}
//    	existingProcessInstance = new WorkflowEngine().advanceDocument(existingProcessInstance);
//    	
//        return existingProcessInstance;
//    }
//
//    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
//    @ResponseBody
//    public ProcessInstance cancel(@RequestBody ProcessInstance processInstance) {
//    	ProcessInstance existingProcessInstance = repository.findOne( processInstance.getId() );
//    	if ( existingProcessInstance == null ) {
//    		throw new IllegalArgumentException("Unknown processInstanceId");
//    	}
//
//    	existingProcessInstance.setState("CANCELLED");
//    	
//        return repository.save(existingProcessInstance);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = "processId")
    @ResponseBody
    public List<ProcessInstance> findByProcess( @RequestParam String processId ) {
    	return repository.getByProcessId(processId);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcessInstance> list() {
    	return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ProcessInstance put(@PathVariable String id, @RequestBody ProcessInstance processInstance) {
    	ProcessInstance existingProcessInstance = repository.findOne( id );
    	if ( existingProcessInstance == null ) {
    		throw new IllegalArgumentException("Unknown processInstanceId");
    	}
    	if ( StringUtils.equals( existingProcessInstance.getState(), "PREROUTE" )
    			&& StringUtils.equals( processInstance.getState(), "ENROUTE") ) {
    		existingProcessInstance.setSubmitDate( new Date() );
    		existingProcessInstance = new WorkflowEngine().advanceDocument(existingProcessInstance);
    	}
    	
    	return repository.save(existingProcessInstance);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete( @PathVariable String id ) {
        repository.delete(id);
    }
}
