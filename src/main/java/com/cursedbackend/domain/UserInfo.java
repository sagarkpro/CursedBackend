package com.cursedbackend.domain;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record UserInfo(
        String email,
        String aud,
        String role,
        String azp,
        String scope,
        String iss,
        String name,
        Instant exp,
        Instant iat,
        String jti,
        String picture) {

    public static UserInfo from(Map<String, Object> claims) {
        var roles = (List<String>) claims.get("https://sudox1.com/roles");
        var auds = (List<String>) claims.get("aud");
        return new UserInfo(
                (String) claims.get("sub"),
                auds == null || auds.isEmpty() ? null : auds.get(0),
                roles == null || roles.isEmpty() ? null : roles.get(0),
                (String) claims.get("azp"),
                (String) claims.get("scope"),
                (String) claims.get("iss"),
                (String) claims.get("name"),
                (Instant) claims.get("exp"),
                (Instant) claims.get("iat"),
                (String) claims.get("jti"),
                (String) claims.get("picture"));
    }

}
