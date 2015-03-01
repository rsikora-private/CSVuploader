package robertsikora.pl.core.util

import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-27.
 */
class UtilsTest extends Specification {
    def "Testing conversion from MultipartFile object to InputStream"() {

        given:
            def multipartFile = Stub(MultipartFile)
            def inputStream = Stub(InputStream)
            multipartFile.getInputStream() >> inputStream
        when:
            def result = Utils.convertToInputStream(multipartFile)
        then: "checking if method returns InputStream"
            result == inputStream
    }
}
