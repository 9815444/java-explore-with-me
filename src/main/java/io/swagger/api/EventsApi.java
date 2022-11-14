/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.36).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ApiError;
import io.swagger.model.EventFullDto;
import io.swagger.model.EventShortDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-11-13T14:31:14.091Z[GMT]")
@Validated
public interface EventsApi {

    @Operation(summary = "Получение подробной информации об опубликованном событии по его идентификатору", description = "Обратите внимание: - событие должно быть опубликовано - информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики", tags={ "Public: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Событие найдено", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventFullDto.class))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/events/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<EventFullDto> getEvent1(@Parameter(in = ParameterIn.PATH, description = "id события", required=true, schema=@Schema()) @PathVariable("id") Long id);


    @Operation(summary = "Получение событий с возможностью фильтрации", description = "Обратите внимание:  - это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события - текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв - если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени - информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие - информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики", tags={ "Public: События" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "События найдены", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EventShortDto.class)))),
        
        @ApiResponse(responseCode = "400", description = "Запрос составлен с ошибкой", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "403", description = "Не выполнены условия для совершения операции", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "404", description = "Объект не найден", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "409", description = "Запрос приводит к нарушению целостности данных", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))) })
    @RequestMapping(value = "/events",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<EventShortDto>> getEvents1(@Parameter(in = ParameterIn.QUERY, description = "текст для поиска в содержимом аннотации и подробном описании события" ,schema=@Schema()) @Valid @RequestParam(value = "text", required = false) String text, @Parameter(in = ParameterIn.QUERY, description = "список идентификаторов категорий в которых будет вестись поиск" ,schema=@Schema()) @Valid @RequestParam(value = "categories", required = false) List<Long> categories, @Parameter(in = ParameterIn.QUERY, description = "поиск только платных/бесплатных событий" ,schema=@Schema()) @Valid @RequestParam(value = "paid", required = false) Boolean paid, @Parameter(in = ParameterIn.QUERY, description = "дата и время не раньше которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeStart", required = false) String rangeStart, @Parameter(in = ParameterIn.QUERY, description = "дата и время не позже которых должно произойти событие" ,schema=@Schema()) @Valid @RequestParam(value = "rangeEnd", required = false) String rangeEnd, @Parameter(in = ParameterIn.QUERY, description = "только события у которых не исчерпан лимит запросов на участие" ,schema=@Schema( defaultValue="false")) @Valid @RequestParam(value = "onlyAvailable", required = false, defaultValue="false") Boolean onlyAvailable, @Parameter(in = ParameterIn.QUERY, description = "Вариант сортировки: по дате события или по количеству просмотров" ,schema=@Schema(allowableValues={ "EVENT_DATE", "VIEWS" }
)) @Valid @RequestParam(value = "sort", required = false) String sort, @Parameter(in = ParameterIn.QUERY, description = "количество событий, которые нужно пропустить для формирования текущего набора" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "from", required = false, defaultValue="0") Integer from, @Parameter(in = ParameterIn.QUERY, description = "количество событий в наборе" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size);

}

