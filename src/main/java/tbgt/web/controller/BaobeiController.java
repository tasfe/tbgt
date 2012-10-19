package tbgt.web.controller;


import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Sku;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import tbgt.domain.Baobei;
import tbgt.domain.BaobeiSku;
import tbgt.service.BaoBeiService;
import tbgt.util.TaobaoClientUtil;
import tbgt.web.paging.PaginationTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/baobei")
public class BaobeiController {

    private BaoBeiService baoBeiService;

    @Autowired
    public void setBaoBeiService(BaoBeiService baoBeiService) {
        this.baoBeiService = baoBeiService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewBaobei() {
        ModelAndView mv = new ModelAndView("baobei");
//        mv.addObject("baobeis", baoBeiService.getAllBaobei());
        return mv;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PaginationTO list() {
        return baoBeiService.getBaobei();
    }

    @RequestMapping(value = "/newBaobei", method = RequestMethod.GET)
    public ModelAndView newBaobei() {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        mv.addObject("baobei", new Baobei());
        return mv;
    }

    @RequestMapping(value = "/updateBaobei", method = RequestMethod.GET)
    public ModelAndView updateBaobei(@RequestParam long id) {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        Baobei baobei = baoBeiService.getBaobeiById(id);
        mv.addObject("baobei", baobei);
        return mv;
    }

    @RequestMapping(value = "/deleteBaobei", method = RequestMethod.POST)
    public ModelAndView deleteBaobei(@RequestParam long id) {
        ModelAndView mv = new ModelAndView("baobei");
        baoBeiService.deleteBaobei(id);
//        mv.addObject("baobeis", baoBeiService.getAllBaobei());
        return mv;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveBaobei(@ModelAttribute(value = "baobei") Baobei baobei, BindingResult result) {
        ModelAndView mv = new ModelAndView("baobei");
        //todo..validator
        if (!result.hasErrors()) {
            baoBeiService.updateBaobeiCustomAttr(baobei);
        }
        return mv;
    }

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refresh(@RequestParam String top_session) throws Exception {
        ModelAndView mv = new ModelAndView("baobei");
        baoBeiService.refreshBaobei(top_session);
        return mv;
    }

    @RequestMapping(value = "/refreshBaobei", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView refreshBaobei(@RequestParam String top_session,@RequestParam long bbid) throws Exception {
        ModelAndView mv = new ModelAndView("baobei");
        baoBeiService.refreshOneBaobei(top_session,bbid);
        return mv;
    }

}
