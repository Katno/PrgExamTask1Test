package edu.kit.informatik.test.management.literature.system.command.literatureIndex;

import edu.kit.informatik.management.literature.system.LiteratureManagementSystem;
import edu.kit.informatik.terminal.Terminal;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author David Oberacker
 */
public class DirectPrintJournalTest {
    private static final String[] NO_ARGS = {};
    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
    }

    @After
    public void tearDown() throws Exception {
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void validTest1() throws Exception {
        Terminal.addSingleLineOutputThatIsExactly("direct print journal ieee:Edsger Dijkstra,,,Go To Statement Considered Harmful," +
                "Comm. of the ACM,1968","[1] E. Dijkstra, \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968.");

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }

    @Test
    public void validTest2() throws Exception {
        Terminal.addSingleLineOutputThatIsExactly("direct print journal chicago:Edsger Dijkstra,,,Go To Statement Considered Harmful," +
                "Comm. of the ACM,1968","(Dijkstra, 1968) Dijkstra, Edsger. \"Go To Statement Considered Harmful.\" Comm. of the ACM (1968).");

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }
}