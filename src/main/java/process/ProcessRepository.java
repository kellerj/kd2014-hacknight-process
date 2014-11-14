package process;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends MongoRepository<Process, String> {
	List<Process> findByStatus( String status );
}
