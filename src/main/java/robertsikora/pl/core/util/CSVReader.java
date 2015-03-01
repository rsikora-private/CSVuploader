package robertsikora.pl.core.util;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Robert on 2015-02-26.
 */

public class CSVReader implements Iterator<String>{

    private BufferedReader bufferedReader;
    private String line;
    private boolean omittFirstLine;

    public CSVReader(InputStream inputStream, boolean omittFirstLine) {
        try {

            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            this.omittFirstLine = omittFirstLine;

        }catch (IOException e){

            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {

        if(omittFirstLine){
            omittFirstLine=false;
            read();
        }
        this.line = read();

        boolean hasNext = null!=line;
        if(!hasNext){
            closeReader();
        }

        return hasNext;
    }

    @Override
    public String next() {

        return this.line;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Method is not supported !");
    }

    private String read(){
        String out ="";
        try {

            out= this.bufferedReader.readLine();

        } catch (IOException e) {

            closeReader();

            throw new RuntimeException(e);
        }
        return out;
    }

    private void closeReader(){
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
        }
    }
}
