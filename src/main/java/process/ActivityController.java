package process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
}
