package robertsikora.pl.core.util

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Robert on 2015-02-28.
 */
class CSVReaderTest extends Specification {

    @Shared InputStream inputStream

    def setup(){
        inputStream = TestUtils.loadSampleFile()
    }

    def "Testing CSVReaderTest functionality"() {
        given:
            CSVReader csvReader = new CSVReader(inputStream, false)
            String[] results = new String[13]
        when:
            int i=0;
            while(csvReader.hasNext()){
                results[i++] = csvReader.next()
            }
        then: "checking if reader parse file correctly and returns good results"
            results[idx] == result
        where:
             idx | result
              0  | "First Name,Last Name,SSN,DOB,E-mail"
              1  | "Robert,Sikora,85041717600,17-04-1985,robertsikora@interia.pl"
              2  | "Kuba,Sikora,85041717601,17-04-1985,robertsikora@interia.pl"
              3  | "Jacek,Sikora,85041717602,17-04-1985,robertsikora@interia.pl"
              4  | "Andrzej,Sikora,85041717604,17-04-1985,robertsikora@interia.pl"
              5  | "Robert,Sikora,85041717605,17-04-1985,robertsikora@interia.pl"
              6  | "Kuba,Sikora,85041717606,17-04-1985,robertsikora@interia.pl"
              7  | "Jacek,Sikora,85041717607,17-04-1985,robertsikora@interia.pl"
              8  | "Andrzej,Sikora,85041717608,17-04-1985,robertsikora@interia.pl"
              9  | "Robert,Sikora,85041717609,17-04-1985,robertsikora@interia.pl"
              10 | "Kuba,Sikora,85041717611,17-04-1985,robertsikora@interia.pl"
              11 | "Jacek,Sikora,85041717612,17-04-1985,robertsikora@interia.pl"
              12 | "Andrzej,Sikora,85041717613,17-04-1985,robertsikora@interia.pl"
    }

    def cleanup() {
        inputStream.close()
    }
}
