package com.ecommerce.inventoryservice.models.relation;

import com.ecommerce.inventoryservice.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "feedbacks")
public class Feedback extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "feedback_id")
    private UUID id;

    @Column(name = "feedback_content", columnDefinition = "text")
    private String content;

    @Column(name = "feedback_rating_point")
    private float ratingPoint;

    @Column(name = "feedback_owner_id")
    private UUID ownerId;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;

}
