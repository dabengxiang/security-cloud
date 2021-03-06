package com.onion.config;

import com.onion.properties.OnionAuthProperties;
import com.onion.properties.OnionClientProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author gyc
 * @date 2020/3/10
 */

@EnableAuthorizationServer
@Configuration
public class OnionAuthServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }






//    @Autowired
//    private AuthenticationManager authenticationManager;



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OnionAuthProperties onionAuthProperties;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(redisTokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
//        endpoints.tokenStore(redisTokenStore()).authenticationManager(authenticationManager).exceptionTranslator(new OnionWebResponseExceptionTranslator());
    }







    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        OnionClientProperties[] clientPropertiesArr = onionAuthProperties.getClients();


        for (OnionClientProperties onionClientProperties : clientPropertiesArr) {
            if(StringUtils.isBlank(onionClientProperties.getClient())){
                throw new Exception("client不能为空");
            }
            if(StringUtils.isBlank(onionClientProperties.getSecret())){
                throw new Exception("secret不能为空");
            }


            String[] split = StringUtils.split(onionClientProperties.getGrantType(), ",");
            String[] scopes = StringUtils.split(onionClientProperties.getScope(), ",");


            builder.withClient(onionClientProperties.getClient()).secret(passwordEncoder.encode(onionClientProperties.getSecret()))
                    .authorizedGrantTypes(split)
                    .scopes(scopes);


        }




    }




    @Bean
    public AuthorizationServerTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setAccessTokenValiditySeconds(60*60*24); //一天
//        tokenServices.setRefreshTokenValiditySeconds(60*60*24*7);  //一周
       tokenServices.setSupportRefreshToken(true);

       tokenServices.setTokenStore(redisTokenStore());
       tokenServices.setAccessTokenValiditySeconds(onionAuthProperties.getAccessTokenValiditySeconds()); //一天
       tokenServices.setRefreshTokenValiditySeconds(onionAuthProperties.getRefreshTokenValiditySeconds());  //一周
        return tokenServices;

    }

}
