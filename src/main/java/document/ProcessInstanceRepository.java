package document;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessInstanceRepository extends
		MongoRepository<ProcessInstance, String> {

	List<ProcessInstance> getByInitiatorId( String initiatorId );
	List<ProcessInstance> getByProcessId( String processId );
	List<ProcessInstance> getByCurrentActivityId( String currentActivityId );
	List<ProcessInstance> getByState( String state );
}
