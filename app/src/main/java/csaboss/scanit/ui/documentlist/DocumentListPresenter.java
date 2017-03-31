package csaboss.scanit.ui.documentlist;


import java.util.ArrayList;

import csaboss.scanit.ui.Presenter;


public class DocumentListPresenter extends Presenter<DocumentListScreen> {



    public DocumentListPresenter() {
    }

    @Override
    public void attachScreen(DocumentListScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void getDocuments(){
        ArrayList<String> documents = new ArrayList<String>();
        documents.add("ELSO DOKUMENTUM");
        screen.showDocumentList(documents);
    }
}