package com.tuyano.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.entity.Entry;
import com.tuyano.springboot.repositories.EntryRepository;

@Controller
public class EntryController {
	
	@Autowired
	private EntryRepository entryRepo;
	
	@RequestMapping(value="/entryregist", method=RequestMethod.POST)
	ModelAndView entryRegist(
		@ModelAttribute("entry") 
		@Validated Entry entry,
		BindingResult results,
		ModelAndView mav
		) {
		
		entryRepo.saveAndFlush(entry);
		return new ModelAndView("redirect:/eventlist");
	}
	
	@RequestMapping(value="/entrylist", method=RequestMethod.GET)
	ModelAndView entryList(ModelAndView mav) {
		mav.setViewName("entryList");
		List<Entry> list = entryRepo.findAll();
		mav.addObject("list", list);
		return mav;
	}
}