package robertsikora.pl.core.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Robert on 2015-02-27.
 */
public final class Utils {

    private Utils(){
    }

    public static InputStream convertToInputStream(final MultipartFile file){
        InputStream in;
        try{
            in = file.getInputStream();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return in;
    }
}
