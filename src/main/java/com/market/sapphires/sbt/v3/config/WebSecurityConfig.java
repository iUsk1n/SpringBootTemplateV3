package com.market.sapphires.sbt.v3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.market.sapphires.sbt.v3.service.auth.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用。
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * 認可設定を無視するリクエストを設定。<br>
     * 静的リソース(image,js,css,webjars)を認可処理の対象から除外する。
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/js/**",
                "/webjars/**");
    }

    /**
     * 認証・認可の情報を設定する。<br>
     * 画面遷移のURL・パラメータを取得するname属性の値を設定。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/portal").authenticated()
                .mvcMatchers("/users/info").authenticated()
                .mvcMatchers("/users/**").hasAuthority("USER_SHOW")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //ログインページはコントローラを経由しないのでViewNameとの紐付けが必要
                .loginProcessingUrl("/auth") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
                .usernameParameter("username") //リクエストパラメータのname属性を明示
                .passwordParameter("password")
                .defaultSuccessUrl("/portal", true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    /**
     * 認証時に利用するデータソースを定義する設定メソッド。<br>
     * ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている。
     */
    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

}
