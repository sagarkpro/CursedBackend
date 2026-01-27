package com.cursedbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto{
    int currentPage;
    int totalPages;
    long totalItems;
    int itemsPerPage;
    boolean hasNext;
    boolean hasPrev;
}
