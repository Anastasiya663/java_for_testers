package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(6);
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesCli().addUser(email, password);
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)

        app.reg().signupNewAccount(username, email);
        //заполняем форму создания и отправляем (браузер)

        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        //ждем почту (MailHelper)

        var url = app.reg().extractUrl(messages);
        //извлекаем ссылку из письма

        app.reg().confirmReg(url, username, password);
        //проходим по ссылке и завершаем регистрацию (браузер)

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }
}
