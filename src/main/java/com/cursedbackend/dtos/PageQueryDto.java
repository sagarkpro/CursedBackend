package com.cursedbackend.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageQueryDto {
    @NotNull
    @Min(0)
    private Integer pageNum;

    @NotNull
    @Min(5)
    @Max(50)
    private Integer pageSize;
}
