package robertsikora.pl.core.service

import org.joda.time.format.DateTimeFormat
import robertsikora.pl.web.validator.WorkerValidatorImpl
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-28.
 */
class WorkerConverterTest extends Specification {

    def "Testing conversion from String to Worker object"() {

        def df = DateTimeFormat.forPattern("dd-MM-yyyy")
        def workerConverter = new WorkerConverter()

        given:
            String workerStr = "Robert,Sikora,85041717600,17-04-1985,robertsikora@interia.pl"
            workerConverter.workerValidator = Stub(WorkerValidatorImpl)

        when:
            def result = workerConverter.convert(workerStr, ",")

        then: "checking if returned object has proper properties"
            result.firstName == "Robert"
            result.lastName == "Sikora"
            result.ssn == "85041717600"
            result.dob == df.parseDateTime("17-04-1985");
            result.email == "robertsikora@interia.pl"
    }
}
