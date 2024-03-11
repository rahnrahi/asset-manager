package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "CustomerAddresses")
@Data
@EqualsAndHashCode(callSuper=false, of = "addressId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerAddressesEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID addressId;

    private String addressline1;

    private String addressline2;

    private String postalCode;

    private String country;

    private String city;

    private String phone_number;

    @ManyToOne
    private UserAccountEntity user;


}
