package com.urna.api.service;

import com.urna.api.model.Usuario;
import com.urna.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado (" + id + ")"));
    }

    public Usuario salvar(Usuario usuario) {

        if (repository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }

        // hash da senha
        usuario.setSenhaHash(encoder.encode(usuario.getSenhaHash()));

        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario atualizado) {

        Usuario existente = buscarPorId(id);

        if (!existente.getEmail().equals(atualizado.getEmail()) &&
                repository.existsByEmail(atualizado.getEmail())) {
            throw new RuntimeException("Email já existente!");
        }

        existente.setNome(atualizado.getNome());
        existente.setEmail(atualizado.getEmail());
        existente.setPerfil(atualizado.getPerfil());

        // só atualiza senha se vier preenchida
        if (atualizado.getSenhaHash() != null && !atualizado.getSenhaHash().isEmpty()) {
            existente.setSenhaHash(encoder.encode(atualizado.getSenhaHash()));
        }

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public Usuario autenticar(String email, String senha) {

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        return usuario;
    }
}