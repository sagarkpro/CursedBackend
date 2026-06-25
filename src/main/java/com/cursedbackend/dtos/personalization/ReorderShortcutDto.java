package com.cursedbackend.dtos.personalization;

import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReorderShortcutDto {
    @NotNull
    UUID id;
    @Nullable
    UUID prev;
    @Nullable
    UUID next;
}
