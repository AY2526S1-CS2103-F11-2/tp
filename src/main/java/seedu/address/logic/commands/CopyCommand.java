package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Copy details of auditionees to clipboard.
 * Can optionally filter by instrument and limit to top N by rating.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies auditionee details to clipboard.\n"
            + "Parameters: [b/COUNT] [i/INSTRUMENT]\n"
            + "Examples:\n"
            + "- " + COMMAND_WORD + " (copies all auditionees)\n"
            + "- " + COMMAND_WORD + " b/5 (copies top 5 auditionees by rating)\n"
            + "- " + COMMAND_WORD + " i/Piano (copies all piano players)\n"
            + "- " + COMMAND_WORD + " b/5 i/Piano (copies top 5 piano players by rating)";

    public static final String MESSAGE_SUCCESS = "Copied details of %d auditionee(s) to clipboard";

    private final Integer count;
    private final String instrument;

    /**
     * Creates a CopyCommand to copy all auditionees
     */
    public CopyCommand() {
        this.count = null;
        this.instrument = null;
    }

    /**
     * Creates a CopyCommand with optional filtering parameters
     *
     * @param count Number of top auditionees to copy (null for all)
     * @param instrument Instrument to filter by (null for all instruments)
     */
    public CopyCommand(Integer count, String instrument) {
        this.count = count;
        this.instrument = instrument;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Person> filteredList = getFilteredAndSortedList(model);
        String formattedOutput = formatPersonList(filteredList);
        copyToClipboard(formattedOutput);

        return new CommandResult(String.format(MESSAGE_SUCCESS, filteredList.size()));
    }

    /**
     * Filters and sorts the person list based on command parameters
     */
    private List<Person> getFilteredAndSortedList(Model model) {
        List<Person> persons = model.getFilteredPersonList();

        // Filter by instrument if specified
        if (instrument != null) {
            persons = persons.stream()
                    .filter(person -> person.getInstrument().instrumentName
                            .equalsIgnoreCase(instrument))
                    .collect(Collectors.toList());
        }

        // Sort by rating in descending order
        persons = persons.stream()
                .sorted(Comparator.comparing(person ->
                        Integer.parseInt(((Person) person).getRating().rating))
                        .reversed())
                .collect(Collectors.toList());

        // Limit to top N if count specified
        if (count != null && count < persons.size()) {
            persons = persons.stream()
                    .limit(count)
                    .collect(Collectors.toList());
        }

        return persons;
    }

    /**
     * Formats the person list into a pretty table string
     */
    private String formatPersonList(List<Person> persons) {
        StringBuilder sb = new StringBuilder();
        sb.append("AUDITIONEE LIST\n");
        sb.append("===============\n\n");

        int index = 1;
        for (Person person : persons) {
            sb.append(String.format("%d. %s\n", index++, person.getName()));
            sb.append(String.format("   Telehandle: %s\n", person.getTeleHandle()));
            sb.append(String.format("   Instrument: %s\n", person.getInstrument()));
            sb.append(String.format("   Rating: %s/10\n", person.getRating()));
            sb.append(String.format("   Comment: %s\n", person.getComment()));
            sb.append(String.format("   Tags: %s\n", formatTags(person)));
            sb.append("\n");
        }

        sb.append(String.format("Total: %d auditionee(s)", persons.size()));
        return sb.toString();
    }

    /**
     * Formats tags into a comma-separated string
     */
    private String formatTags(Person person) {
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : person.getTags()) {
            if (tagString.length() > 0) {
                tagString.append(", ");
            }
            tagString.append(tag.toString());
        }
        return tagString.toString();
    }

    /**
     * Copies the formatted string to system clipboard using JavaFX Clipboard
     */
    private void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CopyCommand)) {
            return false;
        }

        CopyCommand otherCommand = (CopyCommand) other;
        boolean countEqual = (count == null && otherCommand.count == null)
                || (count != null && count.equals(otherCommand.count));
        boolean instrumentEqual = (instrument == null && otherCommand.instrument == null)
                || (instrument != null && instrument.equals(otherCommand.instrument));

        return countEqual && instrumentEqual;
    }
}

