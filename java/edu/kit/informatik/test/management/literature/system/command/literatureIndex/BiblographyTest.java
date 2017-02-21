package edu.kit.informatik.test.management.literature.system.command.literatureIndex;

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
public class BiblographyTest {
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

        Terminal.addSingleLineOutputThatIsExactly("add conference TSA,1995,Karlsruhe", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference IAA,1996,Genf", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add article to series TSA:id1,1995,Test Title", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to series IAA:id2,1996,Test Title", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add article to journal CT:id3,1994,Test Title", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal NYT:id4,1995,Test Title", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add author David,Oberacker", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Klug,Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Christian,Gruenhage", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Test,Author", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("written by id1,Christian Gruenhage;David Oberacker", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id2,Klug Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id3,David Oberacker;Klug Alexander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("written by id4,Christian Gruenhage;David Oberacker", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add keywords to series TSA:swt;reference;trivial", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add keywords to series IAA:swt;reference", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add keywords to journal CT:swt;testr", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add keywords to journal NYT:swt;testr", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("cites id2,id4","Ok");
        Terminal.addSingleLineOutputThatIsExactly("cites id2,id3","Ok");
        Terminal.addSingleLineOutputThatIsExactly("cites id2,id1","Ok");
    }


    @Test
    public void validTest1() throws Exception {
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography ieee:id1;id2;id3;id4"
                , "[1] K. Alexander, \"Test Title,\" in Proceedings of IAA, Genf, 1996."
                , "[2] D. Oberacker and K. Alexander, \"Test Title,\" CT, 1994."
                , "[3] D. Oberacker and C. Gruenhage, \"Test Title,\" in Proceedings of TSA, Karlsruhe, 1995."
                , "[4] D. Oberacker and C. Gruenhage, \"Test Title,\" NYT, 1995.");

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }

    @Test
    public void validTest2() throws Exception {
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography chicago:id1;id2;id3;id4"
                , "(Alexander, 1996) Alexander, Klug. \"Test Title.\" Paper presented at IAA, 1996, Genf."
                , "(Oberacker, 1994) Oberacker, David, and Alexander, Klug. \"Test Title.\" CT (1994)."
                , "(Oberacker, 1995) Oberacker, David, and Gruenhage, Christian. \"Test Title.\" Paper presented at TSA, 1995, Karlsruhe."
                , "(Oberacker, 1995) Oberacker, David, and Gruenhage, Christian. \"Test Title.\" NYT (1995).");

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }
    @Test
    public void validTest3() throws Exception {
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography chicago:id1;id2;id1;id2"
                , "(Alexander, 1996) Alexander, Klug. \"Test Title.\" Paper presented at IAA, 1996, Genf."
                , "(Oberacker, 1995) Oberacker, David, and Gruenhage, Christian. \"Test Title.\" Paper presented at TSA, 1995, Karlsruhe.");

        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        LiteratureManagementSystem.main(NO_ARGS);
    }
}