package robertsikora.pl.core.service

import org.joda.time.format.DateTimeFormat
import org.springframework.test.util.ReflectionTestUtils
import robertsikora.pl.core.repository.WorkerDAO
import robertsikora.pl.web.validator.FileSchemaValidatorImpl
import robertsikora.pl.web.validator.Validator
import robertsikora.pl.web.validator.WorkerValidatorImpl
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-28.
 */

class WorkerServiceTest extends Specification {

    def df = DateTimeFormat.forPattern("dd-MM-yyyy")
    InputStream inputStream
    WorkerService workerService

    def setup(){
        inputStream = TestUtils.loadSampleFile()

        Validator workerValidator = Stub(WorkerValidatorImpl)
        WorkerConverter workerConverter = new WorkerConverter()
        ReflectionTestUtils.setField(workerConverter, "workerValidator", workerValidator)
        WorkerDAO workerDAO = Stub(WorkerDAO)
        workerService = new WorkerService(workerConverter, workerDAO);
        ReflectionTestUtils.setField(workerService, "schemaValidator", Stub(FileSchemaValidatorImpl))
    }

    def "Testing preparePreview"() {
        when:
            def result = workerService.preparePreview(inputStream)
        then:
            result.size() == 10

            result[0].firstName == "Robert"
            result[0].lastName == "Sikora"
            result[0].ssn == "85041717600"
            result[0].dob == df.parseDateTime("17-04-1985");
            result[0].email == "robertsikora@interia.pl"

            result[9].firstName == "Kuba"
            result[9].lastName == "Sikora"
            result[9].ssn == "85041717611"
            result[9].dob == df.parseDateTime("17-04-1985");
            result[9].email == "robertsikora@interia.pl"
    }

    def cleanup() {
        inputStream.close()
    }

}
