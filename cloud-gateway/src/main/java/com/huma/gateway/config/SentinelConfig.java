package com.huma.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hudenian
 * @date 2021/12/17
 */
@Configuration
public class SentinelConfig {

    private static final int COUNT = 10;
    private static final int INTERVAL_SEC = 10;

    final List<ViewResolver> viewResolvers;
    final ServerCodecConfigurer serverCodecConfigurer;

    public SentinelConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 自定义异常处理，替换 SentinelGatewayBlockExceptionHandler
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        CustomExceptionHandler customCallbackExceptionHandler = new CustomExceptionHandler();
        customCallbackExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        customCallbackExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        customCallbackExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return customCallbackExceptionHandler;
    }

    /**
     * 配置SentinelGatewayFilter
     */
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 自定义 token 过滤器
     */
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    /**
     * 灰度 gray 过滤器
     */
    @Bean
    public GrayFilter grayFilter() {
        return new GrayFilter();
    }

    @PostConstruct
    public void doInit() {
        initGatewayRules();
    }

    /**
     * 初始化限流规则（或在 dashboard 中配置）
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("cloud-api")
                // 限流阈值 QPS，默认 10
                .setCount(COUNT)
                // 统计时间窗口，单位是秒，默认 10 秒
                .setIntervalSec(INTERVAL_SEC)
        );
        GatewayRuleManager.loadRules(rules);
    }
}
