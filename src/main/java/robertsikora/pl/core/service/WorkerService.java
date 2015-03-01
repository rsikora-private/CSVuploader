package robertsikora.pl.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import robertsikora.pl.core.model.Worker;
import robertsikora.pl.core.repository.WorkerDAO;
import robertsikora.pl.core.util.CSVReader;
import robertsikora.pl.web.validator.Validator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Robert on 2015-02-26.
 */

@Service
public class WorkerService {

    private final static String CSV_SEPARATOR = ",";
    private final static byte PREVIEW_LIMIT = 10;

    private WorkerConverter workerConverter;
    private WorkerDAO workerDAO;
    private Validator schemaValidator;

    @Autowired
    @Qualifier("schemaValidator")
    public void setSchemaValidator(Validator schemaValidator){
        this.schemaValidator = schemaValidator;
    }
    @Autowired
    public void setWorkerConverter(WorkerConverter workerConverter) {
        this.workerConverter = workerConverter;
    }
    @Autowired
    public void setWorkerDAO(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    public Collection<Worker> preparePreview(InputStream inputStream){

        CSVReader csvReader = new CSVReader(inputStream, false);
        Collection<Worker> workers = new ArrayList<>();

        byte idx = 0;
        while(csvReader.hasNext()
                && idx <=PREVIEW_LIMIT){

            String csvLine = csvReader.next();
            if(idx==0){
                ++idx;
                schemaValidator.validate(csvLine);
                continue;
            }
            ++idx;
            Worker worker = workerConverter.convert(csvLine, CSV_SEPARATOR);
            workers.add(worker);
        }

        return workers;
    }

    @Transactional
    public void importWorkers(InputStream inputStream){

        CSVReader csvReader = new CSVReader(inputStream, false);
        Collection<Worker> workers = new ArrayList<>();

        int idx = 0;
        while(csvReader.hasNext()){
            String csvLine = csvReader.next();
            if(idx==0){
                ++idx;
                schemaValidator.validate(csvLine);
                continue;
            }
            ++idx;
            Worker worker = workerConverter.convert(csvLine, CSV_SEPARATOR);
            workers.add(worker);
        }

        for(Worker worker : workers)
            workerDAO.save(worker);

    }


}
