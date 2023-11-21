package work.twgj.resolverdemo.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * HTTP消息转换接口
 * @author weijie.zhu
 * @date 2023/11/16 15:13
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    /**
     * 转换器必须声明自己接受的mediaType
     * */
    public PropertiesHttpMessageConverter() {
        super(new MediaType("text","properties"));
    }

    /**
     * writeInternal为序列化过程，将响应序列化
     * */
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws HttpMessageNotWritableException, IOException {
        HttpHeaders headers = httpOutputMessage.getHeaders();

        MediaType contentType = headers.getContentType();

        Charset charset = null;
        if (contentType != null){
            charset = contentType.getCharset();
        }

        charset = charset == null?Charset.forName("UTF-8"):charset;

        OutputStream body = httpOutputMessage.getBody();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body,charset);
        properties.store(outputStreamWriter,"Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws HttpMessageNotReadableException, IOException {
        return readInternal(null,httpInputMessage);
    }

    /**
     * readInternal 为反序列化过程 HTTP请求反序列化为参数的过程
     * */
    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws HttpMessageNotReadableException, IOException {
        Properties properties = new Properties();
        // 获取请求头
        HttpHeaders headers = httpInputMessage.getHeaders();
        // 获取contentType
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;
        if (contentType != null){
            charset = contentType.getCharset();
        }

        charset = charset == null?Charset.forName("UTF-8"): charset;

        // 获取请求体
        InputStream body = httpInputMessage.getBody();
        InputStreamReader inputStreamReader = new InputStreamReader(body,charset);

        properties.load(inputStreamReader);
        return properties;
    }
}
