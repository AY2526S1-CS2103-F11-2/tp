package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;

import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BEST, PREFIX_INSTRUMENT);

        // Check for invalid preamble (text before any prefix)
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }

        // Verify no duplicate prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_BEST, PREFIX_INSTRUMENT);

        // Parse optional count parameter
        Integer count = null;
        if (argMultimap.getValue(PREFIX_BEST).isPresent()) {
            try {
                count = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BEST).get()).getZeroBased() + 1;
                if (count <= 0) {
                    throw new ParseException("Count must be a positive integer.");
                }
            } catch (ParseException pe) {
                throw new ParseException(
                        "Invalid count format. Please provide a positive integer after b/\n"
                        + CopyCommand.MESSAGE_USAGE, pe);
            }
        }

        // Parse optional instrument filter
        String instrument = null;
        if (argMultimap.getValue(PREFIX_INSTRUMENT).isPresent()) {
            instrument = argMultimap.getValue(PREFIX_INSTRUMENT).get().trim();
            if (instrument.isEmpty()) {
                throw new ParseException("Instrument cannot be empty.\n" + CopyCommand.MESSAGE_USAGE);
            }
        }

        return new CopyCommand(count, instrument);
    }
}
