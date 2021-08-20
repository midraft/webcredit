package controllers;

import com.example.application.data.entity.Person;
import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.SortDirection;
import model.Client;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;

public static class ClientDataProvider extends AbstractBackEndDataProvider<Client, CrudFilter>{

    // A real app should hook up something like JPA
    final List<Client> DATABASE = createHibernateSessionFactoryUtil();

    private Consumer<Long> sizeChangeListener;

    @Override
    protected Stream<Client> fetchFromBackEnd(Query<Client, CrudFilter> query) {
        int offset = query.getOffset();
        int limit = query.getLimit();

        Stream<Client> stream = DATABASE.stream();

        if (query.getFilter().isPresent()) {
            stream = stream
                    .filter(predicate(query.getFilter().get()))
                    .sorted(comparator(query.getFilter().get()));
        }

        return stream.skip(offset).limit(limit);
    }

    @Override
    protected int sizeInBackEnd(Query<Client, CrudFilter> query) {
        long count = fetchFromBackEnd(query).count();

        if (sizeChangeListener != null) {
            sizeChangeListener.accept(count);
        }

        return (int) count;
    }

    void setSizeChangeListener(Consumer<Long> listener) {
        sizeChangeListener = listener;
    }

    private static Predicate<Client> predicate(CrudFilter filter) {
        // For RDBMS just generate a WHERE clause
        return filter.getConstraints().entrySet().stream()
                .map(constraint -> (Predicate<Client>) client -> {
                    try {
                        Object value = valueOf(constraint.getKey(), client);
                        return value != null && value.toString().toLowerCase()
                                .contains(constraint.getValue().toLowerCase());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .reduce(Predicate::and)
                .orElse(e -> true);
    }

    private static Comparator<Client> comparator(CrudFilter filter) {
        // For RDBMS just generate an ORDER BY clause
        return filter.getSortOrders().entrySet().stream()
                .map(sortClause -> {
                    try {
                        Comparator<Client> comparator
                                = Comparator.comparing(client ->
                                (Comparable) valueOf(sortClause.getKey(), client));

                        if (sortClause.getValue() == SortDirection.DESCENDING) {
                            comparator = comparator.reversed();
                        }

                        return comparator;
                    } catch (Exception ex) {
                        return (Comparator<Client>) (o1, o2) -> 0;
                    }
                })
                .reduce(Comparator::thenComparing)
                .orElse((o1, o2) -> 0);
    }

    private static Object valueOf(String fieldName, Client person) {
        try {
            Field field = Client.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(client);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void persist(Client item) {
        if (item.getCLIENTID() == null) {
            item.setCLIENTID(DATABASE
                    .stream()
                    .map(Client::getCLIENTID)
                    .max(naturalOrder())
                    .orElse(0) + 1);
        }

        final Optional<Client> existingItem = find(item.getCLIENTID());
        if (existingItem.isPresent()) {
            int position = DATABASE.indexOf(existingItem.get());
            DATABASE.remove(existingItem.get());
            DATABASE.add(position, item);
        } else {
            DATABASE.add(item);
        }

        Optional<Client> find(UUID CLIENTID) {
            return DATABASE
                    .stream()
                    .filter(entity -> entity.getCLIENTID().equals(CLIENTID))
                    .findFirst();
        }

        void delete(Client item) {
            DATABASE.removeIf(entity -> entity.getCLIENTID().equals(item.getCLIENTID()));
        }
}
