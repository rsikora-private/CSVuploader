package robertsikora.pl.core.service

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import robertsikora.pl.config.TestConfiguration
import robertsikora.pl.core.repository.WorkerDAO
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = [TestConfiguration.class])

@Transactional
class WorkerServiceIntegrationTest extends Specification {

    InputStream inputStream
    @Autowired
    WorkerService workerService
    @Autowired
    WorkerDAO workerDAO

    @Before
    public void executedBeforeEach(){
        inputStream = TestUtils.loadSampleFile()
    }

    @Test
    def testImportWorkers() {
        when:
            workerService.importWorkers(inputStream)
            def result = workerDAO.findAll()
        then:
            result.size() == 12
    }

    @After
    public void cleanupMess() {
        workerDAO.deleteAll();
        inputStream.close()
    }
}
