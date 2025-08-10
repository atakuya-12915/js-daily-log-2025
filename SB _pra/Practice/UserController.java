
@Controller
public class UserController {

  @GetMapping("/form")
  public String showForm(Model model) {
    model.addAttribute("user", new User,)
  }

}