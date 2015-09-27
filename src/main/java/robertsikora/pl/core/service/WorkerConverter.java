package robertsikora.pl.core.service;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import robertsikora.pl.core.model.Worker;
import robertsikora.pl.web.validator.Validator;

/**
 * Created by Robert on 2015-02-26.
 */

@Component
public class WorkerConverter {

    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");

    @Autowired
    @Qualifier("workerValidator")
    private Validator workerValidator;

    public Worker convert(final String csvLine, final String separator){
        Assert.hasText(csvLine);
        Assert.hasText(separator);

        final String[] properties = csvLine.split(separator);
        Worker worker = new Worker();
        worker.setFirstName(properties[0]);
        worker.setLastName(properties[1]);
        worker.setSsn(properties[2]);
        worker.setDob(formatter.parseDateTime(properties[3]));
        worker.setEmail(properties[4]);
        workerValidator.validate(worker);
        return worker;
    }
}
