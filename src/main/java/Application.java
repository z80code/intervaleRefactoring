import com.google.inject.Guice;
import com.google.inject.Injector;
import com.intervale.configurations.BillingModule;
import com.intervale.services.InitBaseService;
import com.intervale.services.InterfaceInitBaseService;
import server.MainServer;

public class Application {

    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new BillingModule());
        InterfaceInitBaseService initBaseService = injector.getInstance(InitBaseService.class);

        initBaseService.start("commissions.xml");

        MainServer mainServer = new MainServer();
        mainServer.run();
    }

}
