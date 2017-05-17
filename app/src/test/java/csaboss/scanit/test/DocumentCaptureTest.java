package csaboss.scanit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import csaboss.scanit.BuildConfig;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentcapture.DocumentCapturePresenter;
import csaboss.scanit.ui.documentcapture.DocumentCapureScreen;
import csaboss.scanit.ui.documentdetails.DocumentDetailsPresenter;
import csaboss.scanit.ui.documentdetails.DocumentDetailsScreen;
import csaboss.scanit.utils.RobolectricDaggerTestRunner;

import static csaboss.scanit.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DocumentCaptureTest {

    private DocumentCapturePresenter documentCapturePresenter;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        documentCapturePresenter = new DocumentCapturePresenter();
    }

    @Test
    public void testSaveDocument() {
        DocumentCapureScreen screen = mock(DocumentCapureScreen.class);
        documentCapturePresenter.attachScreen(screen);

        Document document = new Document(1L,"Asd","FGH");
        documentCapturePresenter.saveDocument(document);

        verify(screen, times(1)).showSuccess("Successful save.");
    }

    @Test
    public void testSaveDocumentError() {
        DocumentCapureScreen screen = mock(DocumentCapureScreen.class);
        documentCapturePresenter.attachScreen(screen);
        ArgumentCaptor<Document> captor = ArgumentCaptor.forClass(Document.class);

        documentCapturePresenter.saveDocument(captor.capture());

        verify(screen, times(1)).showError("Error saving document!");
    }


    @After
    public void tearDown() {
        documentCapturePresenter.detachScreen();
    }
}