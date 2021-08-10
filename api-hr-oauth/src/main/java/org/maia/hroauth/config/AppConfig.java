package org.maia.hroauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

	
	@Value("${jwt.secret}") // carregada do properties do githud  **** api-ms-spring-cloud-configs/application.properties
	private String jwtSecret;
	
	/* Ecrypt */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	
	/* para Assinatura Secreta do Token*/
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter =  new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtSecret); // chave de assinatura secreta
		return tokenConverter;
	}
	
	
	/* ler as informações do token */
	/**recebe como argumento um  JwtAccessTokenConverter no retorno da instancia*/
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore( accessTokenConverter() );
	}
	
	
	
}
