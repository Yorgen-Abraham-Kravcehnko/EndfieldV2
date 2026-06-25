package Grupo_Endfield.EndfieldV2.Servicio;

import Grupo_Endfield.EndfieldV2.DTO.VehiculoDTO;
import Grupo_Endfield.EndfieldV2.DTO.VehiculoResponseDTO;
import Grupo_Endfield.EndfieldV2.Modelo.Vehiculo;
import Grupo_Endfield.EndfieldV2.Repositorio.VehiculoRepositorio;
import Grupo_Endfield.EndfieldV2.Repositorio.VisitanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private VisitanteRepositorio visitanteRepositorio;

    public List<VehiculoResponseDTO> obtenerTodos() {
        return vehiculoRepositorio.findAll().stream()
            .map(this::convertirAResponseDTO)
            .collect(Collectors.toList());
    }

    public Optional<VehiculoResponseDTO> obtenerPorId(Integer id) {
        return vehiculoRepositorio.findById(id)
            .map(this::convertirAResponseDTO);
    }

    public VehiculoResponseDTO guardar(VehiculoDTO dto) {
        // Validar patente según tipo de vehículo
        validarPatente(dto.getPatente(), dto.getTipoVehiculo());

        // Buscar visitante
        var visitante = visitanteRepositorio.findById(dto.getIdVisitante())
            .orElseThrow(() -> new RuntimeException(
                "Visitante con id " + dto.getIdVisitante() + " no encontrado"));

        // Convertir DTO a entidad
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setVisitante(visitante);
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setColor(dto.getColor());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());

        return convertirAResponseDTO(vehiculoRepositorio.save(vehiculo));
    }

    public VehiculoResponseDTO actualizar(Integer id, VehiculoDTO dto) {
        // Validar que existe
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "Vehículo con id " + id + " no encontrado"));

        // Validar patente según tipo
        validarPatente(dto.getPatente(), dto.getTipoVehiculo());

        // Actualizar campos
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setColor(dto.getColor());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());

        return convertirAResponseDTO(vehiculoRepositorio.save(vehiculo));
    }

    public void eliminar(Integer id) {
        if (!vehiculoRepositorio.existsById(id)) {
            throw new RuntimeException("Vehículo con id " + id + " no encontrado");
        }
        vehiculoRepositorio.deleteById(id);
    }

    private void validarPatente(String patente, String tipoVehiculo) {
        if (tipoVehiculo.equals("MOTOCICLETA")) {
            if (!patente.matches("^[A-Z]{4}[0-9]$")) {
                throw new RuntimeException(
                    "Patente de motocicleta inválida. Formato esperado: BBBB0 (4 letras + 1 dígito)");
            }
        } else {
            if (!patente.matches("^[A-Z]{5}[0-9]$")) {
                throw new RuntimeException(
                    "Patente de vehículo inválida. Formato esperado: BBBBB0 (5 letras + 1 dígito)");
            }
        }
    }

    private VehiculoResponseDTO convertirAResponseDTO(Vehiculo vehiculo) {
        return new VehiculoResponseDTO(
            vehiculo.getIdVehiculo(),
            vehiculo.getVisitante().getIdVisitante(),
            vehiculo.getVisitante().getNombre() + " " + vehiculo.getVisitante().getPrimerApellido(),
            vehiculo.getPatente(),
            vehiculo.getMarca(),
            vehiculo.getModelo(),
            vehiculo.getColor(),
            vehiculo.getTipoVehiculo()
        );
    }
}