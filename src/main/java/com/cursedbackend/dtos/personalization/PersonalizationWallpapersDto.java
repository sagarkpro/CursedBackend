package com.cursedbackend.dtos.personalization;

import com.cursedbackend.entities.enums.ShortcutType;

import jakarta.validation.constraints.Email;
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
public class PersonalizationWallpapersDto {
    @NotBlank
    @Email
    String email;

    @NotNull
    ShortcutType type;

    @NotBlank
    String name;

    @NotBlank
    String url;

    String image;
}
