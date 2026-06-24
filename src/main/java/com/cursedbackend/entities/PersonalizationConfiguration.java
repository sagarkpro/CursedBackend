package com.cursedbackend.entities;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.cursedbackend.entities.enums.ShortcutType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "personalization_configurations", schema = "cursed", uniqueConstraints = @UniqueConstraint(name = "uq_personalization_configurations_user_email_rank", columnNames = {
        "user_email", "rank" }))
public class PersonalizationConfiguration extends BaseEntity {
    @Column(name = "user_email", nullable = false, length = 100)
    String userEmail;

    @Column(nullable = false, columnDefinition = "cursed.shortcut_type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    ShortcutType type;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, length = 2048)
    String url;

    @Column(length = 2048)
    String image;

    @Column(length = 255, nullable = false)
    String rank;
}
