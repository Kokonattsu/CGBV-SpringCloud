package cn.tedu.sp11.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {
    //设置过滤器的类型：pre/routing/post/error
    @Override
    public String filterType() {
        //等同于return "pre"；
        return FilterConstants.PRE_TYPE;
    }
    //设置过滤器的顺序号
    @Override
    public int filterOrder() {
        return 6;
    }
    //对当前请求进行判断，是执行过滤代码（true执行）
    @Override
    public boolean shouldFilter() {
        //例：调用item-service时调用过滤代码

        //获取访问服务的服务名
        RequestContext context = RequestContext.getCurrentContext();
        String serviceId= (String)context.get(FilterConstants.SERVICE_ID_KEY);

        //判断是不是商品服务的请求，忽略大小写判断
        return "item-service".equalsIgnoreCase(serviceId);
    }
    //过滤代码
    @Override
    public Object run() throws ZuulException {
        //判断请求是否有token
        //获取request
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //
        String token = request.getParameter("token");
        //判断是否存在token，isBlank是否空白
        if(StringUtils.isBlank(token)){
            //不再向后台转发
            context.setSendZuulResponse(false);
            //设置向客户端发送的响应
            context.addZuulResponseHeader(
                    "Content-Type", "application/json");

            context.getResponse().setCharacterEncoding("UTF8");

            context.setResponseBody(
                    JsonResult.err().msg("你没有登录").toString());

        }
        //当前版本中这个返回值没有任何用处（2.2.6）
        return null;
    }
}
