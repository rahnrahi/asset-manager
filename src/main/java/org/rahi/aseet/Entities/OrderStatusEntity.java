package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "OrderStatus")
@Data
@EqualsAndHashCode(callSuper=false, of = "orderStatusId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderStatusEntity extends BaseEntity{
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID orderStatusId;

    private String statusName;

}
