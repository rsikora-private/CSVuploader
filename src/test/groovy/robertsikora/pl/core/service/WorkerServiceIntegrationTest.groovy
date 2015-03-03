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
import robertsikora.pl.core.model.Worker
import robertsikora.pl.core.repository.WorkerDAO
import robertsikora.pl.core.util.CSVReader
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = [TestConfiguration.class])

@Transactional
class WorkerServiceIntegrationTest extends Specification {

    InputStream inputStream1
    InputStream inputStream2
    @Autowired
    WorkerService workerService
    @Autowired
    WorkerConverter workerConverter
    @Autowired
    WorkerDAO workerDAO

    @Before
    public void executedBeforeEach(){
        inputStream1 = TestUtils.loadSampleFile()
        inputStream2 = TestUtils.loadSampleFile()
    }

    @Test
    def testImportWorkers() {
        given:
            CSVReader csvReader = new CSVReader(inputStream1, true)
        when:
            workerService.importWorkers(inputStream2)
            def result = workerDAO.findAll()
            def fileWorkers = new ArrayList<>()

            while(csvReader.hasNext()){
                String csvLine = csvReader.next();
                Worker worker = workerConverter.convert(csvLine, ",")
                fileWorkers.add(worker);
            }

        then:
            result.size() == fileWorkers.size()
            result.sort() == fileWorkers.sort()
    }

    @After
    public void cleanupMess() {
        workerDAO.deleteAll()
        inputStream1.close()
        inputStream2.close()
    }
}
