package Default.Controller;

import Default.DTO.LoginDTO;
import Default.DTO.RegistrationDTO;
import Default.Service.Impl.TaskServiceImpl;
import Default.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    TaskServiceImpl taskService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("")
    public String showHomePage(Model model){
        model.addAttribute("tasks", taskService.findAllTasks());
//       List<Task> task = taskRepository.findAll();
//        System.out.println(task.toString());
        return "index";
    }

    @GetMapping("/registration-form")
    public String showForm(Model model){
        model.addAttribute("person", new RegistrationDTO());
        return "UserRegistration";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("person") RegistrationDTO registrationDTO){
     userService.registerUser(registrationDTO);
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String showLoginForm(Model model){
        model.addAttribute("user",  new LoginDTO());
        return "login-form";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user") LoginDTO loginDTO, HttpSession session){



           if(userService.Login(loginDTO)) {
               session.setAttribute("user", loginDTO.getEmail());
               return "redirect:/";
           }
           return "redirect:/error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return"redirect:/";
    }


    @GetMapping("/update-user/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        model.addAttribute("update", userService.findUser(id));
        return "updateTask";
    }

//    @PostMapping("/update-user"){
//        public String updateUser(@ModelAttribute("user") )
//    }



}
