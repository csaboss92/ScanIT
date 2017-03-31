package csaboss.scanit.ui.documentdetails;


import csaboss.scanit.ui.Presenter;


public class DocumentDetailsPresenter extends Presenter<DocumentDetailsScreen> {



    public DocumentDetailsPresenter() {
    }

    @Override
    public void attachScreen(DocumentDetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void getDocument(int id){
        //TODO
        screen.showDocument("document");
    }
    public void deleteDocument(int id){
        if (1==1)
        {
            screen.deleteSuccess("Success");
        }else{
            screen.deleteSuccess("Failed");
        }
    }
}