package ad.uda.tprats.workitapi.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

