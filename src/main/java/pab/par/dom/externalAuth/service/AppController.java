package pab.par.dom.externalauth.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/externalauth")
// @SessionAttributes("name")
public class AppController {

  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public String welcome(Model model, OAuth2AuthenticationToken authentication) {

    OAuth2AuthorizedClient client = this.authorizedClientService
        .loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

    String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

    if (!StringUtils.isEmpty(userInfoEndpointUri)) {
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
      HttpEntity<String> entity = new HttpEntity<>("", headers);
      ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
      Map<?, ?> userAttributes = response.getBody();
      model.addAttribute("name", userAttributes.get("name"));
      model.addAttribute("email", userAttributes.get("email"));
      model.addAttribute("pic", userAttributes.get("picture"));
    }

    return "welcome";
  }
}
