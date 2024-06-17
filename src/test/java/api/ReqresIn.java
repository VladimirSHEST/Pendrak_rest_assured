package api;
/*
1) получить список пользователей со 2 страницы по адресу https://reqres.in/api/users?page=2
2) Убедиться (сделать сравнение), что имена файлов - аватаров пользователей совпадают(проверить что совпадают id у пользователя и его аватара)
3) Убедиться, что Email пользователей имеют окончания reqres.in(это домен)
     в get запросе нет тела, работаем с ответом только с "data"; "data" содержит список пользователей, создадим этот список
     для этого создадим класс UserData и потом уже список типа UserData List<UserData> users и заполним его
 */
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresIn {
    @Test
    public void checkAvatarAndIdTest(){
        List<UserData> users = given()     // создаём список пользователей, Given() – это статичный метод в rest assured, с него начинаем писать код.
                .when()  // когда у нас есть то что ниже
                .contentType(ContentType.JSON)  // тип данных
                .get("https://reqres.in/api/users?page=2")  // куда обращаемся и тип запроса
                .then().log().all()  //  тогда делаем следующее:
                .extract().body().jsonPath()   // извлекаем ответ в поджо класс из тела через путь JSON
                .getList("data", UserData.class);  //  мы создаём лист, поэтому getList(внутри указываем путь и класс куда извлечём ответ)
// пройдём по всему списку заполненному и утвердим что в нем строка аватар содержит id как строку
        users.forEach(x-> assertTrue(x.getAvatar().contains(x.getId().toString())));
        //  forEach- проходим по всем элементам списка
//  x.getAvatar() - обращаемся к элементу из списка, а именно к аватару
//  contains(x.getId() - должен содержать id
//  .toString() - переводим  id в строку, так как сравниваем со строкой
        //еееее
    }

}
