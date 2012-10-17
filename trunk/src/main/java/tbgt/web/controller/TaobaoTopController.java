package tbgt.web.controller;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Express;
import tbgt.domain.Order;
import tbgt.domain.TopSessionKey;
import tbgt.service.OrderService;
import tbgt.service.StatService;
import tbgt.service.TopSessionKeyService;
import tbgt.util.DateUtil;
import tbgt.util.TbgtResourceUtil;
import tbgt.web.criteria.OrderCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/top")
public class TaobaoTopController {
    private TopSessionKeyService topSessionKeyService;

    @Autowired
    public void setTopSessionKeyService(TopSessionKeyService topSessionKeyService) {
        this.topSessionKeyService = topSessionKeyService;
    }

    @RequestMapping(value = "/getSessionKey", method = {RequestMethod.POST, RequestMethod.GET})
    public void getSessionKey(@RequestParam String callbackUrl, HttpServletResponse res) throws Exception {
        TopSessionKey topSessionKey = new TopSessionKey();
        topSessionKey.setCallback_url(callbackUrl);
        topSessionKeyService.insertTopSessionKey(topSessionKey);
        res.sendRedirect(TbgtResourceUtil.getInstance().getMessage("auth.url"));
    }

    @RequestMapping(value = "/returnSessionKey", method = {RequestMethod.POST, RequestMethod.GET})
    public void returnSessionKey(@RequestParam String top_session,HttpServletResponse res) throws Exception {
        TopSessionKey topSessionKey = topSessionKeyService.getLastTopSessionKey();
        String callbackUrl = topSessionKey.getCallback_url();
        topSessionKey.setSessionkey(top_session);
        topSessionKeyService.updateTopSessionKey(topSessionKey);
        res.sendRedirect(callbackUrl + "?top_session=" + top_session);
    }


}
