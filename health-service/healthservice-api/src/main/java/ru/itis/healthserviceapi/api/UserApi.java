package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;

import java.util.UUID;

@Tag(name = "Users")
@Schema(description = "Работа с пользователем")
@RequestMapping("/api/v1/user")
public interface UserApi {
    @PostMapping
    @Operation(summary = "Создание пользователя", method = "create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserSave userSave);

    @Operation(summary = "Получение пользователя по username", method = "find-by-username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь получен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse findByUsername(@PathVariable("username") String username);

    @Operation(summary = "Обновление пользователя", method = "update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь обновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void update(@RequestBody UserUpdate userUpdate);

    @Operation(summary = "Обновление роли пользователя", method = "update-role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль Пользователя обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PatchMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    void updateRole(@RequestParam UUID id, @RequestParam String roleType);

    @Operation(summary = "Удаление пользователя по username", method = "delete-by-username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@PathVariable("id") UUID id);
}
