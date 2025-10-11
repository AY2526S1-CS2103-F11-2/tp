package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEHANDLE = "JOHNDOE";
    private static final String INVALID_INSTRUMENT = "862";
    private static final String INVALID_COMMENT = "   ";
    private static final String INVALID_RATING = "11";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_TELEHANDLE = "@rachelwalker";
    private static final String VALID_INSTRUMENT = "Guitar";
    private static final String VALID_COMMENT = "Very skilled musician";
    private static final String VALID_RATING = "8";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    // ------------------------------------------------------------
    // INDEX TESTS
    // ------------------------------------------------------------

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // ------------------------------------------------------------
    // NAME TESTS
    // ------------------------------------------------------------

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // ------------------------------------------------------------
    // TELEHANDLE TESTS
    // ------------------------------------------------------------

    @Test
    public void parseTeleHandle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTeleHandle((String) null));
    }

    @Test
    public void parseTeleHandle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTeleHandle(INVALID_TELEHANDLE));
    }

    @Test
    public void parseTeleHandle_validValueWithoutWhitespace_returnsTeleHandle() throws Exception {
        TeleHandle expectedTeleHandle = new TeleHandle(VALID_TELEHANDLE);
        assertEquals(expectedTeleHandle, ParserUtil.parseTeleHandle(VALID_TELEHANDLE));
    }

    @Test
    public void parseTeleHandle_validValueWithWhitespace_returnsTrimmedTeleHandle() throws Exception {
        String handleWithWhitespace = WHITESPACE + VALID_TELEHANDLE + WHITESPACE;
        TeleHandle expectedTeleHandle = new TeleHandle(VALID_TELEHANDLE);
        assertEquals(expectedTeleHandle, ParserUtil.parseTeleHandle(handleWithWhitespace));
    }

    // ------------------------------------------------------------
    // INSTRUMENT TESTS
    // ------------------------------------------------------------

    @Test
    public void parseInstrument_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInstrument((String) null));
    }

    @Test
    public void parseInstrument_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInstrument(INVALID_INSTRUMENT));
    }

    @Test
    public void parseInstrument_validValueWithoutWhitespace_returnsInstrument() throws Exception {
        Instrument expectedInstrument = new Instrument(VALID_INSTRUMENT);
        assertEquals(expectedInstrument, ParserUtil.parseInstrument(VALID_INSTRUMENT));
    }

    @Test
    public void parseInstrument_validValueWithWhitespace_returnsTrimmedInstrument() throws Exception {
        String instrumentWithWhitespace = WHITESPACE + VALID_INSTRUMENT + WHITESPACE;
        Instrument expectedInstrument = new Instrument(VALID_INSTRUMENT);
        assertEquals(expectedInstrument, ParserUtil.parseInstrument(instrumentWithWhitespace));
    }

    // ------------------------------------------------------------
    // COMMENT TESTS
    // ------------------------------------------------------------

    @Test
    public void parseComment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseComment((String) null));
    }

    @Test
    public void parseComment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseComment(INVALID_COMMENT));
    }

    @Test
    public void parseComment_validValueWithoutWhitespace_returnsComment() throws Exception {
        Comment expectedComment = new Comment(VALID_COMMENT);
        assertEquals(expectedComment, ParserUtil.parseComment(VALID_COMMENT));
    }

    @Test
    public void parseComment_validValueWithWhitespace_returnsTrimmedComment() throws Exception {
        String commentWithWhitespace = WHITESPACE + VALID_COMMENT + WHITESPACE;
        Comment expectedComment = new Comment(VALID_COMMENT);
        assertEquals(expectedComment, ParserUtil.parseComment(commentWithWhitespace));
    }

    // ------------------------------------------------------------
    // RATING TESTS
    // ------------------------------------------------------------

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating((String) null));
    }

    @Test
    public void parseRating_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating(INVALID_RATING));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }

    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }

    // ------------------------------------------------------------
    // TAG TESTS
    // ------------------------------------------------------------

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}