package com.nttdata.customer.domain;

import lombok.Data;

/**
 * CUSTOMER: La clase customer contendrá  información de los clientes Personales y Empresariales
 */
@Data
public class Customer {
    private String code;
    private CustomerType customerType;      // Tipo de cliente
    private DocumentType documentType;      // Tipo de documento
    private String documentNumber;          // Numero de documento
    private String name;                    // Nombre del cliente
    private String businessName;            // Razon social del cliente
    private Status state;                   // Estado: Activo, Inactivo
}
