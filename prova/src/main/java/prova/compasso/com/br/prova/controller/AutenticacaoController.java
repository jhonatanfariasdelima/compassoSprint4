package prova.compasso.com.br.prova.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import prova.compasso.com.br.prova.config.security.TokenService;
import prova.compasso.com.br.prova.dto.TokenDto;
import prova.compasso.com.br.prova.form.autenticacao.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticacao(@RequestBody LoginForm loginForm){
        UsernamePasswordAuthenticationToken dadosLogin = loginForm.convert();

        try{
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
