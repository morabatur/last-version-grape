package co.maxbi.text;

import java.io.*;

/**
 * Клас для декодування вхідного потоку даних зі специфічним кодування в звичний строковий формат
 */
public class Decoder {

    public String read(InputStream inputStream, String encoding) {
        StringBuilder out = new StringBuilder();
        char buf[] = new char[1024];
        Reader reader = null;
        try {
            reader = new InputStreamReader(inputStream, encoding);
            for (int i = reader.read(buf); i >= 0; i = reader.read(buf)) {
                out.append(buf, 0, i);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String result = out.toString();
        return result;
    }
}
