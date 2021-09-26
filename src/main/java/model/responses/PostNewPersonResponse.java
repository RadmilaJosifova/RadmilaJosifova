package model.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.PersonalData;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)

public class PostNewPersonResponse {

    private String code;
    private String message;
    private PersonalData personData;


}
