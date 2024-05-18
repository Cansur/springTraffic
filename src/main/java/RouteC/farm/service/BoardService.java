package RouteC.farm.service;

import RouteC.farm.domain.Board;
import RouteC.farm.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /**
     * 게시글 리스트 처리
     */
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    /**
     * 게시글 작성 처리
     */
    public void write(Board board, MultipartFile file) throws Exception {

        if(file.isEmpty()){
            // 게시판 작성
            boardRepository.save(board);
        } else {
            // 파일 업로드, static 페이지 생성
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);

            boardRepository.save(board);
        }
    }

    /**
     * 키워드 검색 기능
     */
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    /**
     * 특정 게시글 불러오기
     */
    public Board boardView(Integer id) {

        Optional<Board> newBoard = this.boardRepository.findById(id);

        if (newBoard.isPresent()) {
            Board board_view = newBoard.get();
            board_view.setView(board_view.getView() + 1);
            this.boardRepository.save(board_view);
            return board_view;
        } else {
            return boardRepository.findById(id).get(); // 기존 방법, 조회수 증가 x 버전
        }
    }

    /**
     * 특정 게시글 삭제
     */
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }
}
