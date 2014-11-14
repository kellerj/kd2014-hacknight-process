package process;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/processDefinitions")
public class ProcessDefinitionController {

    @Autowired
    private ProcessDefinitionRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProcessDefinition getProcess(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ProcessDefinition create(@RequestBody ProcessDefinition processDefinition) {
        return repository.save(processDefinition);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Collection<ProcessDefinition> list() {
        return repository.findAll();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestBody List<ProcessDefinition> processDefinitions) {
    	for ( ProcessDefinition pd : processDefinitions ) {
    		repository.save(pd);
    	}
    }
}
