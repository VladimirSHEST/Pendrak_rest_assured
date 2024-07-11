package pendrak.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// в этом классе делаем МОДЕЛЬ ответа(у нас нет тела запроса в гет), это создание POJO класса с полями
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(value = {"first_name","last_name"})
//@JsonIgnoreProperties(ignoreUnknown = true)

public class UserData {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
