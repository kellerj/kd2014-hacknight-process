package process;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Activity getProcess(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Activity create(@RequestBody Activity processDefinition) {
        return repository.save(processDefinition);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Activity> list() {
        return repository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = "processDefinitionId")
    @ResponseBody
    public List<Activity> findByProcessDefinition( @RequestParam String processDefinitionId ) {
        return repository.findByProcessDefinitionId(processDefinitionId, new Sort( new Order("sortCode")));
    }
}
