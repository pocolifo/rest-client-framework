package com.pocolifo.restclientframework.http;

import com.pocolifo.restclientframework.RestClientFramework;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpUtility {
    public static byte[] readInputStreamToBytes(InputStream stream, boolean closeStreams) {
        assert stream != null;

        byte[] buffer = new byte[1024];
        int length;

        ByteArrayOutputStream result = new ByteArrayOutputStream();

        try {
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            byte[] bytes = result.toByteArray();

            if (closeStreams) {
                stream.close();
                stream = null;

                result.close();
                result = null;
            }

            buffer = null;

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String readByteArray(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String readInputStreamToString(InputStream inputStream, boolean closeStreams) {
        assert inputStream != null;

        return readByteArray(readInputStreamToBytes(inputStream, closeStreams));
    }

    public static HttpURLConnection getConnection(RestClientFramework framework, URL url, HttpMethod method) throws IOException {
        URLConnection connection;

        if (framework.proxy == null) {
            connection = url.openConnection();
        } else {
            connection = url.openConnection(framework.proxy);
        }

        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection https = (HttpsURLConnection) connection;

            https.setRequestMethod(method.name());

            return https;
        } else if (connection instanceof HttpURLConnection) {
            HttpURLConnection http = (HttpURLConnection) connection;

            http.setRequestMethod(method.name());

            return http;
        }

        throw new IOException("Connection was not instance of HTTP or HTTPS");
    }
}
