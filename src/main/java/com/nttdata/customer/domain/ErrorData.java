package com.nttdata.customer.domain;

import lombok.Data;
/**
 * ErrorData.
 * La clase ErrorData contendrá  información de los errores
 * en la validación.
 */
@Data
public class ErrorData {
    /**
     * Nombre del campo de error.
     */
    private String name;
    /**
     * Descripción del error.
     */
    private String description;

}
