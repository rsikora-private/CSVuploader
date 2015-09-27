package robertsikora.pl.core.repository;

import org.springframework.stereotype.Repository;
import robertsikora.pl.core.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by Robert on 2015-02-27.
 */

@Repository
public class WorkerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(final Worker worker){
        entityManager.persist(worker);
    }

    public Collection<Worker> findAll(){
        return entityManager.createQuery("FROM Worker").getResultList();
    }

    public int deleteAll(){
        return entityManager.createQuery ("DELETE FROM Worker").executeUpdate();
    }
}
