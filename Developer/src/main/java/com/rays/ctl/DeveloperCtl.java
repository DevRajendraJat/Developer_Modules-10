package com.rays.ctl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.DeveloperDTO;
import com.rays.form.DeveloperForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.DeveloperServiceInt;

@RestController
@RequestMapping(value = "Developer")
public class DeveloperCtl {

	@Autowired
	public DeveloperServiceInt DevService;

	@Autowired
	public AttachmentServiceInt Aservice;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid DeveloperForm form, BindingResult br) {

		ORSResponse res = new ORSResponse(true);

		if (!res.isSuccess()) {
			return res;
		}
		DeveloperDTO dto = new DeveloperDTO();
		dto.setId(form.getId());
		dto.setDeveloperName(form.getDeveloperName());
		dto.setWork(form.getWork());
		dto.setProject(form.getProject());
		dto.setStatus(form.getStatus());
		dto.setSubmitDate(form.getSubmitDate());

		if (dto.getId() != null && dto.getId() > 0) {
			DevService.update(dto);
			res.addMessage("Data Updated suuccssfully");
		} else {
			DevService.add(dto);
			res.setSuccess(true);
			res.addData(dto.getId());
			res.addMessage("data Added successfully");
		}
		return res;

	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		DeveloperDTO dto = new DeveloperDTO();

		DevService.delete(id);
		res.addMessage("Data deleted Successfully");
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		DeveloperDTO dto = new DeveloperDTO();

		if (dto != null) {
			dto = DevService.findById(id);
			res.addData(dto);
		}

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody DeveloperForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		DeveloperDTO dto = new DeveloperDTO();

		dto.setId(form.getId());
		dto.setDeveloperName(form.getDeveloperName());

		List list = DevService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Record Not Found...!!!!!");

		} else {
			res.addData(list);
		}
		return res;

	}

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();

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

	@PostMapping("preload")
	public ORSResponse preload(@RequestBody DeveloperForm form) {
		ORSResponse res = new ORSResponse();

		DeveloperDTO dto = new DeveloperDTO();

		dto.setDeveloperName(form.getDeveloperName());
		dto.setSubmitDate(form.getSubmitDate());
		List<DropDownList> submitlist = DevService.search(dto, 0, 0);
		res.addResult("submitlist", submitlist);

		return res;

	}

	@PostMapping("profilePic/{developerId}")
	public ORSResponse uploadpic(@PathVariable Long developerId, @RequestParam("file") MultipartFile file,
			HttpServletResponse response) {

		ORSResponse res = new ORSResponse(true);
		AttachmentDTO Adto = new AttachmentDTO(file);

		Adto.setDescription("profile pic");
		Adto.setDeveloperId(developerId);

		DeveloperDTO dto = new DeveloperDTO();
		dto = DevService.findById(developerId);

		if (dto.getImageId() != null && dto.getImageId() > 0) {
			Adto.setId(dto.getImageId());
		}

		Long imageId = Aservice.add(Adto);

		if (dto.getImageId() == null) {
			dto.setImageId(imageId);
			DevService.update(dto);
		}
		res.addResult("imageId", imageId);
		return res;

	}

	@GetMapping("profilePic/{developerId}")
	public @ResponseBody void downloadPic(@PathVariable long developerId, HttpServletResponse response) {
		try {
			DeveloperDTO cdto = DevService.findById(developerId);

			AttachmentDTO attachmentDTO = null;

			if (cdto != null) {
				attachmentDTO = Aservice.findById(developerId);

			}
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("Error: File Not Found..!!!!!!");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
