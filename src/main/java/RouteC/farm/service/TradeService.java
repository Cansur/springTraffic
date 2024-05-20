package RouteC.farm.service;

import RouteC.farm.domain.Trade;
import RouteC.farm.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public List<Trade> getTradesByItem(String item) {
        return tradeRepository.findByItem(item);
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
