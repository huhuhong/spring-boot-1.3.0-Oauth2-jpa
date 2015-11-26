package hello;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by david on 11/26/2015.
 */
@Repository
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public List<Greeting> GetList() {
        return null;
    }
}
