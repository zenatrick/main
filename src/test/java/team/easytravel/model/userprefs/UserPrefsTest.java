package team.easytravel.model.userprefs;

import org.junit.jupiter.api.Test;

import team.easytravel.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }
}
