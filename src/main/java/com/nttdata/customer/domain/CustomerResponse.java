package com.nttdata.customer.domain;

import lombok.Data;

import java.util.List;
/**
 * CustomerResponse.
 * La clase CustomerResponse contendrá  información de los clientes Personales
 * y Empresariales
 */
@Data
public class CustomerResponse  extends  Customer {
    /**
     * Lista de errores.
     */
    private List<ErrorData> errors;
}
