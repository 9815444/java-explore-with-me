package ewm.api;

import ewm.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleNotFound(final Exception e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.FORBIDDEN);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

}