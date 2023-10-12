package com.cms.service.exception.handler;

import com.cms.service.dto.response.ApiError;
import com.cms.service.dto.response.CommonResponse;
import com.cms.service.exceptions.CustomException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { CustomException.class, ExceptionHandler.class })
public class InternalServerErrorHandler implements ExceptionHandler<CustomException, HttpResponse<CommonResponse<String>>> {

    @Override
    public HttpResponse<CommonResponse<String>> handle(HttpRequest request, CustomException exception) {
        ApiError apiError = new ApiError(1001, "Something went wrong. Please try again in sometime.");
        return HttpResponse.badRequest(new CommonResponse<>(false, null, apiError));
    }
}
