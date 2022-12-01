package com.jinchange.gzip.filter;

import com.jinchange.gzip.util.GZIPUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @ClassName: UnZIPRequestWrapper
 * @Author zhangjin
 * @Date 2022/3/26 11:02
 * @Description: JsonString经过压缩后保存为二进制文件 -> 解压缩后还原成JsonString转换成byte[] 写回body中
 */
@Slf4j
public class UnZIPRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] bytes;

    public UnZIPRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        try (BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            final byte[] body;
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            body = baos.toByteArray();
            if (body.length == 0) {
                log.info("Body无内容，无需解压");
                bytes = body;
                return;
            }
            this.bytes = GZIPUtils.uncompressToByteArray(body);
        } catch (IOException ex) {
            log.info("解压缩步骤发生异常！");
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}
