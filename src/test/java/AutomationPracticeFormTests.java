import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests {

    //~values
    private final String firstName = "Anna";
    private final String lastName = "Karenina";
    private final String userEmail = "anna@karenina.com";
    private final String userNumber = "9031112233";
    private final String subjectsInput = "Maths";
    private final String subjectsInput2 = "Computer Science";
    private final String picDirectory = "C:/";                    // Для теста размещал картинку на рабочем столе, по адресу: "C:/Users/<USER_NAME>/Desktop/"
    private final String picName = "ava.png";
    private final String currentAddress = "Moscow, Russia";


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 8000;
    }


    @Test
    void myFormTest() {
        open("/automation-practice-form");

        dataFilling();
        dataCheck();
    }


    private void dataFilling() {

        //name (first + second)
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        //email
        $("#userEmail").setValue(userEmail);

        //gender
        $("#gender-radio-2").parent().click();      //оба варианта работают, как правильней?
        $("[for=gender-radio-2]").click();          //оба варианта работают, как правильней?

        //mobile num
        $("#userNumber").setValue("9031112233");

        //date of birth
        $("#dateOfBirthInput").click();                                                //open listbox
        $(".react-datepicker__year-select").$("option[value='1991']").click();      //choose year
        $(".react-datepicker__month-select").$("option[value='6']").click();        //choose month
        $(".react-datepicker__day--031").click();                                      //choose day

        //subjects
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $("#subjectsInput").setValue(subjectsInput2).pressEnter();

        //hobbies
        $("#hobbies-checkbox-2").parent().click();     //оба варианта работают, как правильней?
        $("[for=hobbies-checkbox-3]").click();         //оба варианта работают, как правильней?

        //picture
        $("#uploadPicture").uploadFile(new File(picDirectory + picName));

        //address
        $("#currentAddress").setValue(currentAddress);

        //state
        $("#state").click();
        $("#react-select-3-option-2").click();      //честно списал, сам не смог "докопаться" до этого селектора - в инспекторе при клике на нужные блок (чтобы его раскрыть) слетает фокус с выпадающей менюшки, и сам блок пропадает.

        //city
        $("#city").click();
        $("#react-select-4-option-1").click();      //честно списал, сам не смог "докопаться" до этого селектора - в инспекторе при клике на нужные блок (чтобы его раскрыть) слетает фокус с выпадающей менюшки, и сам блок пропадает.

        //submit
        $("#submit").click();
    }

    private void dataCheck() {

        $(".table-responsive").shouldHave(text(firstName));
        $(".table-responsive").shouldHave(text(lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("9031112233"));
        $(".table-responsive").shouldHave(text("31 July,1991"));
        $(".table-responsive").shouldHave(text(subjectsInput + ", " + subjectsInput2));
        $(".table-responsive").shouldHave(text("Reading, Music"));
        $(".table-responsive").shouldHave(text(picName));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text("Haryana Panipat"));
    }
}
