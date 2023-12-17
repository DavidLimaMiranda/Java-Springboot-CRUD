package com.example.crud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductDto(

        String id,

        @NotBlank
        String name,

        @NotNull
        Integer prince_in_cents)
{ }
