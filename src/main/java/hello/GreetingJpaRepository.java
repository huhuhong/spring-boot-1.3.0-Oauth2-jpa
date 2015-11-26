package hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by david on 11/26/2015.
 */
public interface GreetingJpaRepository extends JpaRepository<Greeting, Long> {
}
