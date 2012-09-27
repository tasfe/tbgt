package tbgt.web.controller;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tbgt.common.CustomJsonDateSerializer;
import tbgt.service.StatService;
import tbgt.util.DateUtil;

import java.io.Serializable;
import java.util.*;

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
        Criteria criteria = new Criteria();
        criteria.setFromDate(new Date());
        criteria.setToDate(new Date());
        return profit(criteria);
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public ModelAndView viewProfit(@ModelAttribute (value = "criteria") Criteria criteria, BindingResult result) {
        return profit(criteria);
    }

    public ModelAndView profit(Criteria criteria) {
        ModelAndView mv = new ModelAndView("stat");
        mv.addObject("criteria",criteria);
        return mv;
    }

    class Criteria{
        @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
        private Date fromDate;
        @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
        private Date toDate;
        private String name;

        public Date getFromDate() {
            return fromDate;
        }

        public void setFromDate(Date fromDate) {
            this.fromDate = fromDate;
        }

        public Date getToDate() {
            return toDate;
        }

        public void setToDate(Date toDate) {
            this.toDate = toDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
