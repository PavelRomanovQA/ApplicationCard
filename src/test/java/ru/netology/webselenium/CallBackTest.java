package ru.netology.webselenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {

    @Test
    void shouldPost() {
        //throw new UnsupportedOperationException();
        //загружаем страницу
        open("http://localhost:9999");
        //ищем эелементы
        SelenideElement form = $("form");
        //взаимодействуем с элементами
        form.$("[data-test-id=name] input").setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("+79808585555");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldntPostEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue(" ");
        form.$("[data-test-id=phone] input").setValue("+798008585555");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText
                ("Поле обязательно для заполнения"));
    }


    @Test
    void shouldntPostEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText
                ("Поле обязательно для заполнения"));
    }
}