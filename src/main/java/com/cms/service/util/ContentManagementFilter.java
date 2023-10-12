package com.cms.service.util;

import com.cms.service.common.ErrorCode;
import com.cms.service.exceptions.CustomException;
import io.micronaut.core.annotation.Order;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.filter.ServerFilterPhase;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;

@Singleton
@Slf4j
@Order(1)
@Filter(Filter.MATCH_ALL_PATTERN)
public class ContentManagementFilter implements HttpServerFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContentManagementFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        logger.info("CustomFilter is processing the request.");
        HttpHeaders headers = request.getHeaders();
        String authorizationHeader = headers.get(AUTHORIZATION_HEADER);
        if (authorizationHeader.isEmpty()) {
        throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        return chain.proceed(request);
    }

    @Override
    public int getOrder() {
        return ServerFilterPhase.SECURITY.order();
    }
}
