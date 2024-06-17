package api;

import lombok.Data;

// в этом классе делаем МОДЕЛЬ ответа(у нас нет тела запроса в гет), это создание POJO класса с полями
@Data
public class UserData {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;


}
