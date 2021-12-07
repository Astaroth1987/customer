package com.nttdata.customer.infraestructure.model.dao;

import com.nttdata.customer.domain.CustomerType;
import com.nttdata.customer.domain.DocumentType;
import com.nttdata.customer.domain.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


/**
 * CUSTOMERDAO.
 * Contiene los atributos del documento Cliente (Customer) para la persistencia
 */
@Data
@Document("customer")
public class CustomerDao {
    /**
     * Codigo del cliente.
     */
    @Id
    private String code;
    /**
     * Tipo de cliente.
     */
    @NotNull(message = "Debe ingresar el tipo de cliente")
    private CustomerType customerType;
    /**
     * Tipo de documento.
     */
    @NotNull(message = "Debe ingresar el tipo de documento")
    private DocumentType documentType;
    /**
     * Numero de documento.
     */
    @NotNull(message = "Debe ingresar el número de documento")
    private String documentNumber;
    /**
     * Nombre del cliente.
     */
    private String name;
    /**
     * Razon social del cliente.
     */
    private String businessName;
    /**
     * Estado: Activo, Inactivo.
     */
    private Status state;
}
