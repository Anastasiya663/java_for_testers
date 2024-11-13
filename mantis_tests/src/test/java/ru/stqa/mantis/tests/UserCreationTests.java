package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase {

    //    public static Stream<String> randomUser () {
//        return Stream.of(CommonFunctions.randomString(8));
//    }
//
//    @ParameterizedTest
//    @MethodSource("randomUser")
//    void canCreateUser (String user) {
//        var email = String.format("%s@localhost", user);
//        var password = "password";
//
//        app.jamesApi().addUser(email, password); // добавление пользователя с использованием api
//
//        app.reg().signupNewAccount(user, email);
//        //заполняем форму создания и отправляем (браузер)
//
//        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//        //ждем почту (MailHelper)
//
//        var url = app.reg().extractUrl(messages);
//        //извлекаем ссылку из письма
//
//        app.reg().confirmReg(url, user, password);
//        //проходим по ссылке и завершаем регистрацию (браузер)
//
//        app.http().login(user, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }
//    DeveloperMailUser user;

//    @Test
//    void canCreateUser() {
//        var password = "password";
//        user = app.developerMail().addUser(); // добавление пользователя через сервис DeveloperMail
//        var email = String.format("%s@developermail.com", user.name());
//
//
//        app.reg().signupNewAccount(user.name(), email);
//        //заполняем форму создания и отправляем (браузер)
//
//        var messages = app.developerMail().receive(user, Duration.ofSeconds(10));
//        //ждем почту (MailHelper)
//
//        var url = app.reg().extractUrl(messages);
//        //извлекаем ссылку из письма
//
//        app.reg().confirmReg(url, user.name(), password);
//        //проходим по ссылке и завершаем регистрацию (браузер)
//
//        app.http().login(user.name(), password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }
//
//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }
    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String user) {
        var email = String.format("%s@localhost", user);
        var password = "password";

        app.jamesApi().addUser(email, password);

        app.rest().createUser(new UserData()
                .withUsername(user)
                .withPassword(password)
                .withEmail(email));

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));

        var url = app.mail().extractUrl(messages);

        app.reg().confirmReg(url, user, password);

        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
