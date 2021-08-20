package com.example.application.views.банк;


import com.example.application.data.entity.Person;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import controllers.ClientDataProvider;
import model.Client;

@Route(value = "bank/:personID?/:action?(edit)", layout = MainView.class)
@PageTitle("Банк")
public class BankView extends Div {

    private final String PERSON_ID = "personID";
    private final String CLIENT_EDIT_ROUTE_TEMPLATE = "bank/%d/edit";

    private Grid<Person> grid = new Grid<>(Person.class, false);

    private TextField surname;
    private TextField name;
    private TextField patronymic;
    private TextField phone;
    private TextField email;
    private TextField passportSeries;
    private TextField passportID;
    private TextField creditAmount;
    private TextField creditTerm;
    private TextField entryInterestRate;


    private Button cancel = new Button("Отменить");
    private Button save = new Button("Сохранить");
    private Button delete = new Button("Удалить");



    private BeanValidationBinder<Client> binder;

    private Client client;




    public BankView() {
        addClassNames("банк-view", "flex", "flex-col", "h-full");

        Crud<Client> crud = new Crud<>(Client.class, createClientEditor());

        ClientDataProvider clientDataProvider = new ClientDataProvider();

        crud.setDataProvider(clientDataProvider);
        crud.addSaveListener(e -> ClientDataProvider.persist(e.getItem()));
        crud.addDeleteListener(e -> ClientdataProvider.delete(e.getItem()));

        crud.getGrid().removeColumnByKey("id");
        crud.addThemeVariants(CrudVariant.NO_BORDER);
        add(crud);



        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(CLIENT_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(BankView.class);
            }
        });


        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.client == null) {
                    this.client = new Client();
                }
                binder.writeBean(this.client);


                clearForm();
                refreshGrid();
                Notification.show("Client details stored.");
                UI.getCurrent().navigate(BankView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the client details.");
            }
        });


    }
    private CrudEditor<Client> createClientEditor() {
        TextField surname = new TextField("Фамилия");
        TextField name = new TextField("Имя");
        TextField patronymic = new TextField("Отчество");
        TextField phone = new TextField("Номер телефона");
        TextField email = new TextField("Почта");
        TextField passportSeries = new TextField("Серия паспорта");
        TextField passportID = new TextField("Номер паспорта");
        Integer creditAmount = new Integer("Сумма кредита");
        Integer creditTerm = new Integer("Срок кредитования");
        Double entryInterestRate = new Double("Процентная ставка");

        FormLayout form = new FormLayout(name, surname, patronymic, phone, email, passportSeries, passportID, creditAmount, creditTerm, entryInterestRate);

        Binder<Client> binder = new Binder<>(Client.class);
        binder.bind(surname, Client::getSURNAME, Client::setSURNAME);
        binder.bind(name, Client::getNAME, Client::setNAME);
        binder.bind(patronymic, Client::getPATRONYMIC, Client::setPATRONYMIC);
        binder.bind(phone, Client::getPHONE, Client::setPHONE);
        binder.bind(email, Client::getEMAIL, Client::setEMAIL);
        binder.bind(passportSeries, Client::getPASSPORTSERIES, Client::setPASSPORTSERIES);
        binder.bind(passportID, Client::getPASSPORTID, Client::setPASSPORTID);
        binder.bind(creditAmount, Client::getCREDIT_AMOUNT, Client::setCREDIT_AMOUNT);
        binder.bind(creditTerm, Client::getCREDIT_TERM, Client::setCREDIT_TERM);
        binder.bind(entryInterestRate, Client::getENTRY_INTEREST_RATE, Client::setENTRY_INTEREST_RATE);

        return new BinderCrudEditor<>(binder, form);
    }


    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        surname = new TextField("Фамилия");
        name = new TextField("Имя");
        patronymic = new TextField("Отчество");
        phone = new TextField("Номер телефона");
        email = new TextField("Почта");
        passportSeries = new TextField("Серия паспорта");
        passportID = new TextField("Номер паспорта");
        creditAmount = new TextField("Сумма кредита");
        creditTerm = new TextField("Срок кредитования");
        entryInterestRate = new TextField("Процентная ставка");

        Component[] fields = new Component[]{surname, name, patronymic, phone, email, passportSeries, passportID, creditAmount, creditTerm, entryInterestRate};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Client value) {
        this.client = value;
        binder.readBean(this.client);

    }
}
