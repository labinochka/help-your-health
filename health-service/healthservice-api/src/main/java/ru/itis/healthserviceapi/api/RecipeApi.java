package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;

import java.util.UUID;

@Tags(value = {
        @Tag(name = "Recipes")
})
@Schema(description = "Работа с рецептами")
@RequestMapping("/api/v1/recipe")
public interface RecipeApi {

    @Operation(summary = "Создание рецепта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody RecipeRequest request);

    @Operation(summary = "Получение всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<RecipeResponse> findAll(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "10") int limit);

    @Operation(summary = "Получение рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт получен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    RecipeResponse findById(@PathVariable("id") UUID id);

    @Operation(summary = "Получение рецепта по названию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/findByTitle/{title}")
    @ResponseStatus(HttpStatus.OK)
    Page<RecipeResponse> findByTitle(@PathVariable("title") String title,
                               @RequestParam(defaultValue = "0") int offset,
                               @RequestParam(defaultValue = "10") int limit);

    @Operation(summary = "Получение рецепта по категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/findByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    Page<RecipeResponse> findByCategory(@PathVariable("category") String category,
                                        @RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit);

    @Operation(summary = "Получение рецепта по времени готовки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/findByCookingTime/{cookingTime}")
    @ResponseStatus(HttpStatus.OK)
    Page<RecipeResponse> findByCookingTime(@PathVariable("cookingTime") int cookingTime,
                                        @RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit);

    @Operation(summary = "Обновление рецепта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт обновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable("id") UUID id, @RequestBody RecipeRequest request);

    @Operation(summary = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@PathVariable("id") UUID id);
}
