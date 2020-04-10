package team.easytravel.model.userprefs;

import java.nio.file.Path;

import team.easytravel.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTransportBookingStorageFilePath();

    Path getActivityStorageFilePath();

    Path getFixedExpenseStorageFilePath();

    Path getAccommodationBookingStorageFilePath();

    Path getPackingListStorageFilePath();

    Path getTripStorageFilePath();
}
