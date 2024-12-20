package br.com.gerenciamento.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.exception.EmailExistsException;
import br.com.gerenciamento.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
    @Autowired
    private ServiceUsuario serviceUsuario;

    @Test
    public void deveriaLancarEmailExistsExceptionParaSalvarUsuario() {
        Usuario user = new Usuario();
        user.setUser("john_doe");
        user.setEmail("john@doe.com");
        user.setSenha("1234");

        try {
            this.serviceUsuario.salvarUsuario(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Assert.assertThrows(EmailExistsException.class, () -> {
            this.serviceUsuario.salvarUsuario(user);
        });
    }

    @Test
    public void deveriaSalvarUsuario() {
        Usuario user = new Usuario();
        user.setUser("john_doe");
        user.setEmail("john@doe.com");
        user.setSenha("1234");

        try {
            this.serviceUsuario.salvarUsuario(user);
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail("Não deveria lançar exceção");
        }

        Assert.assertNotNull(user.getId());
    }

    @Test
    public void deveriaLogarUsuario() {
        Usuario user = new Usuario();
        user.setUser("john_doe");
        user.setEmail("john@doe.com");
        user.setSenha("1234");

        try {
            this.serviceUsuario.salvarUsuario(user);
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail("Não deveria lançar exceção");
        }

        Usuario userRetorno = this.serviceUsuario.loginUser(user.getUser(), user.getSenha());

        Assert.assertNotNull(userRetorno);
    }

    @Test
    public void deveriaRetornarNullParaLogarUsuario() {
        Usuario user = new Usuario();
        user.setUser("john_doe");
        user.setEmail("john@doe.com");
        user.setSenha("1234");

        try {
            this.serviceUsuario.salvarUsuario(user);
        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail("Não deveria lançar exceção");
        }

        Usuario userRetorno = this.serviceUsuario.loginUser(user.getUser(), "12345");

        Assert.assertNull(userRetorno);
    }
}
