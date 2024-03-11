package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Orders")
@Data
@EqualsAndHashCode(callSuper=false, of = "orderId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrdersEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID orderId;

    @ManyToOne
    @NotNull
    private UserAccountEntity user;

    @ManyToOne
    private UserAccountEntity approveBy;

    @ManyToOne
    @NotNull
    private OrderStatusEntity orderStatusEntity;

    @CreationTimestamp
    private LocalDateTime approvedAt;

    @CreationTimestamp
    private LocalDateTime deliveredAt;
}
