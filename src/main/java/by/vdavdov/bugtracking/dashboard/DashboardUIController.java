package by.vdavdov.bugtracking.dashboard;

import by.vdavdov.bugtracking.Handlers;
import by.vdavdov.common.TimestampRepository;
import by.vdavdov.ref.RefType;
import by.vdavdov.ref.ReferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(DashboardUIController.DASHBOARD_URL)
@RequiredArgsConstructor
public class DashboardUIController {
    static final String DASHBOARD_URL = "/ui/dashboard";

    private final Handlers.ProjectHandler handler;

    @GetMapping
    public String showDashboard(Model model) {
        log.info("show dashboard");
        model.addAttribute("projects", handler.getAllTos(TimestampRepository.NEWEST_FIRST));
        model.addAttribute("taskStatusRefs", ReferenceService.getRefs(RefType.TASK_STATUS));
        return "dashboard";
    }
}
