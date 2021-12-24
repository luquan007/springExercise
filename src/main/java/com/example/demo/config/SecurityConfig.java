//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Resource
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //本身默认的
//        //super.configure(auth);
//
//        //基于内存的认证  失败
// PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();//不加这一条没法解析密码
//        auth.inMemoryAuthentication().withUser("cq").password(encoder.encode("cqIsTrick")).authorities("ADMIN")
//                .and().withUser("lq").password(encoder.encode("1 2 3")).authorities("ROLE_USER");
//
//
//        //基于JDBC的认证 successful
//        auth.jdbcAuthentication().dataSource(dataSource).
//                usersByUsernameQuery("select username , password ,enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username,authority from authorities where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//
//}
