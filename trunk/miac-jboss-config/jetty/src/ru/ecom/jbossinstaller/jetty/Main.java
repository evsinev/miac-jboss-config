package ru.ecom.jbossinstaller.jetty;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import ru.ecom.jbossinstaller.service.ConfigServiceImpl;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {

        int port = 2410;
        if (args.length >= 1)
            port = Integer.parseInt(args[0]);
        Server server = new Server(port);

        Context root = new Context(server,"/",Context.ALL);
        root.addServlet(new ServletHolder(new ConfigServiceImpl()), "/configService");
        root.addServlet(new ServletHolder(new ResourceServlet()), "/*");

//        ResourceHandler resource_handler = new ResourceHandler();
//        resource_handler.setWelcomeFiles(new String[]{"JBossInstaller.html"});
//        resource_handler.setResourceBase(args.length == 2 ? args[1] : "jar:file:jbossinstaller.jar!/");
//
//        ServletHandler servletHandler = new ServletHandler();
//        servletHandler.addServletWithMapping("ru.ecom.jbossinstaller.service.ConfigServiceImpl", "/configService") ;
//
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[]{resource_handler, servletHandler, new DefaultHandler()});
//        server.setHandler(handlers);


        server.start();

        System.out.println("\nWeb server is started.\nGo to http://localhost:"+port+"/") ;

        server.join();


    }
}
