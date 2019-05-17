package com.sun.elasticsearch.controller;

import com.sun.elasticsearch.dao.PoemRepository;
import com.sun.elasticsearch.entity.Poem;
import com.sun.elasticsearch.service.impl.PoemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 喻湘东
 * @email jyxd1997@163.com
 * @create 2019-05-17 23:19:41
 * @describe
 */
@Controller
public class WebController {
    @Autowired
    private PoemServiceImpl poemService;

    @Autowired
    PoemRepository poemRepository;


    @GetMapping("/")
    public String index(Model model) {
        List<Poem> poems = new ArrayList<>();
        poems.add(new Poem(4, "湘春夜月·近清明", "近清明,翠禽枝上消魂,可惜一片清歌，都付与黄昏。欲共柳花低诉，怕柳花轻薄，不解伤春。念楚乡旅宿，柔情别绪，谁与温存。"));
        poems.add(new Poem(5, "卜算子·不是爱风尘", "不是爱风尘，似被前缘误。花落花开自有时，总赖东君主。\n" +
                "去也终须去，住也如何住！若得山花插满头，莫问奴归处"));
        poems.add(new Poem(6, "御街行·秋日怀旧", "纷纷坠叶飘香砌。夜寂静，寒声碎。真珠帘卷玉楼空，天淡银河垂地。年年今夜，月华如练，长是人千里。"));

        for (int i = 0; i < poems.size(); i++) {
            poemService.save(poems.get(i));
        }
        model.addAttribute("poems", poems);

        return "/index";

    }



    @PostMapping("/search")
    public String search(String content, @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                         @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,Model model) {
        Pageable pageable = new PageRequest(pageIndex,pageSize);
        Page<Poem> poems = poemService.search(content,pageable);
        List<Poem> list = poems.getContent();
        model.addAttribute("poems",list);
        return "/list";
    }
}
