package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	public S baseService;

	public ORSResponse Validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();
		res.setSuccess(true);

		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;
	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

		ORSResponse res = Validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		T dto = (T) form.getDTO();

		if (dto.getId() != null && dto.getId() > 0) {
			baseService.update(dto);
			res.addMessage("Data Updated Successfuylly");
		} else {
			Long pk = baseService.add(dto);
			res.addData(pk);
			res.addMessage("Data Added Successfully");
		}
		return res;

	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		baseService.delete(id);
		res.addMessage("Data deleted SucessFully");
		return res;

	}

	@PostMapping("search")
	public ORSResponse search(@RequestBody F form) {
		ORSResponse res = new ORSResponse();
		T dto = (T) form.getDTO();
		List list = baseService.search(dto, 0, 5);
		if (list.size() == 0) {
			res.addMessage("No Record Found..");
		} else {
			res.addData(list);
		}
		return res;

	}
}
