package com.cursedbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseDto<T> {
    PaginationDto pagination;
    T data;
}
