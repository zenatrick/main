package seedu.address.model.fixedexpense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.fixedexpense.exceptions.DuplicateFixedExpenseException;
import seedu.address.model.fixedexpense.exceptions.FixedExpenseNotFoundException;

class UniqueFixedExpenseListTest {

    //They have a personBuilder, but since we dont have, improvise by using this for now
    public static final FixedExpense FIXED_ACCOMMODATION_EXPENSE =
            new FixedExpense(new Amount("500.00"), new Description("Hotel"),
                    new Category("Accommodation"));

    public static final FixedExpense FIXED_ACTIVITY_EXPENSE =
            new FixedExpense(new Amount("100"), new Description("Disneyland"),
                    new Category("Activity"));

    private static final FixedExpense FIXED_TRANSPORTATION_EXPENSE =
            new FixedExpense(new Amount("1000"), new Description("Airplane"),
                    new Category("Transportation"));

    private final UniqueFixedExpenseList uniqueFixedExpenseList = new UniqueFixedExpenseList();


    @Test
    public void containsNullFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.contains(null));
    }

    @Test
    public void containsExpenseNotInListReturnsFalse() {
        assertFalse(uniqueFixedExpenseList.contains(FIXED_ACCOMMODATION_EXPENSE));
    }

    @Test
    public void containsExpenseInListReturnsTrue() {
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        assertTrue(uniqueFixedExpenseList.contains(FIXED_ACCOMMODATION_EXPENSE));
    }

    @Test
    public void containsExpenseWithSameIdentifyFieldsInListReturnsTrue() {
        //Slightly different from UniquePersonListTest, due to different
        //contains convention used for AB3 and for ours.
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        FixedExpense editedExpense = new FixedExpense(new Amount("500.00"),
                new Description("hotel"), new Category("Accommodation"));
        assertFalse(uniqueFixedExpenseList.contains(editedExpense));
    }

    @Test
    public void addNullFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.add(null));
    }

    @Test
    public void addDuplicateFixedExpenseThrowsDuplicateFixedExpenseException() {
        uniqueFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE);
        assertThrows(DuplicateFixedExpenseException.class, () -> uniqueFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE));
    }

    @Test
    public void setFixedExpenseNullTargetFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.setFixedExpense(null,
                FIXED_ACTIVITY_EXPENSE));
    }

    @Test
    public void setFixedExpenseNullFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList
                .setFixedExpense(FIXED_ACCOMMODATION_EXPENSE,
                null));
    }

    @Test
    public void setFixedExpenseTargetFixedExpenseNotInListThrowsFixedExpenseNotFoundException() {
        assertThrows(FixedExpenseNotFoundException.class, () -> uniqueFixedExpenseList.setFixedExpense(
                FIXED_ACCOMMODATION_EXPENSE, FIXED_ACCOMMODATION_EXPENSE));
    }

    @Test
    public void setFixedExpenseEditedFixedExpenseIsSameFixedExpenseSuccess() {
        uniqueFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE);
        uniqueFixedExpenseList.setFixedExpense(FIXED_ACTIVITY_EXPENSE, FIXED_ACTIVITY_EXPENSE);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        expectedFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseHasSameIdentitySuccess() {
        uniqueFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE);
        FixedExpense editedExpense = new FixedExpense(new Amount("100"), new Description("Disneyland"),
                new Category("Activity"));
        uniqueFixedExpenseList.setFixedExpense(FIXED_ACTIVITY_EXPENSE, editedExpense);
        UniqueFixedExpenseList expectedUniqueFixedExpenseList = new UniqueFixedExpenseList();
        expectedUniqueFixedExpenseList.add(editedExpense);
        assertEquals(expectedUniqueFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseEditedFixedExpenseHasDifferentFixedExpenseSuccess() {
        uniqueFixedExpenseList.add(FIXED_ACTIVITY_EXPENSE);
        uniqueFixedExpenseList.setFixedExpense(FIXED_ACTIVITY_EXPENSE, FIXED_ACCOMMODATION_EXPENSE);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        expectedFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseEditedFixedExpenseHasNonUniqueIdentityThrowsDuplicateFixedExpenseException() {
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        uniqueFixedExpenseList.add(FIXED_TRANSPORTATION_EXPENSE);
        assertThrows(DuplicateFixedExpenseException.class, () -> uniqueFixedExpenseList.setFixedExpense
                (FIXED_ACCOMMODATION_EXPENSE, FIXED_TRANSPORTATION_EXPENSE));
    }

    @Test
    public void removeNullFixedExpenseThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList.remove(null));
    }

    @Test
    public void removeFixedExpenseDoesNotExistThrowsFixedExpenseNotFoundException() {
        assertThrows(FixedExpenseNotFoundException.class, () -> uniqueFixedExpenseList
                .remove(FIXED_ACCOMMODATION_EXPENSE));
    }

    @Test
    public void removeExistingFixedExpenseRemovesFixedExpense() {
        uniqueFixedExpenseList.add(FIXED_TRANSPORTATION_EXPENSE);
        uniqueFixedExpenseList.remove(FIXED_TRANSPORTATION_EXPENSE);
        UniqueFixedExpenseList expectedUniquePersonList = new UniqueFixedExpenseList();
        assertEquals(expectedUniquePersonList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseNullUniqueFixedExpenseListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList
                .setFixedExpenses((UniqueFixedExpenseList) null));
    }

    @Test
    public void setFixedExpenseUniqueFixedExpenseReplacesOwnListWithProvidedUniqueFixedExpenseList() {
        uniqueFixedExpenseList.add(FIXED_TRANSPORTATION_EXPENSE);
        UniqueFixedExpenseList expectedFixedExpenseList = new UniqueFixedExpenseList();
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        uniqueFixedExpenseList.setFixedExpenses(expectedFixedExpenseList);
        assertEquals(expectedFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseNullListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFixedExpenseList
                .setFixedExpenses((List<FixedExpense>) null));
    }

    @Test
    public void setFixedExpenseListReplacesOwnListWithProvidedList() {
        uniqueFixedExpenseList.add(FIXED_TRANSPORTATION_EXPENSE);
        List<FixedExpense> fixedExpenseLists = Collections.singletonList(FIXED_ACCOMMODATION_EXPENSE);
        uniqueFixedExpenseList.setFixedExpenses(fixedExpenseLists);
        UniqueFixedExpenseList expectedUniqueFixedExpenseList = new UniqueFixedExpenseList();
        expectedUniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        assertEquals(expectedUniqueFixedExpenseList, uniqueFixedExpenseList);
    }

    @Test
    public void setFixedExpenseListWithDuplicateFixedExpenseThrowsDuplicateFixedExpenseException() {
        List<FixedExpense> listWithDuplicateFixedExpenses = Arrays
                .asList(FIXED_ACCOMMODATION_EXPENSE, FIXED_ACCOMMODATION_EXPENSE);
        assertThrows(DuplicateFixedExpenseException.class, ()
            -> uniqueFixedExpenseList.setFixedExpenses(listWithDuplicateFixedExpenses));
    }

    @Test
    public void asUnmodifiableObservableListModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFixedExpenseList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testEquals() {
        UniqueFixedExpenseList expectedUniqueFixedExpenseLists = new UniqueFixedExpenseList();
        expectedUniqueFixedExpenseLists.add(FIXED_ACCOMMODATION_EXPENSE);
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        assertEquals(uniqueFixedExpenseList, expectedUniqueFixedExpenseLists);
    }

    @Test
    public void testHashCode() {
        //Same Hash Code
        uniqueFixedExpenseList.add(FIXED_ACCOMMODATION_EXPENSE);
        UniqueFixedExpenseList ue = new UniqueFixedExpenseList();
        ue.add(FIXED_ACCOMMODATION_EXPENSE);
        assertEquals(uniqueFixedExpenseList.hashCode() , ue.hashCode());

        //Different Hash code
        UniqueFixedExpenseList diffUe = new UniqueFixedExpenseList();
        diffUe.add(FIXED_ACTIVITY_EXPENSE);
        assertNotEquals(diffUe.hashCode(), uniqueFixedExpenseList.hashCode());
    }
}
