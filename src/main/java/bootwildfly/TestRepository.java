package bootwildfly;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Teste, String> {

	Teste findAll(PageRequest pageRequest);

}
