package com.example.spring;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // パスワードエンコーダのBean定義
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Data Source
    @Autowired
    private DataSource dataSource;

    // User ID + Password SQL
    private static final String USER_SQL = "SELECT"
            + " user_id,"
            + " password,"
            + " true"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

    // User Role SQL
    private static final String ROLE_SQL = "SELECT"
            + " user_id,"
            + " role"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Exclude Static Resources
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Set Page without Login
        http
            .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();

        // login
        http
            .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true);

        // logout
        http
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");

        // CSRF
        // http.csrf().disable();
        RequestMatcher csrfMatcher = new RestMatcher("/rest/**");

        // disable CSRF if url matches rest
        http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // get user data
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(USER_SQL)
            .authoritiesByUsernameQuery(ROLE_SQL)
            .passwordEncoder(passwordEncoder());
    }
}
