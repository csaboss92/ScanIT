package csaboss.scanit.ui.login;


import csaboss.scanit.ui.Presenter;


public class LoginPresenter extends Presenter<LoginScreen> {



    public LoginPresenter() {
    }

    public void login(String name, String password){
        if (1==1) {
            screen.loginSuccess();
        }else {
            screen.loginError("Login Failed");
        }
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}