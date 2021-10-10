package com.pocolifo.restclientframework.request.methods.post.input;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.UUID;

public class MultipartPostRequestBuilder implements IPostRequestInput {
    private final Charset charset;
    private final String bounds;

    private final ByteArrayOutputStream stream;

    public MultipartPostRequestBuilder(Charset charset) {
        this.charset = charset;
        this.bounds = UUID.randomUUID().toString();
        this.stream = new ByteArrayOutputStream();

        this.begin();
    }

    private void begin() {
        try {
            this.stream.write(("--" + this.bounds).getBytes(this.charset));
            this.closeField();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFieldEnd() {
        try {
            this.stream.write("\r\n\r\n".getBytes(this.charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFieldKey(String name) {
        try {
            this.stream.write(("Content-Disposition: form-data; name=\"" + URLEncoder.encode(name, this.charset.name()) + "\"").getBytes(this.charset));
            this.writeFieldEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFileKey(String name, File file) {
        try {
            this.stream.write(("Content-Disposition: form-data; name=\"" + URLEncoder.encode(name, this.charset.name()) + "\"; filename=\"" + URLEncoder.encode(file.getName(), this.charset.name()) + "\"").getBytes(this.charset));
            this.writeFieldEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileToStream(File file, int bufferSize) {
        try (InputStream stream = new FileInputStream(file)) {
            byte[] buffer = new byte[bufferSize];

            for (int i = 0; i >= 0; i = stream.read(buffer)) {
                this.stream.write(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeField() {
        try {
            this.stream.write("\r\n".getBytes(this.charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultipartPostRequestBuilder addField(String name, String value) {
        try {
            this.addFieldKey(name);
            this.stream.write(URLEncoder.encode(value, this.charset.name()).getBytes(this.charset));
            this.closeField();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public MultipartPostRequestBuilder addField(String name, File file) {
        assert file.isFile();

        this.addFileKey(name, file);
        this.readFileToStream(file, 2048);
        this.closeField();

        return this;
    }

    public MultipartPostRequestBuilder build() {
        try {
            this.stream.write(("--" + this.bounds + "--").getBytes(this.charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public byte[] getContents() {
        try {
            this.stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.stream.toByteArray();
    }

    @Override
    public String getContentType() {
        return "multipart/form-data; charset=" + this.charset.name() + "; boundary=" + this.bounds;
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }
}
