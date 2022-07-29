package ru.netology.delivery.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import ru.netology.delivery.data.RegistrationByCardInfo;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.data.DataGenerator.fakeUser;
import static ru.netology.delivery.data.DataGenerator.regNewUser;
import static java.time.Duration.ofSeconds;

public class TestModeCardDeliveryTest {
    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
    }

    @Test
    void shouldTestWithActiveUser() {
        RegistrationByCardInfo info = regNewUser("active");
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Личный кабинет")).shouldBe(visible, ofSeconds(15));
    }

    @Test
    void shouldTestWithBlockedUser() {
        RegistrationByCardInfo info = regNewUser("blocked");
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Пользователь заблокирован")).shouldBe(visible, ofSeconds(15));
    }

    @Test
    void shouldTestWithInvalidPassword() {
        RegistrationByCardInfo info = regNewUser("active");
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(fakeUser().getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Ошибка")).shouldBe(visible, ofSeconds(15));
    }

    @Test
    void shouldTestWithInvalidLogin() {
        RegistrationByCardInfo info = regNewUser("active");
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(fakeUser().getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Ошибка")).shouldBe(visible, ofSeconds(15));
    }

    @Test
    void shouldTestWithInvalidLoginAndPassword() {
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(fakeUser().getLogin());
        $("[data-test-id='password'] input").setValue(fakeUser().getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Ошибка")).shouldBe(visible, ofSeconds(15));
    }

    @Test
    void shouldTestWithEmptyForms() {
        open("http://localhost:9999/");
        $("[data-test-id='action-login'] .button__content").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible, ofSeconds(15));
    }
}
