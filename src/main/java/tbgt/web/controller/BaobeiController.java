package tbgt.web.controller;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Baobei;
import tbgt.domain.Price;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/baobei")
public class BaobeiController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewBaobei() {
        ModelAndView mv = new ModelAndView("baobei");
        mv.addObject("baobei", new Baobei());
//        mv.addObject("baobeis", new ArrayList());
        return mv;
    }

    @RequestMapping(value = "/newBaobei", method = RequestMethod.GET)
    public ModelAndView newBaobei() {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        mv.addObject("baobei", new Baobei());
//        mv.addObject("baobeis", new ArrayList());
        return mv;
    }

    @RequestMapping(value = "/updateBaobei", method = RequestMethod.GET)
    public ModelAndView updateBaobei(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        Baobei baobei = new Baobei();
        baobei.setId(Integer.valueOf(id));
        baobei.setName("name");
        baobei.setTaobaoId("234445");
        baobei.setSaleTitle("sale title");
        Price price = new Price();
        price.setId(12345);
        price.setPurchasePrice(new BigDecimal("123.45"));
        price.setRecommendedPrice(new BigDecimal("123.455"));
        price.setSalePrice(new BigDecimal("123.4555"));
        baobei.setPrice(price);
        mv.addObject("baobei", baobei);
        return mv;
    }

    @RequestMapping(value = "/deleteBaobei", method = RequestMethod.POST)
    public ModelAndView deleteBaobei(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("baobei");
        System.out.println("delete baobei = " + id);
        List<Baobei> baobeis = new ArrayList<Baobei>();
        Baobei e = new Baobei();
        e.setId(1234);
        e.setName("name");
        e.setTaobaoId("234445");
        e.setSaleTitle("sale title");
        Price price = new Price();
        price.setId(12345);
        price.setPurchasePrice(new BigDecimal("123.45"));
        price.setRecommendedPrice(new BigDecimal("123.455"));
        price.setSalePrice(new BigDecimal("123.4555"));
        e.setPrice(price);
        baobeis.add(e);
        baobeis.add(e);
        baobeis.add(e);
        mv.addObject("baobeis", baobeis);
        return mv;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView viewOrder() {
        ModelAndView mv = new ModelAndView("order");
        mv.addObject("baobei", new Baobei());
//        mv.addObject("baobeis", new ArrayList());
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveBaobei(@ModelAttribute(value = "baobei") Baobei baobei, BindingResult result) {
        ModelAndView mv = new ModelAndView("baobei");
        //todo..validator
        if (!result.hasErrors()) {
            baobei.setId(new Random(1000).nextInt());
            mv.addObject("baobei", baobei);
        }
        List<Baobei> baobeis = new ArrayList<Baobei>();
        Baobei e = new Baobei();
        e.setId(1234);
        e.setName("name");
        e.setTaobaoId("234445");
        e.setSaleTitle("sale title");
        Price price = new Price();
        price.setId(12345);
        price.setPurchasePrice(new BigDecimal("123.45"));
        price.setRecommendedPrice(new BigDecimal("123.455"));
        price.setSalePrice(new BigDecimal("123.4555"));
        e.setPrice(price);
        baobeis.add(baobei);
        baobeis.add(e);
        baobeis.add(e);
        baobeis.add(e);
        mv.addObject("baobeis", baobeis);
        return mv;
    }

}
