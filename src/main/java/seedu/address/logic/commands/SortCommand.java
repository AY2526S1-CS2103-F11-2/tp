package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all persons in the address book according to their rating.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons in the address book according to "
            + "their rating.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Persons sorted by rating (highest to lowest)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Get all persons and create a new sorted list
        List<Person> allPersons = new ArrayList<>(model.getAddressBook().getPersonList());

        // Sort by rating (highest to lowest)
        allPersons.sort((person1, person2) -> {
            int rating1 = Integer.parseInt(person1.getRating().toString());
            int rating2 = Integer.parseInt(person2.getRating().toString());
            return Integer.compare(rating2, rating1); // Descending order
        });

        // Create a new AddressBook with the sorted persons
        AddressBook sortedAddressBook = new AddressBook();
        sortedAddressBook.setPersons(allPersons);

        // Replace the entire address book with the sorted version
        model.setAddressBook(sortedAddressBook);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
