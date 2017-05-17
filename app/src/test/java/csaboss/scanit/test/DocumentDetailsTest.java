package csaboss.scanit.test;

import android.util.Log;

import org.apache.maven.artifact.ant.shaded.cli.Arg;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.List;

import csaboss.scanit.BuildConfig;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentdetails.DocumentDetailsPresenter;
import csaboss.scanit.ui.documentdetails.DocumentDetailsScreen;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
import csaboss.scanit.ui.documentlist.DocumentListScreen;
import csaboss.scanit.utils.RobolectricDaggerTestRunner;

import static csaboss.scanit.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DocumentDetailsTest {

    private DocumentDetailsPresenter documentDetailsPresenter;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        documentDetailsPresenter = new DocumentDetailsPresenter();
    }

    @Test
    public void testGetDocument() {
        DocumentDetailsScreen screen = mock(DocumentDetailsScreen.class);
        documentDetailsPresenter.attachScreen(screen);
        ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);


        documentDetailsPresenter.getDocument(1);

        verify(screen, times(1)).showDocument(captor.capture());
        assertEquals("Ez itt a tartalma a cuccnak",captor.getValue().getText());
        assertEquals("Cim1",captor.getValue().getTitle());
    }

    @Test
    public void testDeleteDocument() {
        DocumentDetailsScreen screen = mock(DocumentDetailsScreen.class);
        documentDetailsPresenter.attachScreen(screen);
        Document document = new Document(1L,"Asd","FGH");

        documentDetailsPresenter.deleteDocument(document);

        verify(screen,times(1)).deleteSuccess("Successful delete operation");
    }

    @After
    public void tearDown() {
        documentDetailsPresenter.detachScreen();
    }
}