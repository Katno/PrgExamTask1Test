package edu.kit.informatik.test.management.literature.system.command.complexCommand;

import edu.kit.informatik.management.literature.system.LiteratureManagementSystem;
import edu.kit.informatik.terminal.Terminal;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author David Oberacker
 */
public class CoauthorsOfTest {
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

    @Before
    public void prepare() throws Exception {
        Terminal.addSingleLineOutputThatIsExactly("add conference series TSA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series IAA", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add journal CT,Pub", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal NYT,Pub", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add conference TSA,1997,Karlsruhe", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference IAA,1996,Genf", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add article to series TSA:id1,1997,Test Title", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to series IAA:id2,1996,Test Title", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add article to journal CT:id3,1997,Test Title", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal NYT:id4,1995,Test Title", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add author David,Oberacker", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Klug,Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Christian,Gruenhage", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Test,Author", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("written by id1,Christian Gruenhage;David Oberacker", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id2,Klug Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id3,David Oberacker;Klug Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id4,Christian Gruenhage;David Oberacker", "Ok");
    }

    @Test
    public void validTest1() throws Exception {
        Terminal.addMultipleLineOutputThatMatches("coauthors of David Oberacker",
                Matchers.containsInAnyOrder("Christian Gruenhage", "Klug Alexander"));

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }

    @Test
    public void validTest2() throws Exception {
        Terminal.addMultipleLineOutputThatMatches("coauthors of Klug Alexander",
                Matchers.containsInAnyOrder("David Oberacker"));

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }

    @Test
    public void validTest3() throws Exception {
        Terminal.addMultipleLineOutputThatMatches("coauthors of Christian Gruenhage",
                Matchers.containsInAnyOrder("David Oberacker"));

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }

    @Test
    public void validTest4() throws Exception {
        Terminal.addMultipleLineOutputThatMatches("coauthors of Test Author",
                Matchers.containsInAnyOrder());

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }
}