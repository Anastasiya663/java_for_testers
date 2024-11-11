package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.MailMessage;
import java.util.List;
import java.util.regex.Pattern;

public class RegHelper extends HelperBase {
    public RegHelper(ApplicationManager manager) {
        super(manager);
    }

    public void signupNewAccount (String username, String email) {
        click(By.xpath("//a[@href='signup_page.php']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public String extractUrl(List<MailMessage> messages) {
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = "";
        if(matcher.find()) {
           url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }

    public void confirmReg(String url, String username, String password) {
        manager.driver().get(url);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type = 'submit']"));
    }
}
