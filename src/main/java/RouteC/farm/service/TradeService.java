package RouteC.farm.service;

import RouteC.farm.domain.Trade;
import RouteC.farm.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Page<Trade> tradeList(Pageable pageable) {

        return tradeRepository.findAll(pageable);
    }
}
