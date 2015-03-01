package robertsikora.pl.core.repository;

import org.springframework.stereotype.Repository;
import robertsikora.pl.core.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Robert on 2015-02-27.
 */

@Repository
public class WorkerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Worker worker){
        entityManager.persist(worker);
    }

    public Collection<Worker> findAll(){
        Query query = entityManager.createQuery("FROM Worker");
        return (Collection<Worker>) query.getResultList();
    }

    public void deleteAll(){
        Query query = entityManager.createQuery ("DELETE FROM Worker");
        query.executeUpdate();
    }
}
