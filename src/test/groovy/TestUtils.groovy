import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
/**
 * Created by Robert on 2015-02-28.
 */
class TestUtils {

    public static InputStream loadSampleFile(){
        final Resource resource = new ClassPathResource("sample.csv");
        return resource.getInputStream();
    }
}
