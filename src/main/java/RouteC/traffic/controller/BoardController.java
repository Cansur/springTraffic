package RouteC.traffic.controller;

import java.util.List;

import RouteC.traffic.domain.Board;
import RouteC.traffic.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String boardPage(Model model, String searchKeyword) {
        // searchKeyword 파라미터 값에따라 변환
        List<Board> list = searchKeyword == null ? boardService.getAllBoard() : boardService.getSearchBoard(searchKeyword);
        model.addAttribute("board", list);
        return "board/boardMain";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        return "board/boardView";
    }

}