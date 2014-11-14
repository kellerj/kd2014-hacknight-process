package process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/processes")
public class ProcessController {

    @Autowired
    private ProcessRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Process getProcess(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Process process) {
        return repository.save(process).getId();
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params = "status")
    @ResponseBody
    public String findByStatus( @RequestParam String status ) {
    	StringBuilder sb = new StringBuilder();
    	for ( Process p : repository.findByStatus(status) ) {
    		sb.append(p.toString()).append("<br />");
    	}
    	return sb.toString();
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
    	StringBuilder sb = new StringBuilder();
    	for ( Process p : repository.findAll() ) {
    		sb.append(p.toString()).append("<br />");
    	}
    	return sb.toString();
    }
}
