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
     * Formats the person list
     */
    private String formatPersonList(List<Person> persons) {
        StringBuilder sb = new StringBuilder();

        // Dynamic header based on filters
        String title = generateTitle();
        sb.append(title).append("\n");
        sb.append("=".repeat(title.length())).append("\n\n");

        // Filter information if applicable
        if (count != null || instrument != null) {
            sb.append(generateFilterInfo());
        }

        // Summary section
        sb.append(generateSummary(persons));
        sb.append("\n");

        // Individual entries
        int index = 1;
        for (Person person : persons) {
            sb.append(formatPersonEntry(person, index++));
        }

        // Footer
        sb.append(String.format("Total: %d auditionee(s)\n", persons.size()));

        return sb.toString();
    }

    /**
     * Generates dynamic title based on filters applied
     */
    private String generateTitle() {
        StringBuilder title = new StringBuilder();

        if (count != null && instrument != null) {
            // Both filters: "TOP 5 PIANO PLAYERS"
            title.append(String.format("TOP %d %s PLAYERS", count, instrument.toUpperCase()));
        } else if (count != null) {
            // Count only: "TOP 5 AUDITIONEES"
            title.append(String.format("TOP %d AUDITIONEES", count));
        } else if (instrument != null) {
            // Instrument only: "PIANO PLAYERS"
            title.append(String.format("%s PLAYERS", instrument.toUpperCase()));
        } else {
            // No filters: "ALL AUDITIONEES"
            title.append("ALL AUDITIONEES");
        }

        return title.toString();
    }

    /**
     * Generates filter information section
     */
    private String generateFilterInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Filter Applied:\n");

        if (count != null) {
            sb.append(String.format("  - Showing top %d by rating\n", count));
        }
        if (instrument != null) {
            sb.append(String.format("  - Instrument: %s\n", instrument));
        }

        sb.append("\n");
        return sb.toString();
    }

    /**
     * Generates a brief summary section
     */
    private String generateSummary(List<Person> persons) {
        StringBuilder sb = new StringBuilder();

        double avgRating = calculateAverageRating(persons);
        int highestRating = getHighestRating(persons);
        int lowestRating = getLowestRating(persons);

        sb.append("Summary:\n");
        sb.append(String.format("  Average Rating: %.1f/10\n", avgRating));
        sb.append(String.format("  Highest: %d/10 | Lowest: %d/10\n", highestRating, lowestRating));

        return sb.toString();
    }

    /**
     * Calculates average rating
     */
    private double calculateAverageRating(List<Person> persons) {
        return persons.stream()
                .mapToInt(p -> Integer.parseInt(p.getRating().rating))
                .average()
                .orElse(0.0);
    }

    /**
     * Gets highest rating
     */
    private int getHighestRating(List<Person> persons) {
        return persons.stream()
                .mapToInt(p -> Integer.parseInt(p.getRating().rating))
                .max()
                .orElse(0);
    }

    /**
     * Gets lowest rating
     */
    private int getLowestRating(List<Person> persons) {
        return persons.stream()
                .mapToInt(p -> Integer.parseInt(p.getRating().rating))
                .min()
                .orElse(0);
    }

    /**
     * Formats a single person entry
     */
    private String formatPersonEntry(Person person, int index) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d. %s\n", index, person.getName()));
        sb.append(String.format("   Telehandle: %s\n", person.getTeleHandle()));
        sb.append(String.format("   Instrument: %s\n", person.getInstrument()));
        sb.append(String.format("   Rating: %s/10 %s\n",
                person.getRating(), generateStars(person.getRating().rating)));
        sb.append(String.format("   Comment: %s\n", person.getComment()));

        String tags = formatTags(person);
        if (!tags.isEmpty()) {
            sb.append(String.format("   Tags: %s\n", tags));
        }

        sb.append("\n");

        return sb.toString();
    }

    /**
     * Generates star representation for rating
     */
    private String generateStars(String ratingStr) {
        int rating = Integer.parseInt(ratingStr);
        int stars = (rating + 1) / 2;
        int emptyStars = 5 - stars;

        return "★".repeat(stars) + "☆".repeat(emptyStars);
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
