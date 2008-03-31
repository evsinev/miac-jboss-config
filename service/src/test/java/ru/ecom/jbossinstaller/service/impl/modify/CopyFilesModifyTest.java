package ru.ecom.jbossinstaller.service.impl.modify;

import junit.framework.TestCase;

import java.io.InputStream;
import java.io.IOException;
import java.io.File;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultConfigFile;
import ru.ecom.jbossinstaller.service.impl.MainModelImpl;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

/**
 *
 */
public class CopyFilesModifyTest extends TestCase {

    public void testIsStreamsAreEquals() throws IOException {
        assertEquals(true, isEquals("a.txt",  "a_equals_b.txt"));
        assertEquals(false, isEquals("a.txt", "a_not_equals_b1.txt"));
        assertEquals(false, isEquals("a.txt", "a_not_equals_b2.txt"));
        assertEquals(false, isEquals("a.txt", "a_not_equals_b3.txt"));
    }

    private boolean isEquals(String aSource, String aDest) throws IOException {
        InputStream in1 = getClass().getClassLoader().getResourceAsStream("resources/"+aSource) ;
        InputStream in2 = getClass().getClassLoader().getResourceAsStream("resources/"+aDest) ;
        return CopyFilesModify.isInputStreamEquals(in1, in2);
    }


    public void test() throws ErrorApplyException {
        MainModelImpl mainModel = new MainModelImpl(new File("jboss_test"), new DatasourceInfo());

        CopyFilesModify modify = new CopyFilesModify(
                "test"
                , new IFile[] {new JBossDefaultConfigFile("a.txt")}
                , "test description"
                , "resources"
        );
        assertEquals(false, modify.canApply(mainModel).canApply());
    }
}
