package kot.tret.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataResponse {

    @NotBlank
    @Size(max = 128)
    private String name;
}
