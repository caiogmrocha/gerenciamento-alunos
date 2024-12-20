package br.com.gerenciamento.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoRepositoryTest {
    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void deveriaCarregarUmAlunoAoBuscarPeloNome() {
        Aluno aluno = new Aluno();
        aluno.setNome("Matheus");
        aluno.setMatricula("123456");
        aluno.setStatus(Status.ATIVO);
        aluno.setCurso(Curso.INFORMATICA);
        aluno.setTurno(Turno.NOTURNO);
        alunoRepository.save(aluno);

        String nome = "Matheus";
        List<Aluno> alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        Assert.assertEquals(1, alunos.size());
        Assert.assertEquals(nome, alunos.get(0).getNome());
    }

    @Test
    public void deveriaRetornarUmaListaVaziaAoBuscarPorNomeInexistente() {
        String nome = "Matheus";
        List<Aluno> alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        Assert.assertEquals(0, alunos.size());
    }

    @Test
    public void deveriaCarregarUmAlunoAtivoAoBuscarPeloStatus() {
        Aluno aluno = new Aluno();
        aluno.setNome("Matheus");
        aluno.setMatricula("123456");
        aluno.setStatus(Status.ATIVO);
        aluno.setCurso(Curso.INFORMATICA);
        aluno.setTurno(Turno.NOTURNO);
        alunoRepository.save(aluno);

        List<Aluno> alunos = alunoRepository.findByStatusAtivo();
        Assert.assertEquals(1, alunos.size());
        Assert.assertEquals(Status.ATIVO, alunos.get(0).getStatus());
    }

    @Test
    public void deveriaCarregarUmAlunoInativoAoBuscarPeloStatus() {
        Aluno aluno = new Aluno();
        aluno.setNome("Matheus");
        aluno.setMatricula("123456");
        aluno.setStatus(Status.INATIVO);
        aluno.setCurso(Curso.INFORMATICA);
        aluno.setTurno(Turno.NOTURNO);
        alunoRepository.save(aluno);

        List<Aluno> alunos = alunoRepository.findByStatusInativo();
        Assert.assertEquals(1, alunos.size());
        Assert.assertEquals(Status.INATIVO, alunos.get(0).getStatus());
    }
}
