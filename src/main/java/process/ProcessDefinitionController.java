package process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
}
