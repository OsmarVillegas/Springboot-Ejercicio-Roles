package com.mascotas.sda.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mascotas.sda.dto.ConvocatoriaDto;
import com.mascotas.sda.exception.LocalNotFoundException;
import com.mascotas.sda.exception.ObjectNotFoundException;
import com.mascotas.sda.exception.PatternValidationException;
import com.mascotas.sda.persistencia.entity.Convocatoria;
import com.mascotas.sda.service.ConvocatoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/convocatoria")
public class ConvocatoriaController {
    @Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping
    public ResponseEntity<Page<Convocatoria>> findAll(Pageable pageable) {
        Page<Convocatoria> convocatoria = convocatoriaService.findAll(pageable);
        if (convocatoria.hasContent()) {
            return ResponseEntity.ok(convocatoria);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{convocatoriaId}")
    public ResponseEntity<Convocatoria> findOneById(
            @PathVariable(name = "convocatoriaId", required = true) Integer convocatoriaId) throws LocalNotFoundException {

        Optional<Convocatoria> convocatoria = convocatoriaService.findOneById(convocatoriaId);
        if (convocatoria.isPresent()) {
            return ResponseEntity.ok(convocatoria.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Convocatoria> createOne(
            @RequestBody @Valid ConvocatoriaDto convocatoriaDto) {
        Convocatoria convocatoria = convocatoriaService.createOne(convocatoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convocatoria);
    }

    @PutMapping("/{convocatoriaId}")
    public ResponseEntity<Convocatoria> updateOneById(
            @PathVariable Integer convocatoriaId,
            @RequestBody @Valid ConvocatoriaDto convocatoriaDto) throws ObjectNotFoundException {
        Convocatoria convocatoria = convocatoriaService.updateOneById(
                convocatoriaDto, convocatoriaId);

        return ResponseEntity.ok(convocatoria);

    }

    @DeleteMapping("/{convocatoriaId}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Integer convocatoriaId) {
        {
            try {
                convocatoriaService.deleteMascotas(convocatoriaId);
                return new ResponseEntity<>("Convocatoria eliminada exitosamente", HttpStatus.OK);
            } catch (LocalNotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        // Aquí puedes verificar si la excepción fue causada por un enum no válido
        if (ex.getRequiredType().isEnum()) {
            return new ResponseEntity<>("Valor no válido para el enum: " + ex.getValue(), HttpStatus.BAD_REQUEST);
        }
        // Para otros tipos de errores de tipo, puedes devolver un mensaje genérico o
        // manejarlos de manera diferente
        return new ResponseEntity<>("Error de tipo de argumento", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatternValidationException.class)
    public ResponseEntity<Map<String, String>> handlePatternValidationExceptions(PatternValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
