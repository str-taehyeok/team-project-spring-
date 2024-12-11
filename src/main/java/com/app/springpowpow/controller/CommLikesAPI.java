package com.app.springpowpow.controller;

import com.app.springpowpow.domain.CommLikesDTO;
import com.app.springpowpow.service.CommLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/likeList/")
public class CommLikesAPI {

    private final CommLikesService commLikesService;

    @GetMapping("list")
    public void goToList(Model model) {
//        List<CommLikesDTO> commLikes = commLikesService.getList();
//        model.addAttribute("commLike", commLikes);
    }

}
