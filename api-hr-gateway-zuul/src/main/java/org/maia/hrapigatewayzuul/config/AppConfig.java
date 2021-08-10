package org.maia.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope //atualizar as variaveis em tempo de execução com actuetor.
@Configuration
public class AppConfig {

	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	
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
