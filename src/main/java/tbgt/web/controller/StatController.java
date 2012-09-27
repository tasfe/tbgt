package tbgt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Baobei;
import tbgt.domain.Order;
import tbgt.domain.SoldBaobei;
import tbgt.service.BaoBeiService;
import tbgt.service.StatService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/stat")
public class StatController {
    private StatService statService;

    @Autowired
    public void setStatService(StatService statService) {
        this.statService = statService;
    }




    @RequestMapping(value = "/profit", method = RequestMethod.GET)
    public ModelAndView viewDefaultProfit() {

        return profit(new Date(),new Date());
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public ModelAndView viewProfit(@RequestParam Date fromDate,@RequestParam Date toDate) {
        return profit(fromDate,toDate);
    }

    public ModelAndView profit(Date fromDate, Date toDate) {
        ModelAndView mv = new ModelAndView("stat");
        mv.addObject("fromDate",fromDate);
        mv.addObject("toDate",toDate);
        return mv;
    }



}
