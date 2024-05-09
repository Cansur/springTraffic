package RouteC.traffic.controller;

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
        return "board/main";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, String searchKeyword) {
        return "board/list";
    }

    @GetMapping("/board/write")
    public String boardWriterForm() {
        return "board/write";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        return "board/view";
    }
}