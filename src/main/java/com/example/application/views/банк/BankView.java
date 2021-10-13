package com.example.application.views.банк;


import com.example.application.data.service.ClientService;
import com.example.application.model.Clients;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.*;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "bank/:personID?/:action?(edit)", layout = MainView.class)
@PageTitle("Банк")
public class BankView extends Div {

    public BankView() {
        addClassNames("банк-view", "flex", "flex-col", "h-full");

        CrudGrid<Clients> grid = new CrudGrid<>(Clients.class, false);

        Crud<Clients> crud = new Crud<>(Clients.class, grid, createClientEditor());
        ClientService clientService = new ClientService();
        Button save = new Button("Save", VaadinIcon.CHECK.create());
        Button cancel = new Button("Cancel");
        Button delete = new Button("Delete", VaadinIcon.TRASH.create());


        crud.addSaveListener(e -> clientService.save(e.getItem()));
        crud.addDeleteListener(e -> clientService.delete(e.getItem()));



        crud.addThemeVariants(CrudVariant.NO_BORDER);
        add(crud);
        

    }
    private CrudEditor<Clients> createClientEditor() {
        TextField surname = new TextField("Фамилия");
        TextField name = new TextField("Имя");
        TextField patronymic = new TextField("Отчество");
        TextField phone = new TextField("Номер телефона");
        TextField email = new TextField("Почта");
        IntegerField passportSeries = new IntegerField("Серия паспорта");
        IntegerField passportID = new IntegerField("Номер паспорта");
        NumberField creditAmount = new NumberField("Сумма кредита");
        NumberField creditTerm = new NumberField("Срок кредитования");
        NumberField entryInterestRate = new NumberField("Процентная ставка");

        FormLayout form = new FormLayout(surname, name, patronymic, phone, email, passportSeries, passportID, creditAmount, creditTerm, entryInterestRate);

        Binder<Clients> binder = new Binder<>(Clients.class);
        binder.bind(surname, Clients::getSURNAME, Clients::setSURNAME);
        binder.bind(name, Clients::getNAME, Clients::setNAME);
        binder.bind(patronymic, Clients::getPATRONYMIC, Clients::setPATRONYMIC);
        binder.bind(phone, Clients::getPHONE, Clients::setPHONE);
        binder.bind(email, Clients::getEmail, Clients::setEmail);
        binder.bind(passportSeries, Clients::getPASSPORTSERIES, Clients::setPASSPORTSERIES);
        binder.bind(passportID, Clients::getPASSPORTID, Clients::setPASSPORTID);
        binder.bind(creditAmount, Clients::getCreditAmount, Clients::setCreditAmount);
        binder.bind(creditTerm, Clients::getCreditTerm, Clients::setCreditTerm);
        binder.bind(entryInterestRate, Clients::getEntryInterestRate, Clients::setEntryInterestRate);

        return new BinderCrudEditor<>(binder, form);
    }
    
}
