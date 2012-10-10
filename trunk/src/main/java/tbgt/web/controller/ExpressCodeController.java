package tbgt.web.controller;


import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Express;
import tbgt.domain.ExpressCode;
import tbgt.domain.Order;
import tbgt.service.ExpressCodeService;
import tbgt.service.OrderService;
import tbgt.service.StatService;
import tbgt.web.criteria.ExpressCodeCriteria;
import tbgt.web.criteria.OrderCriteria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tools")
public class ExpressCodeController {
    private ExpressCodeService expressCodeService;

    @Autowired
    public void setExpressCodeService(ExpressCodeService expressCodeService) {
        this.expressCodeService = expressCodeService;
    }


    @RequestMapping(value = "/express", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView express(@ModelAttribute(value="expressCodeCriteria")ExpressCodeCriteria expressCodeCriteria) {
        if(expressCodeCriteria == null){
            expressCodeCriteria = new ExpressCodeCriteria();
        }
        List<ExpressCode> expressCodes = expressCodeService.getExpressCode(expressCodeCriteria);
        ModelAndView mv = new ModelAndView("tools");
        mv.addObject("expressCodeCriteria",expressCodeCriteria);
        mv.addObject("expressCodes",expressCodes);
        return mv;
    }

}
