package tbgt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tbgt.domain.Baobei;
import tbgt.service.BaoBeiService;
import tbgt.web.paging.PaginationTO;

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
    public @ResponseBody
    PaginationTO list() {
        return baoBeiService.getBaobeiWithPaging();
    }

    @RequestMapping(value = "/newBaobei", method = RequestMethod.GET)
    public ModelAndView newBaobei() {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        mv.addObject("baobei", new Baobei());
        return mv;
    }

    @RequestMapping(value = "/updateBaobei", method = RequestMethod.GET)
    public ModelAndView updateBaobei(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("baobeiInfo");
        Baobei baobei = baoBeiService.getBaobeiById(id);
        mv.addObject("baobei", baobei);
        return mv;
    }

    @RequestMapping(value = "/deleteBaobei", method = RequestMethod.POST)
    public ModelAndView deleteBaobei(@RequestParam int id) {
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
            if (baobei.getId() == 0) {
                baoBeiService.insertBaobei(baobei);
            } else {
                baoBeiService.updateBaobei(baobei);
            }
        }
        return mv;
    }

}
