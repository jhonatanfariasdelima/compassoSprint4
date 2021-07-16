package prova.compasso.com.br.prova.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import prova.compasso.com.br.prova.model.Usuario;
import prova.compasso.com.br.prova.repository.UsuarioRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class autenticacaoComToken extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public autenticacaoComToken(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        boolean validade = tokenService.isTokenValido(token);
        if (validade){
            autenticar(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void autenticar(String token){

        Long idusuario = tokenService.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idusuario).get();

        UsernamePasswordAuthenticationToken autent =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autent);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
