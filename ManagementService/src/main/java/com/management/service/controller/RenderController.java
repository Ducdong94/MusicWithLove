package com.management.service.controller;

import com.management.service.entity.Category;
import com.management.service.entity.Playlist;
import com.management.service.repository.CategoryRepository;
import com.management.service.repository.PlayListRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RenderController {
    @Autowired
    PlayListRepsitory playListRepsitory;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public String renderHome(Model model) {
        try {
            List<Playlist> playlists = playListRepsitory.findByName("TOP 100 VIỆT NAM Nhạc Trẻ");
            model.addAttribute("topvnid",playlists.get(0).getId());
            model.addAttribute("topvn",playlists.get(0).getPlaylistSong());
            playlists = playListRepsitory.findByName("TOP 100 US-UK Christian & Gospel");
            model.addAttribute("topusid",playlists.get(0).getId());
            model.addAttribute("topus",playlists.get(0).getPlaylistSong());
            Category category = categoryRepository.findById("EDM").orElse(null);
            model.addAttribute("edms",category.getCategorySong());
            return "index";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }
}
