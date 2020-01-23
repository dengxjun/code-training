package web;

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

    private HandlerMapping mapping;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp){
        MethodHander methodHander = mapping.getHandler(getPath(req));
        if (methodHander != null){
            Object result = mapping.invoke(methodHander, req);
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
    }

    private String getPath(HttpServletRequest req){
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        return uri.replaceFirst(contextPath,"").replaceFirst(servletPath,"");
    }

    @Override
    public void init() throws ServletException {
        WebApplicationContext wac = (WebApplicationContext)getServletContext().getAttribute(WebApplicationContext.WebApplicationContext);
        mapping = new HandlerMapping();
        mapping.init(wac);
    }
}
