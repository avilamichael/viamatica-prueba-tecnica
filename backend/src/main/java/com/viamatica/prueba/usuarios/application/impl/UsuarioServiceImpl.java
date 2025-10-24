package com.viamatica.prueba.usuarios.application.impl;

import com.viamatica.prueba.usuarios.application.PersonaService;
import com.viamatica.prueba.usuarios.application.RolService;
import com.viamatica.prueba.usuarios.application.UsuarioService;
import com.viamatica.prueba.usuarios.domain.dto.RegistroUsuarioDto;
import com.viamatica.prueba.usuarios.domain.entity.Persona;
import com.viamatica.prueba.usuarios.domain.entity.Rol;
import com.viamatica.prueba.usuarios.domain.entity.Usuario;
import com.viamatica.prueba.usuarios.infrastructure.UsuarioRepository;
import com.viamatica.prueba.util.RespuestaWs;
import com.viamatica.prueba.util.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final RolService rolService;
    private final PersonaService personaService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(RolService rolService, PersonaService personaService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.rolService = rolService;
        this.personaService = personaService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario crearUsuario(RegistroUsuarioDto usuarioDto) {

        String username = usuarioDto.getUsername();
        if (!username.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
            throw new IllegalArgumentException("El nombre de usuario debe tener entre 8 y 20 caracteres, al menos una mayúscula y un número");
        }

        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        String password = usuarioDto.getPassword();
        if (!password.matches("^(?=.*[A-Z])(?=.*[\\W_])(?=\\S+$).{8,}$")) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, una mayúscula, un signo y sin espacios");
        }

        String identificacion = usuarioDto.getIdentificacion();
        if (identificacion.length() != 10 || !identificacion.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("La identificación debe tener exactamente 10 dígitos numéricos");
        }

        if (personaService.findByIdentificacion(identificacion) != null) {
            throw new IllegalArgumentException("La identificación ya está en uso");
        }

        if (Utils.validarIdentificacionTieneDigitosRepetidosCuatroVeces(identificacion)) {
            throw new IllegalArgumentException("La identificación no puede tener un número repetido 4 veces seguidas");
        }

        List<Rol> roles = usuarioDto.getRoles().stream()
                .map(rolService::findByCodigo)
                .toList();

        String email = generarEmail(usuarioDto.getNombres(), usuarioDto.getApellidos());

        Persona persona = Persona.builder()
                .nombres(usuarioDto.getNombres())
                .apellidos(usuarioDto.getApellidos())
                .identificacion(identificacion)
                .fechaNacimiento(usuarioDto.getFechaNacimiento())
                .build();
        persona = personaService.crearPersona(persona);
        
        Usuario usuario = Usuario.builder()
                .persona(persona)
                .roles(roles)
                .username(usuarioDto.getUsername())
                .email(email)
                .password(passwordEncoder.encode(password))
                .intentosFallidos(0)
                .estado("ACTIVO")
                .build();

        System.out.println("Usuario creado: " + usuario.toString() + "");

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(usuarioActualizado.getUsername());
                    usuario.setPassword(usuarioActualizado.getPassword());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setPersona(usuarioActualizado.getPersona());
                    usuario.setEstado(usuarioActualizado.getEstado());
                    usuario.setRoles(usuarioActualizado.getRoles());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public RespuestaWs eliminarUsuario(Long id) {
        RespuestaWs respuesta = new RespuestaWs();
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                usuario.setEstado("INACTIVO");
                usuarioRepository.save(usuario);
                respuesta.setEstado(true);
                respuesta.setMensaje("Usuario eliminado exitosamente");
            } else {
                respuesta.setEstado(false);
                respuesta.setMensaje("Usuario no encontrado");
                respuesta.setSugerencia("Verifique el ID del usuario");
            }
        } catch (Exception e) {
            respuesta.setEstado(false);
            respuesta.setMensaje("Error al desactivar usuario");
            respuesta.setSugerencia("Intente nuevamente");
        }
        return respuesta;
    }
    
    private String generarEmail(String nombres, String apellidos) {
        String[] nombreArray = nombres.split(" ");
        String[] apellidoArray = apellidos.split(" ");
        String emailPrincipal = (nombreArray[0].toLowerCase().charAt(0) + apellidoArray[0].toLowerCase()) + "@mail.com";

        String email = emailPrincipal;
        Integer count = 1;
        while (usuarioRepository.findByEmail(email).isPresent()) {
            email = emailPrincipal.replace("@mail.com", count + "@mail.com");
            count++;
        }
        return email;
    }


}