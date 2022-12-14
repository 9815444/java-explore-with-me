package ewm.errors;

import ewm.model.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(final NotFoundException e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.NOT_FOUND);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFound(final MissingServletRequestParameterException e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.FORBIDDEN);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFound(final MethodArgumentNotValidException e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.FORBIDDEN);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFound(final BadRequestException e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.FORBIDDEN);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleNotFound(final DataIntegrityViolationException e) {
        var apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setStatus(ApiError.Status.CONFLICT);
        apiError.setTimestamp(LocalDateTime.now());
        return apiError;
    }

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