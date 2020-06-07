package com.lolpicker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lolpicker.dto.ChampionPickDto;
import com.lolpicker.dto.ChampionPicksAndPositionDto;
import com.lolpicker.dto.ChampionSelectAndPicksDto;
import com.lolpicker.model.Champion;
import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.Position;
import com.lolpicker.service.LolService;

@RestController
@RequestMapping("/api")
public class LolController {

	private final LolService lolService;

	@Autowired
	public LolController(LolService lolService) {
		this.lolService = lolService;
	}

	@PostMapping("/pick")
	public ResponseEntity<ChampionSelectAndPicksDto> getPicks(@RequestBody ChampionPicksAndPositionDto championPicksAndPosition) {
		ArrayList<ChampionPick> championPicks = new ArrayList<>();
		ArrayList<ChampionPickDto> championPicksDto = championPicksAndPosition.getPicks();
		String position = championPicksAndPosition.getPosition();
		for (ChampionPickDto cpdto: championPicksDto) {
			championPicks.add(new ChampionPick(Champion.valueOf(cpdto.getChampion()), cpdto.isMyTeam(), cpdto.isMyChamp()));
		}
		return new ResponseEntity<>(lolService.getPicks(championPicks, Position.valueOf(position)), HttpStatus.OK);
	}
	
	@PostMapping("/advice")
	public ResponseEntity<ChampionSelectAndPicksDto> getAdvice(@RequestBody ChampionPicksAndPositionDto championPicksAndPosition) {
		ArrayList<ChampionPick> championPicks = new ArrayList<>();
		ArrayList<ChampionPickDto> championPicksDto = championPicksAndPosition.getPicks();
		String position = championPicksAndPosition.getPosition();
		for (ChampionPickDto cpdto: championPicksDto) {
			championPicks.add(new ChampionPick(Champion.valueOf(cpdto.getChampion()), cpdto.isMyTeam(), cpdto.isMyChamp()));
		}
		return new ResponseEntity<>(lolService.getAdvice(championPicks, Position.valueOf(position)), HttpStatus.OK);
	}
	
}
