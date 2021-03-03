package com.tuyano.springboot.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.entity.Event;
import com.tuyano.springboot.repositories.EventRepository;

@Controller
public class EventController {
	@Autowired
	private EventRepository eventRepo;
	
	@RequestMapping(value="/eventlist", method=RequestMethod.GET)
	public ModelAndView eventList(ModelAndView mav) {
		mav.setViewName("eventList");
		List<Event> list = eventRepo.findAll();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/eventcreate", method=RequestMethod.GET)
	public ModelAndView eventCreate(ModelAndView mav) {
		mav.setViewName("createEvent");
		Event event = new Event();
		mav.addObject("event", event);
		return mav;
	}
	
	@RequestMapping(value="/eventcreate", method=RequestMethod.POST)
	public ModelAndView eventAdd(
			@ModelAttribute("event") @Validated Event event,
			BindingResult results,
			ModelAndView mav) {
		
		if (results.hasErrors()) {
			mav.setViewName("createEvent");
			mav.addObject("event", event);
			return mav;
		}
		eventRepo.saveAndFlush(event);
		return new ModelAndView("redirect:/eventlist");
	}
	
	@PostConstruct
	void init() {
		Event event1 = new Event();
		event1.setEventName("商品打ち合わせ");
		event1.setEventInfo("新商品A開発検討（開催場所：ルームA)");
		event1.setEventTime("2021/3/1 14:00-15:00");
		eventRepo.saveAndFlush(event1);
		
		Event event2 = new Event();
		event2.setEventName("販売検討会");
		event2.setEventInfo("新商品B開発検討（開催場所：ルームA)");
		event2.setEventTime("2021/3/1 16:00-17:00");
		eventRepo.saveAndFlush(event2);
	}
}
