package com.example.application.views.банк;


import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Optional;

@Route(value = "bank/:personID?/:action?(edit)", layout = MainView.class)
@PageTitle("Банк")
public class BankView extends Div implements BeforeEnterObserver {

    private final String PERSON_ID = "personID";
    private final String PERSON_EDIT_ROUTE_TEMPLATE = "bank/%d/edit";

    private Grid<Person> grid = new Grid<>(Person.class, false);

    private TextField surname;
    private TextField name;
    private TextField patronymic;
    private TextField phone;
    private TextField email;
    private DatePicker date;
    private TextField passportID;

    private Button cancel = new Button("Отменить");
    private Button save = new Button("Сохранить");


    private BeanValidationBinder<Person> binder;

    private Person person;

    private PersonService personService;

    public BankView(@Autowired PersonService personService) {
        addClassNames("банк-view", "flex", "flex-col", "h-full");
        this.personService = personService;
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("surname").setAutoWidth(true);
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("patronymic").setAutoWidth(true);
        grid.addColumn("phone").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("date").setAutoWidth(true);
        grid.addColumn("passportID").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(personService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(BankView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Person.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(passportID).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("passportID");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.person == null) {
                    this.person = new Person();
                }
                binder.writeBean(this.person);

                personService.update(this.person);
                clearForm();
                refreshGrid();
                Notification.show("Person details stored.");
                UI.getCurrent().navigate(BankView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the person details.");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> personId = event.getRouteParameters().getInteger(PERSON_ID);
        if (personId.isPresent()) {
            Optional<Person> personFromBackend = personService.get(personId.get());
            if (personFromBackend.isPresent()) {
                populateForm(personFromBackend.get());
            } else {
                Notification.show(String.format("The requested person was not found, ID = %d", personId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(BankView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        surname = new TextField("Surname");
        name = new TextField("Name");
        patronymic = new TextField("Patronymic");
        phone = new TextField("Phone");
        email = new TextField("Email");
        date = new DatePicker("Date");
        passportID = new TextField("Passport ID");
        Component[] fields = new Component[]{surname, name, patronymic, phone, email, date, passportID};

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
        buttonLayout.add(save, cancel);
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

    private void populateForm(Person value) {
        this.person = value;
        binder.readBean(this.person);

    }
}
