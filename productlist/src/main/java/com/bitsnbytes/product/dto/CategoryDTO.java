package com.bitsnbytes.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;


@Schema(
        name="Category",
        description = "It holds Category information along with Products"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;

}
