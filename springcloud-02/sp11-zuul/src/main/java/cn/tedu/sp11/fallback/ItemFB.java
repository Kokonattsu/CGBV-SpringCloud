package cn.tedu.sp11.fallback;

import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;

@Component
public class ItemFB implements FallbackProvider {
    /**
     * 针对哪个服务进行降级
     * @return  serviceid
     *      返回“*”，null，表示对所有服务都进行降级
     */
    @Override
    public String getRoute() {
        return null;
    }

    /**
     * 降级返回的响应对象response
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {


        ClientHttpResponse response = new ClientHttpResponse() {
            //响应头
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(
                        "Content-Type",
                        "application/json,charset=UTF-8");
                return httpHeaders;
            }
            //响应体
            @Override
            public InputStream getBody() throws IOException {

                String json = JsonResult.err().msg("远程调用失败.zuul").toString();
                //根据服务返回不同的降级代码
                if ("item-service".equalsIgnoreCase(route)){
                    json = JsonResult.err().msg("远程调用item-service失败.zuul").toString();
                }
                if ("user-service".equalsIgnoreCase(route)){
                    json = JsonResult.err().msg("远程调用user-service失败.zuul").toString();
                }
                if ("order-service".equalsIgnoreCase(route)){
                    json = JsonResult.err().msg("远程调用失败order-service.zuul").toString();
                }
                ByteArrayInputStream inputStream =
                        new ByteArrayInputStream(json.getBytes());

                return inputStream;
            }
            //返回200 ok
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
            //返回200
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }
            //返回ok
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }
            //
            @Override
            public void close() {

            }
        };


        return response;
    }
}
