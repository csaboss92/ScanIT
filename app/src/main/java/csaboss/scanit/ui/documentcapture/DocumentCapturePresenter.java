package csaboss.scanit.ui.documentcapture;


import csaboss.scanit.ui.Presenter;


public class DocumentCapturePresenter extends Presenter<DocumentCapureScreen> {



    public DocumentCapturePresenter() {
    }

    @Override
    public void attachScreen(DocumentCapureScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void captureDocument(){
        if (1==1)
        {
            screen.showSuccess("Success");
        }else{
            screen.showError("Failed");
        }
    }
}