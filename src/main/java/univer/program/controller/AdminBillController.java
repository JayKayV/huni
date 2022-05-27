package univer.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univer.program.repository.BillRepository;

@Controller
@RequestMapping("/admin/bill")
public class AdminBillController {
    private final BillRepository billRepository;

    public AdminBillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping
    public String billList(Model model) {
        model.addAttribute("bills", billRepository.findAll());
        return "bills";
    }
}
