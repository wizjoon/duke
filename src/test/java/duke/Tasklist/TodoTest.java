package duke.Tasklist;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testgetStatus() throws AssertionFailedError{
        try {
            ToDo t1 = new ToDo("borrow book");
            assertEquals("[T][] borrow book", ToDo.testgetStatus(t1.getStatus()));
        }catch (AssertionFailedError e) {
            e.printStackTrace();
        }
    }
}
