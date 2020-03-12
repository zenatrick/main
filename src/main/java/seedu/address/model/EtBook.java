package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.et.Et;
import seedu.address.model.et.UniqueEtList;

/**
 * Replaces the AddressBook we Love so much.
 * Since we only have 1 trip(ET), there is no need for our book to set /edit
 * stuff.
 */
public class EtBook implements ReadOnlyEtBook {

    private final UniqueEtList ets;

    {
        ets = new UniqueEtList();
    }

    public EtBook() {
    }

    /**
     * Creates an EtBook using the Ets in the {@code toBeCopied}
     */
    public EtBook(ReadOnlyEtBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setEts(List<Et> ets) {
        this.ets.setEts(ets);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyEtBook newData) {
        requireNonNull(newData);

        setEts(newData.getEtList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasEt(Et et) {
        requireNonNull(et);
        return ets.contains(et);
    }

    /**
     * Adds a Et to the EtBook.
     * The Et must not already exist in the Et book.
     */
    public void addEt(Et et) {
        ets.add(et);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setEt(Et target, Et editedPerson) {
        requireNonNull(editedPerson);

        ets.setEt(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEt(Et key) {
        ets.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return ets.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Et> getEtList() {
        return ets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EtBook // instanceof handles nulls
                && ets.equals(((EtBook) other).ets));
    }

    @Override
    public int hashCode() {
        return ets.hashCode();
    }


}
