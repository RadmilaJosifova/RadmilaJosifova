package model.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostNewPersonRequest {

    private String name;
    private String surname;
    private Integer age;
    @JsonProperty("isEmployed")
    private Boolean isEmployed;
    private String location;
}