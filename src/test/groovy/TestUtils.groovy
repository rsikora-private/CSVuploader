import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
/**
 * Created by Robert on 2015-02-28.
 */
class TestUtils {

    public static InputStream loadSampleFile(){

        Resource resource = new ClassPathResource("sample.csv");
        InputStream resourceInputStream = resource.getInputStream();
        return resourceInputStream;
    }
}
