package com.lolpicker.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lolpicker.dto.ChampionPickDto;
import com.lolpicker.dto.ChampionPicksAndPositionDto;
import com.lolpicker.dto.ChampionSelectAndPicksDto;
import com.lolpicker.dto.CompositionRuleDto;
import com.lolpicker.dto.MessageDto;
import com.lolpicker.model.Champion;
import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.Position;
import com.lolpicker.service.LolService;
import com.lolpicker.service.SecurityService;

@RestController
@RequestMapping("/api")
public class LolController {

	private final LolService lolService;
	private final SecurityService securityService;

	@Autowired
	public LolController(LolService lolService, SecurityService securityService) {
		this.lolService = lolService;
		this.securityService = securityService;
	}

	@PostMapping("/pick")
	public ResponseEntity<ChampionSelectAndPicksDto> getPicks(@RequestBody ChampionPicksAndPositionDto championPicksAndPosition, HttpServletRequest request) {
		if (!this.securityService.checkSpam(request.getRemoteAddr())) {
			ArrayList<ChampionPick> championPicks = new ArrayList<>();
			ArrayList<ChampionPickDto> championPicksDto = championPicksAndPosition.getPicks();
			String position = championPicksAndPosition.getPosition();
			for (ChampionPickDto cpdto: championPicksDto) {
				championPicks.add(new ChampionPick(Champion.valueOf(cpdto.getChampion()), cpdto.isMyTeam(), cpdto.isMyChamp()));
			}
			return new ResponseEntity<>(lolService.getPicks(championPicks, Position.valueOf(position)), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/advice")
	public ResponseEntity<ChampionSelectAndPicksDto> getAdvice(@RequestBody ChampionPicksAndPositionDto championPicksAndPosition, HttpServletRequest request) {
		if (!this.securityService.checkSpam(request.getRemoteAddr())) {
			ArrayList<ChampionPick> championPicks = new ArrayList<>();
			ArrayList<ChampionPickDto> championPicksDto = championPicksAndPosition.getPicks();
			String position = championPicksAndPosition.getPosition();
			for (ChampionPickDto cpdto: championPicksDto) {
				championPicks.add(new ChampionPick(Champion.valueOf(cpdto.getChampion()), cpdto.isMyTeam(), cpdto.isMyChamp()));
			}
			return new ResponseEntity<>(lolService.getAdvice(championPicks, Position.valueOf(position)), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/newRule")
	public ResponseEntity<MessageDto> newRule(@RequestBody CompositionRuleDto compositionRule, HttpServletRequest request) {
		if (!this.securityService.checkSpam(request.getRemoteAddr())) {
			return new ResponseEntity<>(lolService.addRule(compositionRule), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
}
