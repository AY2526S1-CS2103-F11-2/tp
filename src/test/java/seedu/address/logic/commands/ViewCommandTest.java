package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

  private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

  @Test
  public void execute_validIndexUnfilteredList_success() throws CommandException {
    ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));

    String result = viewCommand.execute(model).getFeedbackToUser();
    assertTrue(result.contains("=== AUDITIONEE DETAILS ==="));
    assertTrue(result.contains("Name:"));
    assertTrue(result.contains("Telegram Handle:"));
    assertTrue(result.contains("Instrument:"));
    assertTrue(result.contains("Rating:"));
    assertTrue(result.contains("Comments:"));
  }

  @Test
  public void execute_invalidIndexUnfilteredList_throwsCommandException() {
    ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(model.getFilteredPersonList().size() + 1));

    assertThrows(CommandException.class, () -> viewCommand.execute(model));
  }

  @Test
  public void equals() {
    ViewCommand viewFirstCommand = new ViewCommand(Index.fromOneBased(1));
    ViewCommand viewSecondCommand = new ViewCommand(Index.fromOneBased(2));

    // same object -> returns true
    assertTrue(viewFirstCommand.equals(viewFirstCommand));

    // same values -> returns true
    ViewCommand viewFirstCommandCopy = new ViewCommand(Index.fromOneBased(1));
    assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

    // different types -> returns false
    assertFalse(viewFirstCommand.equals(1));

    // null -> returns false
    assertFalse(viewFirstCommand.equals(null));

    // different person -> returns false
    assertFalse(viewFirstCommand.equals(viewSecondCommand));
  }

  @Test
  public void toStringMethod() {
    Index targetIndex = Index.fromOneBased(1);
    ViewCommand viewCommand = new ViewCommand(targetIndex);
    String expected = ViewCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
    assertEquals(expected, viewCommand.toString());
  }
}
