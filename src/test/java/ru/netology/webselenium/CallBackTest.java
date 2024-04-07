package ru.netology.webselenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void nameIncorrect2() {
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("Ivan Ivanovich");
        form.$("[data-test-id=phone] input").setValue("+79808585555");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText
                        ("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void incorrectPhoneNumber() {
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("79808585555");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=phone].input_invalid .input__sub")
                .shouldHave(exactText
                        ("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void InvalidNameSurnameTest1() {
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("");
        form.$("[data-test-id=phone] input").setValue("+79808585555");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=name].input_invalid .input__sub")
                .shouldHave(exactText
                        ("Поле обязательно для заполнения"));
    }

    @Test
    void InvalidPhoneTest2() {
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText
                        ("Поле обязательно для заполнения"));
    }

    @Test
    void EmptyCheckbox() {
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("+79808585555");
        form.$(".button").click();
        form.$("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText
                        ("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }


    @Test
    void agreementNotMarked(){
        open ("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input")
                .setValue("Иваныч-Ивановичев Иван");
        form.$("[data-test-id=phone] input").setValue("+79808585555");
        form.$(".button").click();
        form.$("[data-test-id=agreement].input_invalid")
                .shouldHave(visible);

    }
}
