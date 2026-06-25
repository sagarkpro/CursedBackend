package com.cursedbackend.domain;

import com.cursedbackend.entities.PersonalizationConfiguration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateReorderShortcutRes {
    boolean valid;
    String error;
    PersonalizationConfiguration current;
    PersonalizationConfiguration prev;
    PersonalizationConfiguration next;

    public static ValidateReorderShortcutRes invalid(String error) {
        return ValidateReorderShortcutRes.builder().valid(false).error(error).build();
    }
}
