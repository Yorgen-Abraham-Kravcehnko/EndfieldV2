package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.UsuarioDTO;
import Grupo_Endfield.EndfieldV2.DTO.UsuarioResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Usuario;
import Grupo_Endfield.EndfieldV2.Modelo.Visitante;
import Grupo_Endfield.EndfieldV2.Repositorio.UsuarioRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.VisitanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private VisitanteRepositorio visitanteRepositorio;

    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepositorio.findAll().stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> obtenerPorId(Integer id) {
        return usuarioRepositorio.findById(id)
            .map(this::convertirAResponse);
    }

    public UsuarioResponseDTO guardar(UsuarioDTO dto) {
        Visitante visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> new RuntimeException("Visitante no encontrado."));

        Usuario usuario = new Usuario();
        usuario.setVisitante(visitante);
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setActivo(true);

        return convertirAResponse(usuarioRepositorio.save(usuario));
    }

    public UsuarioResponseDTO actualizar(Integer id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        Visitante visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> new RuntimeException("Visitante no encontrado."));

        usuario.setVisitante(visitante);
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());

        return convertirAResponse(usuarioRepositorio.save(usuario));
    }

    public void eliminar(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    private UsuarioResponseDTO convertirAResponse(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setUsername(u.getUsername());
        dto.setActivo(u.getActivo());
        dto.setNombreVisitante(u.getVisitante() != null
            ? u.getVisitante().getNombre() : null);
        dto.setRutVisitante(u.getVisitante() != null
            ? u.getVisitante().getRut() : null);
        return dto;
    }
}