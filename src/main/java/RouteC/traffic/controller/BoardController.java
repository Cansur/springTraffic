package RouteC.traffic.controller;

import RouteC.traffic.domain.Board;
import RouteC.traffic.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String boardMain() {
        return "board/main";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    @GetMapping("/board/write")
    public String boardWriterForm() {
        return "board/write";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file, String title) throws Exception {

        // 제목 빈칸이면 글 작성 실패
        if (title.isEmpty()) {
            model.addAttribute("message", "제목을 작성해 주세요.");
            model.addAttribute("searchUrl", "/board/write");

        } else { // 제목 빈칸 아니면 정상 작동
            boardService.write(board, file);

            model.addAttribute("message", "글 작성이 완료되었습니다.");
            model.addAttribute("searchUrl", "/board/list");

        }
        return "board/message";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id, Board board) {

        model.addAttribute("newLineChar", '\n'); // 게시글 엔터 출력 구현

        model.addAttribute("contents",boardService.boardView(id).getContent());

        model.addAttribute("board", boardService.boardView(id));

        return "board/view";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "board/modify";
    }

    @PostMapping("board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }
}