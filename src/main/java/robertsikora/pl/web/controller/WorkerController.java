package robertsikora.pl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import robertsikora.pl.core.model.Worker;
import robertsikora.pl.core.service.WorkerService;
import robertsikora.pl.core.util.Utils;
import robertsikora.pl.web.validator.Validator;

import java.util.Collection;

import static org.springframework.http.MediaType.*;

/**
 * Created by Robert on 2015-02-26.
 */

@Controller
public class WorkerController {

    @Autowired
    @Qualifier("fileValidator")
    private Validator fileValidator;

    private final WorkerService workerService;

    @Autowired
    public WorkerController(final WorkerService workerService){
        this.workerService = workerService;
    }

    @RequestMapping(value="/uploadpreview",
            produces = APPLICATION_JSON_VALUE,
            method= RequestMethod.POST)

    public @ResponseBody
    Collection<Worker> uploadPreview (@RequestParam("file") final MultipartFile file){
        fileValidator.validate(file);
        return workerService.preparePreview(Utils.convertToInputStream(file));
    }

    @RequestMapping(value="/import",
            method= RequestMethod.POST)

    public @ResponseBody
    void importWorkers(@RequestParam("file") final MultipartFile file){
        fileValidator.validate(file);
         workerService.importWorkers(Utils.convertToInputStream(file));
    }
}
