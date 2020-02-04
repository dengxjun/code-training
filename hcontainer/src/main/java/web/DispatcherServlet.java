package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.method.HandlerMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 邓小军
 * @since: 2020/1/20 15:18
 *
 */
public class DispatcherServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private HandlerMapping mapping;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        HandlerExecuteChain handlerExecuteChain = HandlerExecuteChain.build(req, mapping);
        HandlerExecuteAdaptor ha = new HandlerExecuteAdaptor();

        if (!handlerExecuteChain.applyPreExecuteHandle(req, resp)){
            return;
        }

        // do hadle invo
        Object result = null;
        try {
            result = ha.invokeHandleMethod(req, resp, (HandlerMethod)handlerExecuteChain.getHandler());
        } catch (Exception e) {
            logger.error("error occurs when invoke invokeHandleMethod",e);
        }

        handlerExecuteChain.applyPostExecuteHandle(req, resp);

        // output
        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
            printWriter.print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.flush();
            printWriter.close();
        }
    }


    @Override
    public void init() throws ServletException {
        WebApplicationContext wac = (WebApplicationContext)getServletContext().getAttribute(WebApplicationContext.WebApplicationContext);
        mapping = new HandlerMapping();
        mapping.init(wac);
    }
}
