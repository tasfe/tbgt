package tbgt.web.paging;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class PagingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String iDisplayStartStr = httpRequest.getParameter("iDisplayStart");
        PagingContextHolder.clear();
        if (iDisplayStartStr != null) {
            int iDisplayStart = Integer.valueOf(iDisplayStartStr);
            int iDisplayLength = Integer.valueOf(httpRequest.getParameter("iDisplayLength"));
            int sEcho = Integer.valueOf(httpRequest.getParameter("sEcho"));
            int iSortCol_0 = Integer.valueOf(httpRequest.getParameter("iSortCol_0"));
            PaginationTO paginationTO = new PaginationTO();
            paginationTO.setiDisplayLength(iDisplayLength);
            paginationTO.setiDisplayStart(iDisplayStart);
            paginationTO.setsEcho(sEcho);
            paginationTO.setsSearch(httpRequest.getParameter("sSearch"));
            paginationTO.setiSortCol_0(iSortCol_0);
            paginationTO.setsSortDir_0(httpRequest.getParameter("sSortDir_0"));
            paginationTO.setsSortColumn_0(httpRequest.getParameter("mDataProp_"+iSortCol_0));
            PagingContextHolder.set(paginationTO);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
