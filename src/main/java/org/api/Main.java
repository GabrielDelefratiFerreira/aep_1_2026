// foi especificado pra nao utilizar de frameworks, o proprio java contem 
// uma biblioteca http que permite a abertura de uma porta para a aplicacao,
// a base escolhida pra iniciar as rotas segue os padroes da biblioteca httpserver
// esse por sua vez construido em cima da classe ApiServer

package org.api;

import org.api.core.ApiServer;
import org.api.routes.anexo.AnexoController;
import org.api.routes.auth.AuthController;
import org.api.routes.categoria.CategoriaController;
import org.api.routes.comentario.ComentarioController;
import org.api.routes.solicitacao.SolicitacaoController;
import org.api.routes.solicitacao.SolicitacaoService;
import org.api.routes.token.TokenService;
import org.api.routes.usuario.UsuarioController;
import org.api.routes.usuario.UsuarioService;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiServer server = new ApiServer();
        TokenService tokenService = new TokenService();
        UsuarioService userService = new UsuarioService();
        SolicitacaoService solicitacaoService = new SolicitacaoService();

        new SolicitacaoController(server, solicitacaoService);
        new UsuarioController(server, userService);
        new AuthController(server, tokenService, userService);
        new ComentarioController(server, solicitacaoService, userService);
        new AnexoController(server, solicitacaoService);
        new CategoriaController(server );

        server.run(tokenService);
    }
}
