package work.twgj.resolverdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import work.twgj.resolverdemo.converter.PropertiesHttpMessageConverter;
import work.twgj.resolverdemo.handler.PropertiesHandlerMethodReturnValueHandler;
import work.twgj.resolverdemo.resolver.PropertiesHandlerMethodArgumentResolver;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2023/11/16 15:24
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 如果需要优先使用自己定义的转换器，需要声明index,不然可能会直接使用先注册的转换器进行转换
        converters.add(0,new PropertiesHttpMessageConverter());
    }

    /**
     * 我们不能在配置类WebMvcConfigurer中通过重写addArgumentResolvers的方式来添加，
     * 通过这个方法来添加的方法参数解析器不会覆盖Spring内置的方法参数解析器，如果需要这么做的话，可以直接通过修改RequestMappingHandlerAdapter来实现。
     * */
    @PostConstruct
    public void init(){
        // 获取requestMappingHandlerAdapter中 所有的ArgumentResolver对象
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
        // 添加PropertiesHandlerMethodArgumentResolver
        newArgumentResolvers.add(0,new PropertiesHandlerMethodArgumentResolver());
        // 将原来的HandlerMethodArgumentResolver放入
        newArgumentResolvers.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(newArgumentResolvers);

        // 获取HandlerMethodReturnValueHandler中 所有的returnValueHandlers
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        // 添加PropertiesHandlerReturnValueHandler
        newReturnValueHandlers.add(0,new PropertiesHandlerMethodReturnValueHandler());
        // 将原来的HandlerReturnValueHandler放入
        newReturnValueHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }


}
