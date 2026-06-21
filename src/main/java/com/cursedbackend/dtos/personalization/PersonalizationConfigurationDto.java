package com.cursedbackend.dtos.personalization;

import java.util.UUID;

import com.cursedbackend.entities.enums.ShortcutType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonalizationConfigurationDto {
    UUID id;

    @NotNull
    ShortcutType type;

    @NotBlank
    String name;

    @NotBlank
    String url;

    String image;
}
