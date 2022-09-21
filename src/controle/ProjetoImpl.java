package br.unicesumar.aula.controle;

import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class ProjetoImpl implements ProjetoDAO {
    private static Set<Projeto> projetos = new HashSet<Projeto>();
 
    @Override
    public void adicionar(Projeto projeto) throws DadoConsultadoException {
        for(Projeto p: projetos) {
            if (projeto.getNome().equalsIgnoreCase(p.getNome())) {
                throw new DadoConsultadoException("Projeto ja Existe");   
            }
        }
        if (projeto.getDataInicio().compareTo(projeto.getDataFinal()) == 1) {
            throw new DadoConsultadoException("Data inicial maior que data final.");
        }
        projetos.add(projeto);
       
    }
 
    @Override
    public List<Projeto> listar() {
            List<Projeto> projetoList = new ArrayList<>();
            projetoList.addAll(projetos);
            return  projetoList;
    }
 
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for(Projeto p: projetos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        throw new DadoConsultadoException("Projeto " + nome + " não encontrado.\n");
    }
 
    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
         Projeto projeto2 = consultarPorNome(nome);
         this.adicionar(projeto);
         this.excluir(nome);
         return projeto2;
        
    }
 
    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException{
        if (projetos.contains(projeto)) {
            projetos.remove(projeto);
       
        } else {
             throw new DadoConsultadoException("Projeto " + projeto + " não encontrado.\n");
        }
    }
    
 
    @Override
    public void excluir(String nome) throws DadoConsultadoException{
        Projeto projeto = consultarPorNome(nome);
        this.excluir(projeto);
    }
}
