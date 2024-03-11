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

@Entity(name = "OrderItems")
@Data
@EqualsAndHashCode(callSuper=false, of = "orderItemId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItemsEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID orderItemId;

    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private OrdersEntity order;

    private double price;

    private double quantity;
}
