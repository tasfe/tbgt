package tbgt.web.controller;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addBaobei(@ModelAttribute(value = "baobei") Baobei baobei, BindingResult result) {
        ModelAndView mv = new ModelAndView("baobei");
        //todo..validator
        if (!result.hasErrors()) {
            baobei.setId(new Random().nextInt());
            mv.addObject("baobei", baobei);
        }
        List<Baobei> baobeis = new ArrayList<Baobei>();
        Baobei e = new Baobei();
        e.setId(1234);
        e.setName("name");
        e.setTaobaoLink("url");
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
