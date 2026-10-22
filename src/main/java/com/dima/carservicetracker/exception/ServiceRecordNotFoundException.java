package com.dima.carservicetracker.exception;

import org.springframework.http.HttpStatus;
// HttpStatus содержит стандартные HTTP-статусы:
// 200 OK, 201 CREATED, 404 NOT_FOUND, 500 INTERNAL_SERVER_ERROR и т.д.

import org.springframework.web.bind.annotation.ResponseStatus;
// @ResponseStatus позволяет связать Java exception с HTTP-статусом.
// Еесли этот exception будет выброшен,
// Spring вернёт клиенту указанный HTTP status.

@ResponseStatus(HttpStatus.NOT_FOUND)
// Если ServiceRecordNotFoundException будет выброшен,
// Spring вернёт HTTP 404 Not Found.

// Создаём свой собственный тип ошибки.
public class ServiceRecordNotFoundException extends RuntimeException{
    // extends RuntimeException означает:
    // ServiceRecordNotFoundException является разновидностью RuntimeException
    // и наследует от неё стандартное поведение исключения.
     public ServiceRecordNotFoundException(Long id){
         super("Service record with " + id + " not found");
         // super(...) вызывает конструктор родительского класса RuntimeException.
         // super означает: Обратись к родительскому классу

     }
}
