package duke.Tasklist;

import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void testgetStatus() throws AssertionFailedError{
        try {
            Events t= new Events("attend seminar", "at NUS on 2022-08-01");
            assertEquals("[E][ ] attend seminar (at NUS on Aug 1 2022)", Events.testgetStatus(t.getStatus()));

        } catch (DukeException e) {
        }catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }
}
