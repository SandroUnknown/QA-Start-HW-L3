import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// [fixed] 1. Давай пока не будем забегать вперёд и делить тест на отдельные методы. В уроке по Page Object расскажут, как сделать это правильно

// [fixed] 2. Configuration.holdBrowserOpen = true; - перед коммитом удаляем/закомменчиваем/меняем на false, особенно критично будет при удалённом запуске тестов.
// Данная конфигурация полезна при разработке и отладке тестов, но в готовом проекте её быть не должно.
// Особенно при удалённом запуске тестов - браузер останется висеть открытым и будет занимать ресурсы, которые можно было бы использовать.

// [fixed] 3. Для загрузки картинки рекомендуется использование:
// $("#uploadPicture").uploadFromClasspath(picName);
// В таком случае картинка будет подтягиваться по имени файла из папки resources, которую нужно создать на одном уровне с папкой java.
// Картинку обязательно нужно закоммитить, тесты должны работать, что называется, "из коробки", без предварительных правок с моей стороны

// [fixed] 4. В названии методов в java принято использовать глагол, также принято ставить его на первое место

// [fixed] 5. Выбор пола и хобби по порядковому номеру нестабилен.
// К порядковым номерам стоит привязываться только в том случае, если другого способа нет,
// т.к. порядок элементов на странице может поменяться в любой момент, и тесты упадут.
// Лучше сделать поиск по тексту в конкретном месте страницы, в данном случае в #genterWrapper и в #hobbiesWrapper.

// [fixed] 6. Для выбора штата и города - аналогично, поиском по тексту в $("#stateCity-wrapper")

// [in work] 7. Проверки можно улучшить, добавив проверку соответствия содержимого колонок друг другу
// (не обязательно сейчас, это можно будет сделать позже по мере развития проекта)


public class AutomationPracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 8000;
    }

    @Test
    void testForm() {
        //1. values
        String firstName = "Anna";
        String lastName = "Karenina";
        String userEmail = "anna@karenina.com";
        String gender = "Female";
        String phoneNumber = "9031112233";
        //дата рождения??
        String subjectsInput = "Maths";
        String subjectsInput2 = "Computer Science";
        String hobby = "Reading";
        String hobby2 = "Music";
        String picName = "ava.png";
        String currentAddress = "Moscow, Russia";
        String state = "Haryana";
        String city = "Panipat";

        //2. открываем url
        open("/automation-practice-form");

        //3. заполнение данными
        //name (first + second)
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        //email
        $("#userEmail").setValue(userEmail);

        //gender
        $("#genterWrapper").$(byText(gender)).click();    //в верстве опечатка - genTer.

        //mobile num
        $("#userNumber").setValue(phoneNumber);

        //date of birth
        $("#dateOfBirthInput").click();                                                //open listbox
        $(".react-datepicker__year-select").$("option[value='1991']").click();      //choose year
        $(".react-datepicker__month-select").$("option[value='6']").click();        //choose month
        $(".react-datepicker__day--031").click();                                      //choose day

        //subjects
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $("#subjectsInput").setValue(subjectsInput2).pressEnter();

        //hobbies
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();

        //picture
        $("#uploadPicture").uploadFromClasspath(picName);

        //address
        $("#currentAddress").setValue(currentAddress);

        //state
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();


        //city
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        //submit
        $("#submit").click();

        //============================================================================

        //4. проверка
        $(".table-responsive").shouldHave(text(firstName));
        $(".table-responsive").shouldHave(text(lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text(phoneNumber));
        $(".table-responsive").shouldHave(text("31 July,1991"));
        $(".table-responsive").shouldHave(text(subjectsInput + ", " + subjectsInput2));
        $(".table-responsive").shouldHave(text(hobby + ", " + hobby2));
        $(".table-responsive").shouldHave(text(picName));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(state + " " + city));
    }
}
