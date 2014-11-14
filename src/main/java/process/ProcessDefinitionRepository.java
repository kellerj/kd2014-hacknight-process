package process;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDefinitionRepository extends MongoRepository<ProcessDefinition, String> {

}
