package br.com.vhclaw.timesheet.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.com.vhclaw.timesheet.entities.Usuario;
import br.com.vhclaw.timesheet.repositories.UsuarioRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UsuarioRepository repository;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usr = repository.findByUsuario(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		map.put("nome", usr.getAdvogado().getNome());
		map.put("id", usr.getAdvogado().getId());
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		
		token.setAdditionalInformation(map);
		
		return accessToken;
	}

}
