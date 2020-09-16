package demo.configuration;

import demo.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserServiceImpl userService;


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("1admin").password(bCryptPasswordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
//                .successHandler(successHandler())
//                .failureHandler(failureHandler()).permitAll()
                .and()
//                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                .accessDeniedHandler(accessDeniedHandler())
//                .and()
                .csrf().disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .deleteCookies("JSESSIONID").permitAll();
    }


//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
//                        .allowedOrigins().allowCredentials(true);
//            }
//        };
//    }

//    private AuthenticationSuccessHandler successHandler() {
//        return new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.getWriter().append("OK");
//                httpServletResponse.setStatus(200);
//            }
//        };
//    }
//
//    private AuthenticationFailureHandler failureHandler() {
//        return new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                httpServletResponse.getWriter().append("Authentication failure");
//                httpServletResponse.setStatus(401);
//            }
//        };
//    }
//
//    private AccessDeniedHandler accessDeniedHandler() {
//        return new AccessDeniedHandler() {
//            @Override
//            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//                httpServletResponse.getWriter().append("Access denied");
//                httpServletResponse.setStatus(403);
//            }
//        };
//    }

}
