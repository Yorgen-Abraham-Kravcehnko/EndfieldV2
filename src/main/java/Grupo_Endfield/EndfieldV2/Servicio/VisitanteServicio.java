package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.VisitanteDTO;
import Grupo_Endfield.EndfieldV2.DTO.VisitanteResponseDTO;
import Grupo_Endfield.EndfieldV2.DTO.VehiculoResumenDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Visitante;
import Grupo_Endfield.EndfieldV2.Repositorio.VisitanteRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitanteServicio {

    @Autowired
    private VisitanteRepositorio visitanteRepositorio;

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    public List<VisitanteResponseDTO> obtenerTodos() {
        return visitanteRepositorio.findAll().stream()
            .map(this::convertirAResponseDTO)
            .collect(Collectors.toList());
    }

    public Optional<VisitanteResponseDTO> obtenerPorId(Integer id) {
        return visitanteRepositorio.findById(id)
            .map(this::convertirAResponseDTO);
    }

    public VisitanteResponseDTO guardar(VisitanteDTO dto) {
        // Validar RUT duplicado
        boolean rutExiste = visitanteRepositorio.findAll().stream()
            .anyMatch(v -> v.getRut().equals(dto.getRut()));
        if (rutExiste) {
            throw new RuntimeException(
                "Ya existe un visitante con el RUT " + dto.getRut());
        }

        Visitante visitante = new Visitante();
        visitante.setNombre(dto.getNombre());
        visitante.setPrimerApellido(dto.getPrimerApellido());
        visitante.setSegundoApellido(dto.getSegundoApellido());
        visitante.setRut(dto.getRut());
        visitante.setDv(dto.getDv());
        visitante.setEdad(dto.getEdad());
        visitante.setSexo(dto.getSexo());
        visitante.setCorreo(dto.getCorreo());

        return convertirAResponseDTO(visitanteRepositorio.save(visitante));
    }

    public VisitanteResponseDTO actualizar(Integer id, VisitanteDTO dto) {
        Visitante visitante = visitanteRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "Visitante con id " + id + " no encontrado"));

        // Validar RUT duplicado excluyendo el actual
        boolean rutExiste = visitanteRepositorio.findAll().stream()
            .anyMatch(v -> v.getRut().equals(dto.getRut()) 
                && !v.getIdVisitante().equals(id));
        if (rutExiste) {
            throw new RuntimeException(
                "Ya existe un visitante con el RUT " + dto.getRut());
        }

        visitante.setNombre(dto.getNombre());
        visitante.setPrimerApellido(dto.getPrimerApellido());
        visitante.setSegundoApellido(dto.getSegundoApellido());
        visitante.setRut(dto.getRut());
        visitante.setDv(dto.getDv());
        visitante.setEdad(dto.getEdad());
        visitante.setSexo(dto.getSexo());
        visitante.setCorreo(dto.getCorreo());

        return convertirAResponseDTO(visitanteRepositorio.save(visitante));
    }

    public void eliminar(Integer id) {
        if (!visitanteRepositorio.existsById(id)) {
            throw new RuntimeException(
                "Visitante con id " + id + " no encontrado");
        }
        visitanteRepositorio.deleteById(id);
    }

    private VisitanteResponseDTO convertirAResponseDTO(Visitante visitante) {
        List<VehiculoResumenDTO> vehiculos = vehiculoRepositorio.findAll().stream()
            .filter(v -> v.getVisitante().getIdVisitante()
                .equals(visitante.getIdVisitante()))
            .map(v -> new VehiculoResumenDTO(v.getIdVehiculo(), v.getPatente()))
            .collect(Collectors.toList());

        return new VisitanteResponseDTO(
            visitante.getIdVisitante(),
            visitante.getNombre(),
            visitante.getPrimerApellido(),
            visitante.getSegundoApellido(),
            visitante.getRut(),
            visitante.getDv(),
            visitante.getEdad(),
            visitante.getSexo(),
            visitante.getCorreo(),
            vehiculos
        );
    }
}