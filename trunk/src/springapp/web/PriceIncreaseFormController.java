package springapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springapp.service.ProductManager;
import springapp.service.PriceIncrease;

@Controller
@SessionAttributes({"priceIncrease"})
public class PriceIncreaseFormController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private ProductManager productManager;
    private String successView;

    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView post(@ModelAttribute("priceIncrease") PriceIncrease priceIncrease,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        return get(priceIncrease, request, response);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView get(@ModelAttribute("priceIncrease") PriceIncrease priceIncrease,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        int increase = priceIncrease.getPercentage();
        logger.info("Increasing prices by " + increase + "%.");

        productManager.increasePrice(increase);

        logger.info("returning from PriceIncreaseForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @ModelAttribute("priceIncrease")
    protected PriceIncrease formBackingObject() {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return priceIncrease;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
}