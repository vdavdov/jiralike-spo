package by.vdavdov.ref.internal.web;

import by.vdavdov.ref.RefType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping(value = AdminUIReferenceController.URL)
public class AdminUIReferenceController {
    static final String URL = "/ui/admin/references";

    @GetMapping
    public String getRefTypes(Model model) {
        model.addAttribute("refTypes", RefType.values());
        return "references";
    }
}

