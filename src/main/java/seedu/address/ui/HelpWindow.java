package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL =
            "https://github.com/AY2526S1-CS2103-F11-2/tp/blob/master/docs/UserGuide.md";
    public static final String HELP_MESSAGE = "AuditionNUS Commands:\n"
            + "• viewall — Lists all auditionees.\n"
            + "    Format: viewall\n\n"

            + "• add — Adds a new auditionee.\n"
            + "    Format: add n/NAME h/TELEHANDLE i/INSTRUMENT c/COMMENT r/RATING [t/TAG]…\n"
            + "    Example: add n/John Doe h/@johndoe i/Violin c/Strong sight-reading r/9 t/Selected\n\n"

            + "• delete — Deletes an auditionee by index.\n"
            + "    Format: delete INDEX\n"
            + "    Example: delete 3 (deletes the 3rd auditionee in the list)\n\n"

            + "• view — Views detailed information of a specific auditionee.\n"
            + "    Format: view INDEX\n"
            + "    Example: view 1 (to view details of auditionee #1)\n\n"

            + "• edit — Edits an existing auditionee’s details.\n"
            + "    Format: edit INDEX [n/NAME] [h/TELEHANDLE] [i/INSTRUMENT] [c/COMMENT] [r/RATING] [t/TAG]…\n"
            + "    Notes: At least one optional field must be provided. "
            + "Tags entered will replace existing tags.\n"
            + "    Example: edit 2 n/Betsy Crower r/8 t/Shortlisted\n\n"

            + "• copy — Copies auditionee details to clipboard in a formatted table.\n"
            + "    Format: copy [b/COUNT] [i/INSTRUMENT]\n"
            + "    Examples:\n"
            + "        copy — copies all auditionees\n"
            + "        copy b/5 — copies top 5 auditionees by rating\n"
            + "        copy i/Piano — copies all piano players\n"
            + "        copy b/3 i/Guitar — copies top 3 guitarists by rating\n\n"

            + "• sort — Sorts all auditionees by rating (highest to lowest).\n"
            + "    Format: sort\n\n"

            + "• find — Finds auditionees whose name, instrument, Telegram handle, rating, or tag matches keywords.\n"
            + "    Format: find KEYWORD [MORE_KEYWORDS]\n"
            + "    Examples:\n"
            + "        find Guitar — finds all auditionees with instrument 'Guitar'\n"
            + "        find @AlexYeoh — finds auditionee with Telegram handle '@AlexYeoh'\n"
            + "        find 9 — finds auditionees rated 9\n\n"

            + "For more details, refer to: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than
     *                               the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or
     *                               layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
