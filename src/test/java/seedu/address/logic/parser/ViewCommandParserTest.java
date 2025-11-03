package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;

/**
 * Test class for {@code ViewCommandParser}.
 */
public class ViewCommandParserTest {

  private ViewCommandParser parser = new ViewCommandParser();

  @Test
  public void parse_validArgs_returnsViewCommand() {
    assertParseSuccess(parser, VALID_INDEX_ONE, new ViewCommand(Index.fromOneBased(1)));
  }

  @Test
  public void parse_invalidArgs_throwsParseException() {
    assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
  }

  @Test
  public void parse_emptyArgs_throwsParseException() {
    assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
  }
}
