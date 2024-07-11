package pendrak.api;
/*
Ссылка на репо в гите https://github.com/penolegrus/Youtube-qa-project/blob/main/src/test/java/api/reqres/ReqresPojoTest.java
1) получить список пользователей со 2 страницы по адресу https://reqres.in/api/users?page=2
2) Убедиться (сделать сравнение), что имена файлов - аватаров пользователей совпадают(проверить что совпадают id у пользователя и его аватара)
3) Убедиться, что Email пользователей имеют окончания reqres.in(это домен)
     в get запросе нет тела, работаем с ответом только с "data"; "data" содержит список пользователей, создадим этот список
     для этого создадим класс UserData и потом уже список типа UserData List<UserData> users и заполним его
 */
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static groovy.xml.Entity.lt;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.STREAM;
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
                .getList("data", UserData.class);  //  мы создаём лист, поэтому пишем getList(и внутри указываем путь JSON и класс куда извлечём ответ)

            // 2-е задание пройдём по всему списку заполненному и утвердим что в нем строка аватар содержит id как строку

        users.forEach(x-> assertTrue(x.getAvatar().contains(x.getId().toString())));

//  x.getAvatar() - обращаемся к элементу из списка, а именно к аватару
//  contains(x.getId() - должен содержать id
//  .toString() - переводим  id в строку, так как сравниваем со строкой

            // 3-е задание, что у емайлов окончание "@reqres.in"
        assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

// allMatch() проверяет что все элементы в потоке соответствуют правилу и возр тру, если хоть одна не прошла то фолс
// endsWith() в Java - проверить заканчивается ли строка заданным значением

           // Другой способ 2-е задание.
// Сделаем 2 списка типа String avatars и ids и сравним их при помощи цикла и ассерта,
// что в списке avatars есть строка как у списка ids.
// Берём всех пользователей users, делаем поток, преобразовываем элементы из UserData в String

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(e -> Integer.toString(e.getId())).toList();

        // перебираем наши новые списки циклом
        for (int i = 0; i < avatars.size(); i++) {
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
        }


    }


}
