package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferHeader = $(byText("Пополнение карты"));
    private final SelenideElement transferAmount = $("[data-test-id='amount'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement transferFrom = $("[data-test-id='from'] input");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public TransferPage() {
        transferHeader.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer (String amountToTransfer, DataHelper.CardInfo cardInfo) {
        transferAmount.setValue(amountToTransfer);
        transferFrom.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage (String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}