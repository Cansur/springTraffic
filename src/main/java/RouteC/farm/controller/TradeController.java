package RouteC.farm.controller;

import RouteC.farm.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/trade/list")
    public String  tradeList(){
        return "trade/list";
    }
}
