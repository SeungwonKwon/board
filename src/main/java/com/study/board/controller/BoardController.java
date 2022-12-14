package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")//localhost:8080/board/write
    public String boardWriteForm(){

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model) {

        boardService.write(board);

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id",
                            direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword, String searchTitle){

        Page<Board> list = null;

        if(searchKeyword == null){
            if(searchTitle == null){
                list = boardService.boardList(pageable);
            }
            else{
                list = boardService.boardSearchTitle(searchTitle, pageable);
            }
        }
        else{
            list = boardService.boardSearchCategory(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());


        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable Integer id){

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/sign/{id}")
    public String boardSign(@PathVariable("id") Integer id, Model model, Board board){

        Board boardTemp = boardService.boardView(id);
        if(boardTemp.currentpeople < boardTemp.maxpeople) {
            boardTemp.currentpeople += 1;
            boardService.write(boardTemp);
            model.addAttribute("message", "신청되었습니다.");
        }
        else{
            model.addAttribute("message", "정원 초과입니다");
        }
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @PostMapping("board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model){

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardTemp.setCategory(board.getCategory());
        boardTemp.setDate(board.getDate());
        boardTemp.setNoon(board.getNoon());
        boardTemp.setHour(board.getHour());
        boardTemp.setMinute(board.getMinute());
        boardTemp.setMaxpeople(board.getMaxpeople());
        boardTemp.setGenderdisplay(board.getGenderdisplay());
        boardTemp.setPlacename(board.getPlacename());
        boardTemp.setPosition(board.getPosition());

        boardService.write(boardTemp);

        model.addAttribute("message","글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/api/posts")
    @ResponseBody
    public List<Board> getAllData(){
        return boardService.getAllData();
    }

    @GetMapping("/api/posts/{category}")
    @ResponseBody
    public List<Board> getCategoryData(@PathVariable String category){
        return boardService.getCategoryData(category);
    }

    @GetMapping("/api/posts/id/{id}")
    @ResponseBody
    public Board getIdData(@PathVariable("id") Integer id){
        return boardService.boardView(id);
    }

    @GetMapping("/api/write/{data}")
    public String DataSave(@PathVariable String data, Model model, Board board){
        JSONObject jobject = new JSONObject(data);
        board.title = jobject.getString("title");
        board.content = jobject.getString("content");
        board.category = jobject.getString("category");
        board.date = jobject.getString("date");
        board.noon = jobject.getString("noon");
        board.hour = jobject.getString("hour");
        board.minute = jobject.getString("minute");
        board.currentpeople = 1;
        board.maxpeople = jobject.getInt("maxpeople");
        board.genderdisplay = jobject.getString("genderdisplay");
        board.placename = jobject.getString("placename");
        board.position = jobject.getString("position");


        boardService.write(board);

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @PostMapping("/api/posts/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, @RequestBody Board board, Model model){
        Board boardTemp = boardService.boardView(id);

        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardTemp.setCategory(board.getCategory());
        boardTemp.setDate(board.getDate());
        boardTemp.setNoon(board.getNoon());
        boardTemp.setHour(board.getHour());
        boardTemp.setMinute(board.getMinute());
        boardTemp.setMaxpeople(board.getMaxpeople());
        boardTemp.setGenderdisplay(board.getGenderdisplay());
        boardTemp.setPlacename(board.getPlacename());
        boardTemp.setPosition(board.getPosition());

        boardService.write(boardTemp);

        model.addAttribute("message","글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }

    @GetMapping("/api/delete/{id}")
    public String deletePost(@PathVariable Integer id, Model model){

        boardService.boardDelete(id);

        return "redirect:/";
    }
}