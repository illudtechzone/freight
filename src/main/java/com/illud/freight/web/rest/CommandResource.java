package com.illud.freight.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.illud.freight.service.CommandService;

@RestController
@RequestMapping("/api")
public class CommandResource {

	private final Logger log = LoggerFactory.getLogger(CommandResource.class);

	@Autowired
	CommandService commandService;

}
