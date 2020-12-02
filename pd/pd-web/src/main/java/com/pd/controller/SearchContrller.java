package com.pd.controller;

import com.pd.pojo.Item;
import com.pd.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchContrller {

    @Autowired
    private SearchService searchService;

    //http://localhost/search/toSearch.html?key=%E7%94%B5%E8%84%91
    @GetMapping("/search/toSearch.html")
    public String search(Model model,String key){
        List<Item> list = searchService.search(key);
        model.addAttribute("list", list);
        return "/search.jsp";
    }

}
