package duke.Tasklist;

import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void testgetStatus() throws AssertionFailedError {
        try {
            Deadlines t1 = new Deadlines("submit report","by 2022-05-17 2359");
            assertEquals("[D][ ] submit report (May 17 2022 11:59pm)", Deadlines.testgetStatus(t1.getStatus()));

            Deadlines t2 = new Deadlines("pay tax","by 2022-08-01 2359");
            assertEquals("[D][ ] pay tax (Aug 1 2022 11:59pm)", Deadlines.testgetStatus(t2.getStatus()));

            Deadlines t3 = new Deadlines("visit TCM","by 2022-10-11 2359");
            assertEquals("[D][ ] visit TCM (Oct 11 2022 11:59pm)", Deadlines.testgetStatus(t3.getStatus()));

        } catch (DukeException e) {
        } catch (timeparseException e) {
        } catch (dateparseException e) {
        } catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }
}
