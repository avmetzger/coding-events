package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title","Coding Events");
        return "events/index";
    }

    @GetMapping("/create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title","Create Event");
        return"events/create";
    }

    @PostMapping("/create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("/delete")
    public String renderDeleteEventsForm(Model model) {
        model.addAttribute("title","Delete Event");
        model.addAttribute("events",EventData.getAll());
        return "events/delete";
    }

    @PostMapping("/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds ){
        if (eventIds != null) {
            for (int id : eventIds){
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("/edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        model.addAttribute("title","Edit Event " + EventData.getById(eventId).getName() + " (id = " + eventId + ")");
        model.addAttribute("event", EventData.getById(eventId));
        return "events/edit";
    }

    @PostMapping("/edit/{eventId}")
    public String processEditForm(int eventId, String name, String description) {
        Event event = EventData.getById(eventId);
        event.setName(name);
        event.setDescription(description);
        return "redirect:redirect:";
    }
}
