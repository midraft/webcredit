package com.example.application.views.клиент;

import com.example.application.data.entity.SamplePerson;
import com.example.application.data.service.SamplePersonService;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Клиент")
public class ClientView extends Div {

    private TextField surname = new TextField("Фамилия");
    private TextField name = new TextField("Имя");
    private TextField patronymic = new TextField("Отчество");
    private EmailField email = new EmailField("Email адрес");
    private PhoneNumberField phone = new PhoneNumberField("Номер телефона");


    private Button save = new Button("Оформить заявку");

    private Binder<SamplePerson> binder = new Binder(SamplePerson.class);

    public ClientView(SamplePersonService personService) {
        addClassName("клиент-view");

        add(createTitle());
        add(createFormLayout());
        add(new Credit_Amount());
        add(new creditTerm());
        add(createButtonLayout());


    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Данные клиента");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(surname, name, patronymic, phone, email);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        return buttonLayout;
    }

    private static class PhoneNumberField extends CustomField<String> {
        private ComboBox<String> countryCode = new ComboBox<>();
        private TextField number = new TextField();

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
    public class Credit_Amount extends Div {

        private final IntegerField integerField;
        public int credit_Amount(){ return integerField.getValue();}

        public Credit_Amount() {
           integerField = new IntegerField();
            integerField.setLabel("Сумма кредита");
            integerField.setHelperText("от 100 000 ₽     до 5 000 000 ₽");
            integerField.setMin(100000);
            integerField.setMax(5000000);
            integerField.setValue(100000);
            integerField.setStep(100000);
            integerField.setHasControls(true);
            add(integerField);
        }

    }
    @Route("creditTerm")
    public class creditTerm extends Div {

        private final IntegerField integerField;
        public int creditMonth(){ return integerField.getValue();}

       public creditTerm() {
           integerField = new IntegerField();
            integerField.setLabel("Срок кредитования");
            integerField.setMin(13);
            integerField.setMax(84);
            integerField.setValue(13);
            integerField.setStep(1);
            integerField.setHasControls(true);
            add(integerField);
        }

    }
}
