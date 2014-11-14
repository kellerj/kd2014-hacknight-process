package process;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
	
	List<Activity> findByProcessDefinitionId( String processDefinitionId, Sort sort );
}
