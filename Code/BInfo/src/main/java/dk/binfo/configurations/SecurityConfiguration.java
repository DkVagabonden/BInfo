package dk.binfo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/login", "/forgotpassword/**", "/registration", "/error").permitAll()
				.antMatchers("/apartment/**", "/users/**").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true").permitAll()
				.defaultSuccessUrl("/home")
				.usernameParameter("email").passwordParameter("password")
				.and().rememberMe().key("rem-me-key").rememberMeParameter("remember-me").rememberMeCookieName("remember-me")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
				// TODO admin skal kunne se alle ventelister (/admin/list/Family/intern/ekstern/connectMrawesomeSexy)
				// TODO lave en SQL side hvor admin kan se hvornår en bruger loggede sidst ind og deres IP. -- skip for nu
				// TODO lave så admin kan godkende nyopskrevet brugere.
				// TODO Admin skal godkende en bruger har betalt
				// TODO brugere skal kunne registrere sig som brugere
				// TODO Brugere skal kunne opskrive sig til en venteliste
				// TODO brugere skal kunne indtaste og redigere deres ønske til lejligheder og makspris
				// TODO Se placering på venteliste
				// TODO lave at admin får en mail når en bruger opretter sig og sende mail til brugere - STEEN
				// TODO Sende mail til en bruger hvis de ikke har betalt 1 måned forud. - STEEN
				// TODO Generere en venteliste til PDF til admin  - JENS
				// TODO admin skal kunne oprette en ny bruger/se alle brugere og søge/edit (/admin/user/ + HTML)
				// TODO konfigurer databasen med alle lister
				// TODO test lister funktion
				// TODO lav user bruger indstillinger side (/user/settings Controller + HTML) - MORTEN - DONE
				// TODO lav admin bruger indstillinger (/admin/settings Controller + html) - MORTEN - DONE
				// TODO Lav javadoc på alle classer, methoder og atributer
				// TODO lav admin bruger indstillinger (/admin/settings Controller + html) - MORTEN - DONE
				// TODO Lav javadoc på alle classer, methoder og
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**", "/static/**", "/fonts/**", "/css/**", "/js/**", "/img/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		return new CustomAccessDeniedHandler();
	}

	@Bean //TODO CHECK DENNE
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(sec);
		return templateEngine;
	}

}


// As for the configureGlobal(AuthenticationManagerBuilder) method, it sets up an in-memory user store with a single user. That user is given a username of "user", a password of "password", and a role of "USER". */ //TODO PATRICK
