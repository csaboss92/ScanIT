package csaboss.scanit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import csaboss.scanit.BuildConfig;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentlist.DocumentListPresenter;
import csaboss.scanit.ui.documentlist.DocumentListScreen;

import csaboss.scanit.utils.RobolectricDaggerTestRunner;

import static csaboss.scanit.TestHelper.setTestInjector;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DocumentListTest {

    private DocumentListPresenter documentListPresenter;
    @Captor
    ArgumentCaptor<List<Document>> captor;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        setTestInjector();
        documentListPresenter = new DocumentListPresenter();
    }

    @Test
    public void testGetDocuments(){
        DocumentListScreen screen = mock(DocumentListScreen.class);
        documentListPresenter.attachScreen(screen);

        documentListPresenter.getDocuments();

        verify(screen,times(1)).showDocumentList(captor.capture());
        assertEquals(2,captor.getValue().size());

    }

    @After
    public void tearDown() {
        documentListPresenter.detachScreen();
    }
}