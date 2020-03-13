package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.fixedexpense.exceptions.DuplicateFixedExpenseException;
import seedu.address.model.fixedexpense.exceptions.FixedExpenseNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

class UniqueFixedExpenseListTest {

    private final UniqueFixedExpenseList uniqueFixedExpenseList = new UniqueFixedExpenseList();

    //They have a personBuilder, but since we dont have, improvise by using this for now
    private static final FixedExpense fixedTransportationExpense =
            new FixedExpense(new Amount("1000"), new Description("Airplane"),
                    new Category("Transportation"));

    public static final FixedExpense fixedAccommodationExpense =
            new FixedExpense(new Amount("500.00"), new Description("Hotel"),
                    new Category("Accommodation"));

    public static final FixedExpense fixedActivityExpense =
            new FixedExpense(new Amount("100"), new Description("Disneyland"),
                    new Category("Activity"));

    @Test
    public void contains_nullFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.contains(null));
    }

    @Test
    public void contains_ExpenseNotInList_returnsFalse() {
        assertFalse(uniqueFixedExpenseList.contains(fixedAccommodationExpense));
    }

    @Test
    public void contains_ExpenseInList_returnsTrue() {
        uniqueFixedExpenseList.add(fixedAccommodationExpense);
        assertTrue(uniqueFixedExpenseList.contains(fixedAccommodationExpense));
    }

    @Test
    public void contains_expenseWithSameIdentifyFieldsInList_returnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniqueFixedExpenseList.add(fixedAccommodationExpense);
        FixedExpense editedExpense = new FixedExpense(new Amount("500.00"),
                new Description("hotel"), new Category("Accommodation"));
        assertFalse(uniqueFixedExpenseList.contains(editedExpense));
    }

    @Test
    public void add_nullFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.add(null));
    }

    @Test
    public void add_duplicateFixedExpense_throwsDuplicateFixedExpenseException() {
        uniqueFixedExpenseList.add(fixedActivityExpense);
        assertThrows(DuplicateFixedExpenseException.class, () -> uniqueFixedExpenseList.add(fixedActivityExpense));
    }

    @Test
    public void setFixedExpense_nullTargetFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.setFixedExpense(null,
                fixedActivityExpense));
    }

    @Test
    public void setFixedExpense_nullFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.setFixedExpense(fixedAccommodationExpense,
                null));
    }

    @Test
    public void setFixedExpense_targetFixedExpenseNotInList_throwsFixedExpenseNotFoundException() {
        assertThrows(FixedExpenseNotFoundException.class, () -> uniqueFixedExpenseList.setFixedExpense(
                fixedAccommodationExpense, fixedAccommodationExpense));
    }

    @Test
    public void setFixedExpense_editedFixedExpenseIsSameFixedExpense_success() {
        uniqueFixedExpenseList.add(fixedActivityExpense);
        uniqueFixedExpenseList.setFixedExpense(fixedActivityExpense, fixedActivityExpense);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        expectedFixedExpenseList.add(fixedActivityExpense);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseHasSameIdentity_success() {
        uniqueFixedExpenseList.add(fixedActivityExpense);
        FixedExpense editedExpense = new FixedExpense(new Amount("100"), new Description("Disneyland"),
                new Category("Activity"));
        uniqueFixedExpenseList.setFixedExpense(fixedActivityExpense, editedExpense);
        UniqueFixedExpenseList expectedUniqueFixedExpenseList = new UniqueFixedExpenseList();
        expectedUniqueFixedExpenseList.add(editedExpense);
        assertEquals(expectedUniqueFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpense_editedFixedExpenseHasDifferentFixedExpense_success() {
        uniqueFixedExpenseList.add(fixedActivityExpense);
        uniqueFixedExpenseList.setFixedExpense(fixedActivityExpense, fixedAccommodationExpense);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        expectedFixedExpenseList.add(fixedAccommodationExpense);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpense_editedFixedExpenseHasNonUniqueIdentity_throwsDuplicateFixedExpenseException() {
        uniqueFixedExpenseList.add(fixedAccommodationExpense);
        uniqueFixedExpenseList.add(fixedTransportationExpense);
        assertThrows(DuplicateFixedExpenseException.class, () -> uniqueFixedExpenseList.setFixedExpense
                (fixedAccommodationExpense, fixedTransportationExpense));
    }

    @Test
    public void remove_nullFixedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.remove(null));
    }

    @Test
    public void remove_FixedExpenseDoesNotExist_throwsFixedExpenseNotFoundException() {
        assertThrows(FixedExpenseNotFoundException.class, () -> uniqueFixedExpenseList.
                remove(fixedAccommodationExpense));
    }

    @Test
    public void remove_existingFixedExpense_removesFixedExpense() {
        uniqueFixedExpenseList.add(fixedTransportationExpense);
        uniqueFixedExpenseList.remove(fixedTransportationExpense);
        UniqueFixedExpenseList expectedUniquePersonList = new UniqueFixedExpenseList();
        assertEquals(expectedUniquePersonList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpense_nullUniqueFixedExpenseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.
                setFixedExpenses((UniqueFixedExpenseList) null));
    }

    @Test
    public void setFixedExpense_uniqueFixedExpense_replacesOwnListWithProvidedUniqueFixedExpenseList() {
        uniqueFixedExpenseList.add(fixedTransportationExpense);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        uniqueFixedExpenseList.add(fixedAccommodationExpense);
        uniqueFixedExpenseList.setFixedExpenses(expectedFixedExpenseList);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpense_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.
                setFixedExpenses((List<FixedExpense>) null));
    }

    @Test
    public void setFixedExpense_list_replacesOwnListWithProvidedList() {
        uniqueFixedExpenseList.add(fixedTransportationExpense);
        List<FixedExpense> fixedExpenseList = Collections.singletonList(fixedAccommodationExpense);
        uniqueFixedExpenseList.setFixedExpenses(fixedExpenseList);
        UniqueFixedExpenseList expectedUniqueFixedExpenseList = new UniqueFixedExpenseList();
        expectedUniqueFixedExpenseList.add(fixedAccommodationExpense);
        assertEquals(expectedUniqueFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpense_listWithDuplicateFixedExpense_throwsDuplicatePersonException() {
        List<FixedExpense> listWithDuplicateFixedExpense = Arrays.
                asList(fixedAccommodationExpense, fixedAccommodationExpense);
        assertThrows(DuplicateFixedExpenseException.class,
                () -> uniqueFixedExpenseList.setFixedExpenses(listWithDuplicateFixedExpense));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueFixedExpenseList.asUnmodifiableObservableList().remove(0));
    }


    @Test
    void forEach() {
    }

    @Test
    void spliterator() {
    }

    @Test
    void contains() {
    }

    @Test
    void add() {
    }

    @Test
    void setFixedExpense() {
    }

    @Test
    void remove() {
    }

    @Test
    void setFixedExpenses() {
    }

    @Test
    void testSetFixedExpenses() {
    }

    @Test
    void asUnmodifiableObservableList() {
    }

    @Test
    void iterator() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}