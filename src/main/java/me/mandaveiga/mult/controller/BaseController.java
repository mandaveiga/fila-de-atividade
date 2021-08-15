package me.mandaveiga.mult.controller;

import me.mandaveiga.mult.model.AbstractModel;
import me.mandaveiga.mult.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public abstract class BaseController<T extends AbstractModel> {

	protected BaseService<T> service;

	@Autowired
	public BaseController(BaseService<T> service) {
		this.service = service;
	}
}
