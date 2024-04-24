import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderСardTest {

    @Test
    void shouldPositiveCardDeliveryOrder() throws InterruptedException {
        open("http://localhost:9999/");

        LocalDate date = LocalDate.now().plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = date.format(formatter);
        $("[placeholder='Город']").setValue("Ставрополь");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(text);
        $("[data-test-id=name] [type=text]").setValue("Рубцов Максим");
        $("[name='phone']").setValue("+79200001111");
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $("[class='notification__content']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + text));
    }
}
