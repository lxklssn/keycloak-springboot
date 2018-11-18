package de.summit.keycloakspringboot;

import de.summit.keycloakspringboot.customer.CustomerService;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WebController {

//    private final CustomerService customerService;

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/intranet")
    public String intranet(Principal principal, Model model) {
        ResponseEntity<Iterable> customerResponse = keycloakRestTemplate.getForEntity("http://localhost:8085/customer", Iterable.class);
        model.addAttribute("customers", customerResponse.getBody());
        model.addAttribute("username", principal.getName());
        return "intranet";
    }

    @GetMapping(path = "/admin")
    public String admin(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "admin";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return index();
    }

}
