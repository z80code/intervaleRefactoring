package server;

import com.intervale.controllers.RestServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.ResourceBundle;

public class MainServer {

    private Server server;

    public MainServer() {

        ResourceBundle bundle = ResourceBundle.getBundle("server");
        int port = Integer.parseInt(bundle.getString("server.port"));

        String restUrl = bundle.getString("server.url.service.rest");

        this.server = new Server(port);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);


        handler.addServlet(RestServlet.class, restUrl);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        //resource_handler.setWelcomeFiles(new String[]{"index.html","index.html" });

        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, handler});
        server.setHandler(handlers);
    }

    public void run() throws Exception {
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
