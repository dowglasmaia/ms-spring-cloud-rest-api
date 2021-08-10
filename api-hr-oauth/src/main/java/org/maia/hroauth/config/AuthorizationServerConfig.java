package org.maia.hroauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/* configurando o servidor  de autorização */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${oauth.client.name}") // carregada do properties do githud  **** api-ms-spring-cloud-configs/application.properties
	private String clientName;

	@Value("${oauth.client.secret}") 
	private String clientSecret;
	
	@Value("${token.validity.seconds}") 
	private int validitySeconds;
	
	@Value("${authorized.grant.types}") 
	private String authorizedGrantTypes;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JwtAccessTokenConverter tokenConverter;

	@Autowired
	private JwtTokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	/* conf a autorização com base nas credencias do app 'Client' */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()  									// autenticação somente em memoria
		.withClient(clientName)									// nome da aplicação cliente
		.secret(passwordEncoder.encode(clientSecret) )			// senha da aplicação cliente
		.scopes("read","write")									// leitura e graçação
		.authorizedGrantTypes(authorizedGrantTypes)				// grant_type - password
		.accessTokenValiditySeconds(validitySeconds);			// tempo de duração do token em milisegundos -exp 24horas: 60 * 60 * 24
	}

	/* processa o token */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				 .tokenStore(tokenStore)
				 .accessTokenConverter(tokenConverter);
	}

}
