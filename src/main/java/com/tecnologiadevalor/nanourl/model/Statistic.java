package com.tecnologiadevalor.nanourl.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Represents statistical information about the URL shortening service.")
public class Statistic {

    @Schema(description = "The total number of shortened URLs.", example = "150")
    private Long totalShortenedUrls;
    @Schema(description = "The total number of times shortened URLs have been accessed.", example = "4500")
    private Long totalRedirects;
    @Schema(description = "The total number of active URL.", example = "100")
    private Long activeUrls;
    @Schema(description = "The total number of expired URL.", example = "50")
    private Long expiredUrls;
}
