package com.example.application.views.клиент;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.example.application.model.LoanCalculator;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Клиент")
public class ClientView extends Div {

    private final TextField surname = new TextField("Фамилия");
    private final TextField name = new TextField("Имя");
    private final TextField patronymic = new TextField("Отчество");
    private final PhoneNumberField phone = new PhoneNumberField("Номер телефона");
    private final EmailField email = new EmailField("адрес электронной почты ");
    private final IntegerField passportSeries = new IntegerField ("Серия паспорта");
    private final IntegerField passportID = new IntegerField ("Номер паспорта");
    private final CreditAmount creditAmount = new CreditAmount();
    private final CreditTerm creditTerm = new CreditTerm();

    private final Button save = new Button("Оформить заявку");

    public Button getSave() {
        save.addClickListener(x ->{ LoanCalculator LoanCalculator = new LoanCalculator();
            LoanCalculator.loanCalculator(creditAmount,creditTerm,10);
        });
        return save;
    }

    

    public ClientView() {
        addClassName("клиент-view");

        add(createTitle());
        add(createFormLayout());
        add(creditAmount);
        add(creditTerm);
        add(createButtonLayout());


    }
    

    private Component createTitle() {
        return new H3("Данные клиента");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(surname, name, patronymic, phone, email, passportSeries, passportID);
        return formLayout;
    }

    private Component createButtonLayout() {
        Button saveButton = getSave();
        HorizontalLayout buttonLayout = new HorizontalLayout(creditAmount,creditTerm);
        buttonLayout.setWidth("150px");
        buttonLayout.addClassName("button-layout");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonLayout.add(saveButton);
        return buttonLayout;
    }

    private static class PhoneNumberField extends CustomField<String> {
        private final ComboBox<String> countryCode = new ComboBox<>();
        private final TextField number = new TextField();

        public PhoneNumberField(String label) {
            setLabel(label);
            countryCode.setWidth("120px");
            countryCode.setPlaceholder("Страна");
            countryCode.setPattern("\\+\\d*");
            countryCode.setPreventInvalidInput(true);
            countryCode.setItems("+354", "+91", "+62", "+98", "+964", "+353", "+44", "+7", "+39", "+225");
            countryCode.addCustomValueSetListener(e -> countryCode.setValue(e.getDetail()));
            number.setPattern("\\d*");
            number.setPreventInvalidInput(true);
            HorizontalLayout layout = new HorizontalLayout(countryCode, number);
            layout.setFlexGrow(1.0, number);
            add(layout);
        }

        @Override
        public String generateModelValue() {
            if (countryCode.getValue() != null && number.getValue() != null) {
                String s = countryCode.getValue() + " " + number.getValue();
                return s;
            }
            return "";
        }

        @Override
        protected void setPresentationValue(String phoneNumber) {
            String[] parts = phoneNumber != null ? phoneNumber.split(" ", 2) : new String[0];
            if (parts.length == 1) {
                countryCode.clear();
                number.setValue(parts[0]);
            } else if (parts.length == 2) {
                countryCode.setValue(parts[0]);
                number.setValue(parts[1]);
            } else {
                countryCode.clear();
                number.clear();
            }
        }
    }
    @Route("Credit_Amount")
    public static class CreditAmount extends Div {

        private final NumberField numberField  ;
        public Double creditAmount(){ return numberField.getValue();}

        public CreditAmount() {
            numberField  = new NumberField ();
            numberField.setLabel("Сумма кредита");
            numberField.setHelperText("от 100 000 ₽     до 5 000 000 ₽");
            numberField.setMin(100000);
            numberField.setMax(5000000);
            numberField.setValue(100000.0);
            numberField.setStep(100000);
            numberField.setHasControls(true);
            add(numberField);
        }

    }
    @Route("creditTerm")
    public static class CreditTerm extends Div {

        private final NumberField numberField;
        public Double creditTerm(){ return numberField.getValue();}

       public CreditTerm() {
           numberField = new NumberField();
           numberField.setLabel("Срок кредитования");
           numberField.setMin(13);
           numberField.setMax(84);
           numberField.setValue(13.0);
           numberField.setStep(1);
           numberField.setHasControls(true);
            add(numberField);
        }

    }
}
