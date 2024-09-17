package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormWithPageObjectsTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
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
        $(".table-responsive").$(byTagAndText("td", "Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byTagAndText("td", "Student Email")).sibling(0).shouldHave(text(userEmail));
        $(".table-responsive").$(byTagAndText("td", "Gender")).sibling(0).shouldHave(text(gender));
        $(".table-responsive").$(byTagAndText("td", "Mobile")).sibling(0).shouldHave(text(phoneNumber));
        $(".table-responsive").$(byTagAndText("td", "Date of Birth")).sibling(0).shouldHave(text("31 July,1991"));
        $(".table-responsive").$(byTagAndText("td", "Subjects")).sibling(0).shouldHave(text(subjectsInput + ", " + subjectsInput2));
        $(".table-responsive").$(byTagAndText("td", "Hobbies")).sibling(0).shouldHave(text(hobby + ", " + hobby2));
        $(".table-responsive").$(byTagAndText("td", "Picture")).sibling(0).shouldHave(text(picName));
        $(".table-responsive").$(byTagAndText("td", "Address")).sibling(0).shouldHave(text(currentAddress));
        $(".table-responsive").$(byTagAndText("td", "State and City")).sibling(0).shouldHave(text(state + " " + city));
    }
}