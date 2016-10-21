package helpers;

import com.intervale.configurations.ViewConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherHelper {
    public static void dispatch(String view, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(
                ViewConfig.getInstance().getBasePath()+"/"+
                        view + ViewConfig.getInstance().getSuffix()
        ).forward(req, resp);
    }
}
