package com.nttdata.customer.infraestructure.model.dao;

import com.nttdata.customer.domain.CustomerType;
import com.nttdata.customer.domain.DocumentType;
import com.nttdata.customer.domain.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * CUSTOMERDAO: Contiene los atributos del documento Cliente (Customer) para la persistencia
 */
@Data
@Document("customer")
public class CustomerDao {
    @Id
    private String code;
    private CustomerType customerType;      // Tipo de cliente
    private DocumentType documentType;      // Tipo de documento
    private String documentNumber;          // Numero de documento
    private String name;                    // Nombre del cliente
    private String businessName;            // Razon social del cliente
    private Status state;                   // Estado: Activo, Inactivo
}
