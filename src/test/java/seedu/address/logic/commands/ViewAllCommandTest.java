package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewAllCommand.SHOWING_VIEWALL_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewAllCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_viewall_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_VIEWALL_MESSAGE);
        assertCommandSuccess(new ViewAllCommand(), model, expectedCommandResult, expectedModel);
    }
}
